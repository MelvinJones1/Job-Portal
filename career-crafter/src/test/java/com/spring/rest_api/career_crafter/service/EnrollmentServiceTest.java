package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentServiceTest {

	@Mock
	private EnrollmentRepository enrollmentRepository;

	@InjectMocks
	private EnrollmentService enrollmentService;

	private Enrollment enrollment1;
	private Enrollment enrollment2;

	private JobSeeker jobSeeker;
	private Course course;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Creating mock JobSeeker and Course objects
		jobSeeker = new JobSeeker();
		jobSeeker.setId(1);
		jobSeeker.setName("John Doe");

		course = new Course();
		course.setId(1);
		course.setTitle("Java Programming");

		// Creating mock Enrollment objects
		enrollment1 = new Enrollment();
		enrollment1.setId(1);
		enrollment1.setJobSeeker(jobSeeker);
		enrollment1.setCourse(course);
		enrollment1.setCompleted(true);
		enrollment1.setEnrolledDate(LocalDate.now());

		enrollment2 = new Enrollment();
		enrollment2.setId(2);
		enrollment2.setJobSeeker(jobSeeker);
		enrollment2.setCourse(course);
		enrollment2.setCompleted(false);
		enrollment2.setEnrolledDate(LocalDate.now());
	}

	@Test
	public void testGetAllEnrollmentsPaginated() {
		// Given
		List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
		Pageable pageable = PageRequest.of(0, 10);

		// Mock the repository to return a PageImpl with the enrollments list
		when(enrollmentRepository.findAll(pageable)).thenReturn(new PageImpl<>(enrollments));

		// When
		Page<Enrollment> result = enrollmentService.getAllEnrollmentsPaginated(pageable);

		// Then
		assertEquals(2, result.getContent().size()); // Checking the content size of the page
		verify(enrollmentRepository, times(1)).findAll(pageable);
	}

	@Test
	public void testGetEnrollmentsByCategory() {
		// Given
		String categoryName = "Programming";
		List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
		when(enrollmentRepository.findByCourseCategory(categoryName)).thenReturn(enrollments);

		// When
		List<Enrollment> result = enrollmentService.getEnrollmentsByCategory(categoryName);

		// Then
		assertEquals(2, result.size());
		verify(enrollmentRepository, times(1)).findByCourseCategory(categoryName);
	}

	@Test
	public void testGetEnrollmentsByJobSeekerName() {
		// Given
		String jobSeekerName = "John Doe";
		List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
		when(enrollmentRepository.findByJobSeekerName(jobSeekerName)).thenReturn(enrollments);

		// When
		List<Enrollment> result = enrollmentService.getEnrollmentsByJobSeekerName(jobSeekerName);

		// Then
		assertEquals(2, result.size());
		verify(enrollmentRepository, times(1)).findByJobSeekerName(jobSeekerName);
	}

	@Test
	public void testGetTotalEnrollments() {
		// Given
		long totalEnrollments = 100;
		when(enrollmentRepository.count()).thenReturn(totalEnrollments);

		// When
		long result = enrollmentService.getTotalEnrollments();

		// Then
		assertEquals(totalEnrollments, result);
		verify(enrollmentRepository, times(1)).count();
	}

	@Test
	public void testGetCompletedEnrollments() {
		// Given
		long completedEnrollments = 50;
		when(enrollmentRepository.countByCompleted(true)).thenReturn(completedEnrollments);

		// When
		long result = enrollmentService.getCompletedEnrollments();

		// Then
		assertEquals(completedEnrollments, result);
		verify(enrollmentRepository, times(1)).countByCompleted(true);
	}

	@Test
	public void testGetEnrollmentById() {
		// Given
		int enrollmentId = 1;
		when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.of(enrollment1));

		// When
		Enrollment result = enrollmentService.getEnrollmentById(enrollmentId);

		// Then
		assertNotNull(result);
		assertEquals(enrollmentId, result.getId());
		verify(enrollmentRepository, times(1)).findById(enrollmentId);
	}

	@Test
	public void testGetEnrollmentById_NotFound() {
		// Given
		int enrollmentId = 999;
		when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.empty());

		// When & Then
		assertThrows(RuntimeException.class, () -> enrollmentService.getEnrollmentById(enrollmentId));
	}
}
