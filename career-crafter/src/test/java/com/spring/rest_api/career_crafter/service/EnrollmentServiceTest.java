package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository; // Mocking the EnrollmentRepository to isolate tests from the database

    @InjectMocks
    private EnrollmentService enrollmentService; // Injecting the mocked repository into the EnrollmentService

    private Enrollment enrollment1;
    private Enrollment enrollment2;

    private JobSeeker jobSeeker;
    private Course course;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializing mocks

        // Creating mock JobSeeker and Course objects for the test
        jobSeeker = new JobSeeker();
        jobSeeker.setId(1);
        jobSeeker.setName("John Doe");

        course = new Course();
        course.setId(1);
        course.setTitle("Java Programming");

        // Creating mock Enrollment objects with different states for testing
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

    // Test for paginated retrieval of enrollments
    @Test
    public void testGetAllEnrollmentsPaginated() {
        // Given: A list of enrollments and a Pageable object
        List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
        Pageable pageable = PageRequest.of(0, 10);

        // Mock the repository to return a PageImpl (pageable result) containing the enrollments
        when(enrollmentRepository.findAll(pageable)).thenReturn(new PageImpl<>(enrollments));

        // When: Fetching the paginated enrollments
        Page<Enrollment> result = enrollmentService.getAllEnrollmentsPaginated(pageable);

        // Then: Verifying the result contains the expected number of enrollments and the repository interaction
        assertEquals(2, result.getContent().size()); // Checking the content size of the page
        verify(enrollmentRepository, times(1)).findAll(pageable); // Ensuring the repository method is called once
    }

    // Test for fetching enrollments based on course category
    @Test
    public void testGetEnrollmentsByCategory() {
        // Given: A category name and a list of enrollments in that category
        String categoryName = "Programming";
        List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
        when(enrollmentRepository.findByCourseCategory(categoryName)).thenReturn(enrollments);

        // When: Fetching the enrollments for the specified category
        List<Enrollment> result = enrollmentService.getEnrollmentsByCategory(categoryName);

        // Then: Verifying the correct number of enrollments are returned and the repository interaction
        assertEquals(2, result.size());
        verify(enrollmentRepository, times(1)).findByCourseCategory(categoryName);
    }

    // Test for fetching enrollments based on job seeker's name
    @Test
    public void testGetEnrollmentsByJobSeekerName() {
        // Given: A job seeker's name and a list of their enrollments
        String jobSeekerName = "John Doe";
        List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
        when(enrollmentRepository.findByJobSeekerName(jobSeekerName)).thenReturn(enrollments);

        // When: Fetching the enrollments for the specified job seeker
        List<Enrollment> result = enrollmentService.getEnrollmentsByJobSeekerName(jobSeekerName);

        // Then: Verifying the correct number of enrollments are returned and the repository interaction
        assertEquals(2, result.size());
        verify(enrollmentRepository, times(1)).findByJobSeekerName(jobSeekerName);
    }

    // Test for fetching the total count of enrollments
    @Test
    public void testGetTotalEnrollments() {
        // Given: A total count of enrollments
        long totalEnrollments = 100;
        when(enrollmentRepository.count()).thenReturn(totalEnrollments);

        // When: Fetching the total number of enrollments
        long result = enrollmentService.getTotalEnrollments();

        // Then: Verifying that the total count returned is correct
        assertEquals(totalEnrollments, result);
        verify(enrollmentRepository, times(1)).count();
    }

    // Test for fetching the count of completed enrollments
    @Test
    public void testGetCompletedEnrollments() {
        // Given: A count of completed enrollments
        long completedEnrollments = 50;
        when(enrollmentRepository.countByCompleted(true)).thenReturn(completedEnrollments);

        // When: Fetching the count of completed enrollments
        long result = enrollmentService.getCompletedEnrollments();

        // Then: Verifying that the correct count of completed enrollments is returned
        assertEquals(completedEnrollments, result);
        verify(enrollmentRepository, times(1)).countByCompleted(true);
    }

    // Test for fetching an enrollment by its ID
    @Test
    public void testGetEnrollmentById() {
        // Given: An enrollment ID and a mock enrollment object
        int enrollmentId = 1;
        when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.of(enrollment1));

        // When: Fetching the enrollment by its ID
        Enrollment result = enrollmentService.getEnrollmentById(enrollmentId);

        // Then: Verifying the result is not null, and the ID matches
        assertNotNull(result);
        assertEquals(enrollmentId, result.getId());
        verify(enrollmentRepository, times(1)).findById(enrollmentId); // Ensuring the repository method is called once
    }

    // Test for handling the case where the enrollment ID is not found
    @Test
    public void testGetEnrollmentById_NotFound() {
        // Given: A non-existent enrollment ID
        int enrollmentId = 999;
        when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.empty());

        // When & Then: Verifying that the service throws a RuntimeException when the enrollment is not found
        assertThrows(RuntimeException.class, () -> enrollmentService.getEnrollmentById(enrollmentId));
    }
}
