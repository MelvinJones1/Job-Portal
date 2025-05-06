package com.spring.rest_api.career_crafter.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	public List<Job> getAllJob() {
		return jobRepository.findAll();
	}

	public Job getJobById(int jobId) throws InvalidIDException {
		Optional<Job> optional = jobRepository.findById(jobId);
		if (optional.isEmpty())
			throw new InvalidIDException("Invalid Job ID...");
		return optional.get();
	}

	public Job createJob(Job job) {

		return jobRepository.save(job);
	}

	public List<Job> fetchJobsByHr(int hrId) {

		return jobRepository.findByHrId(hrId);
	}

	public Job updateJob(int jobId, Job updatedJob) throws InvalidIDException {
		Job job = getJobById(jobId);

		job.setTitle(updatedJob.getTitle());
		job.setDepartment(updatedJob.getDepartment());
		job.setJobType(updatedJob.getJobType());
		job.setLocation(updatedJob.getLocation());
		job.setSalaryRange(updatedJob.getSalaryRange());
		job.setDescription(updatedJob.getDescription());
		job.setRequirements(updatedJob.getRequirements());
		job.setBenefits(updatedJob.getBenefits());
		job.setApplicationDeadline(updatedJob.getApplicationDeadline());
		job.setStatus(updatedJob.getStatus());

		return jobRepository.save(job);
	}

	public String removeJob(int jobId) throws InvalidIDException {
		Job job = getJobById(jobId);

		jobRepository.delete(job);
		return "Job deleted successfully.";
	}

}
