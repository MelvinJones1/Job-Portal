package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private Enrollment enrollment1;
    private Enrollment enrollment2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        enrollment1 = new Enrollment();
        enrollment1.setId(1);
        enrollment1.setProgress(50);
        enrollment1.setCompleted(false);

        enrollment2 = new Enrollment();
        enrollment2.setId(2);
        enrollment2.setProgress(100);
        enrollment2.setCompleted(true);
    }

    @Test
    public void testGetAllEnrollmentsPaginated() {
        // Given
        List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
        Pageable pageable = PageRequest.of(0, 10);
        when(enrollmentRepository.findAll(pageable)).thenReturn(new PageImpl<>(enrollments));

        // When
        List<Enrollment> result = enrollmentService.getAllEnrollmentsPaginated(pageable);

        // Then
        assertEquals(2, result.size());
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
        String name = "John Doe";
        List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);
        when(enrollmentRepository.findByJobSeekerName(name)).thenReturn(enrollments);

        // When
        List<Enrollment> result = enrollmentService.getEnrollmentsByJobSeekerName(name);

        // Then
        assertEquals(2, result.size());
        verify(enrollmentRepository, times(1)).findByJobSeekerName(name);
    }

    @Test
    public void testGetTotalEnrollments() {
        // Given
        when(enrollmentRepository.count()).thenReturn(5L);

        // When
        long result = enrollmentService.getTotalEnrollments();

        // Then
        assertEquals(5L, result);
        verify(enrollmentRepository, times(1)).count();
    }

    @Test
    public void testGetCompletedEnrollments() {
        // Given
        when(enrollmentRepository.countByCompleted(true)).thenReturn(3L);

        // When
        long result = enrollmentService.getCompletedEnrollments();

        // Then
        assertEquals(3L, result);
        verify(enrollmentRepository, times(1)).countByCompleted(true);
    }
}
