package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.CourseReview;
import com.spring.rest_api.career_crafter.repository.CourseReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CourseReviewServiceTest {

    @Mock
    private CourseReviewRepository courseReviewRepository;

    @InjectMocks
    private CourseReviewService courseReviewService;

    private CourseReview review1;
    private CourseReview review2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        review1 = new CourseReview();
        review1.setId(1);
        review1.setRating(4);
        review1.setReviewText("Great course!");
        review1.setDatePosted(LocalDate.now());

        review2 = new CourseReview();
        review2.setId(2);
        review2.setRating(5);
        review2.setReviewText("Excellent content!");
        review2.setDatePosted(LocalDate.now());
    }

   
    @Test
    public void testGetAllReviewsPaginated() {
        // Given
        List<CourseReview> reviews = Arrays.asList(review1, review2);
        Pageable pageable = PageRequest.of(0, 10);
        // Mock the repository to return a PageImpl with the reviews list
        when(courseReviewRepository.findAll(pageable)).thenReturn(new PageImpl<>(reviews));

        // When
        List<CourseReview> result = courseReviewService.getAllReviewsPaginated(pageable);

        // Then
        assertEquals(2, result.size());
        verify(courseReviewRepository, times(1)).findAll(pageable);
    }


    @Test
    public void testGetReviewsByCourseId() {
        // Given
        int courseId = 1;
        List<CourseReview> reviews = Arrays.asList(review1, review2);
        when(courseReviewRepository.findByCourseId(courseId)).thenReturn(reviews);

        // When
        List<CourseReview> result = courseReviewService.getReviewsByCourseId(courseId);

        // Then
        assertEquals(2, result.size());
        verify(courseReviewRepository, times(1)).findByCourseId(courseId);
    }

    @Test
    public void testGetReviewsByCourseId_InvalidCourseId() {
        // Given
        int courseId = -1;  // Invalid course ID

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> courseReviewService.getReviewsByCourseId(courseId));
    }
}
