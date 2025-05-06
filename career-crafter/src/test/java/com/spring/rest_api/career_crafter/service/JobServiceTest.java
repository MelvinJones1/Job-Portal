package com.spring.rest_api.career_crafter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.rest_api.career_crafter.enums.JobStatus;
import com.spring.rest_api.career_crafter.enums.JobType;
import com.spring.rest_api.career_crafter.model.Hr;
import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.repository.JobRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

	@InjectMocks
	private JobService jobService;

	@Mock
	private JobRepository jobRepository;

	Hr hr;
	Job job1, job2;

	@BeforeEach
	public void init() {
		hr = new Hr(1, "Ravi HR", "ravi@example.com");

		job1 = new Job(101, "Java Backend Developer", "Engineering", JobType.FULL_TIME, "Remote", "10LPA",
				"Work on backend", "Java, Spring", LocalDate.of(2025, 5, 30), JobStatus.PUBLISHED, hr);

		job2 = new Job(102, "Frontend Developer", "Engineering", JobType.CONTRACT, "Hybrid", "8LPA", "Work on UI",
				"React, HTML, CSS", LocalDate.of(2025, 5, 25), JobStatus.PUBLISHED, hr);
	}

	@Test
	public void testFetchJobsByHr() {

		List<Job> expectedJobs = Arrays.asList(job1, job2);

		when(jobRepository.findByHrId(1)).thenReturn(expectedJobs);

		List<Job> actualJobs = jobService.fetchJobsByHr(1);

		assertEquals(expectedJobs.size(), actualJobs.size());

		verify(jobRepository, times(1)).findByHrId(1);
	}
}
