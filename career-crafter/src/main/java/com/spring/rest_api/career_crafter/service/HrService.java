package com.spring.rest_api.career_crafter.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.enums.ApplicationStatus;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Application;
import com.spring.rest_api.career_crafter.model.Company;
import com.spring.rest_api.career_crafter.model.Hr;
import com.spring.rest_api.career_crafter.model.Interview;
import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.HrRepository;

@Service
public class HrService {
	
	@Autowired
	private HrRepository hrRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private InterviewService interviewService;
	


	public Hr findById(int hrId) throws InvalidIDException {
	
		Optional<Hr> optional = hrRepository.findById(hrId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Hr Id is not valid....");
		}
		return optional.get();
		
		
	}

	

	
	public Hr createHr(int userId, int companyId, Hr hr) throws InvalidIDException {
        User user = authService.findById(userId);
        if (!user.getRole().equalsIgnoreCase("HR")) {
            throw new RuntimeException("User must have HR role.");
        }

        Company company = companyService.getSingleCompany(companyId);

        hr.setUser(user);
        hr.setCompany(company);
        hr.setCreatedAt(LocalDate.now());

        return hrRepository.save(hr);
    }


	public long getTotalJobsByHr(int hrId) throws InvalidIDException {
		
	    if(findById(hrId)==null) {
	    	throw new InvalidIDException("Invalid Hr Id.....");
	    }; 

	    List<Job> jobs = jobService.getAllJob().stream()
	        .filter(job -> job.getHr().getId() == hrId)
	        .toList();

	    return jobs.size();
	}



	public long getTotalHiredByHr(int hrId) throws InvalidIDException {
		 if(findById(hrId)==null) {
		    	throw new InvalidIDException("Invalid Hr Id.....");
		    }; 

	    List<Job> jobs = jobService.getAllJob().stream()
	        .filter(job -> job.getHr().getId() == hrId)
	        .toList();

	    List<Application> allApplications = applicationService.getAllApplication().stream()
	        .filter(app -> jobs.contains(app.getJob()))
	        .filter(app -> app.getStatus().equals(ApplicationStatus.HIRED))
	        .toList();

	    return allApplications.size();
	}
	
	
	public List<Job> getRecentJobs(int hrId) throws InvalidIDException {
	    if (findById(hrId) == null) {
	        throw new InvalidIDException("Invalid HR Id...");
	    }

	    List<Job> jobList = new ArrayList<>();

	    for (Job job : jobService.getAllJob()) {
	        if (job.getHr().getId() == hrId) {
	            jobList.add(job);
	        }
	    }

	    // Sort jobs by applicationDeadline (latest first)
	    jobList.sort((a, b) -> b.getApplicationDeadline().compareTo(a.getApplicationDeadline()));

	    // Return top 3
	    return jobList.stream().limit(3).toList();
	}
	
	
	public List<Interview> getRecentInterviews(int hrId) throws InvalidIDException {
	    if (findById(hrId) == null) {
	        throw new InvalidIDException("Invalid HR Id...");
	    }

	    List<Interview> interviews = new ArrayList<>();

	    for (Interview i : interviewService.getAllInterviews()) {
	        if (i.getApplication().getJob().getHr().getId() == hrId) {
	            interviews.add(i);
	        }
	    }

	    // Sort interviews by date + time (earliest first)
	    interviews.sort((a, b) -> {
	        LocalDateTime dt1 = LocalDateTime.of(a.getDate(), a.getTime());
	        LocalDateTime dt2 = LocalDateTime.of(b.getDate(), b.getTime());
	        return dt1.compareTo(dt2);
	    });

	    // Return top 3
	    return interviews.stream().limit(3).toList();
	}




	
	

}
