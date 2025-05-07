package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.CourseReview;
import com.spring.rest_api.career_crafter.repository.CourseReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Automatically initializes mocks
public class CourseReviewServiceTest {

    // Mock the CourseReviewRepository to simulate repository behavior
    @Mock
    private CourseReviewRepository courseReviewRepository;

    // Inject mocked dependencies into the CourseReviewService
    @InjectMocks
    private CourseReviewService courseReviewService;

    // Sample review objects for testing
    private CourseReview review1;
    private CourseReview review2;

    // Initialize the test data before each test
    @BeforeEach
    public void setUp() {
        // Create and set up review1
        review1 = new CourseReview();
        review1.setId(1);
        review1.setRating(4);
        review1.setReviewText("Great course!");
        review1.setDatePosted(LocalDate.now());

        // Create and set up review2
        review2 = new CourseReview();
        review2.setId(2);
        review2.setRating(5);
        review2.setReviewText("Excellent content!");
        review2.setDatePosted(LocalDate.now());
    }

    // Test for paginated retrieval of course reviews
    @Test
    public void testGetAllReviewsPaginated() {
        // Given: A list of reviews and pageable object for pagination
        List<CourseReview> reviews = Arrays.asList(review1, review2);
        Pageable pageable = PageRequest.of(0, 10);

        // Mock the repository to return a paginated list of reviews
        when(courseReviewRepository.findAll(pageable)).thenReturn(new PageImpl<>(reviews));

        // When: Call the service method
        Page<CourseReview> result = courseReviewService.getAllReviewsPaginated(pageable);

        // Then: Assert the results match expected size and interactions
        assertEquals(2, result.getContent().size()); // Checking the content size of the page
        verify(courseReviewRepository, times(1)).findAll(pageable); // Verifying the repository method was called once
    }

    // Test for retrieving reviews by a specific course ID
    @Test
    public void testGetReviewsByCourseId() {
        // Given: A courseId and a list of reviews for that course
        int courseId = 1;
        List<CourseReview> reviews = Arrays.asList(review1, review2);

        // Mock the repository to return the reviews for the courseId
        when(courseReviewRepository.findByCourseId(courseId)).thenReturn(reviews);

        // When: Call the service method
        List<CourseReview> result = courseReviewService.getReviewsByCourseId(courseId);

        // Then: Assert that the result matches the expected list of reviews
        assertEquals(2, result.size());
        verify(courseReviewRepository, times(1)).findByCourseId(courseId); // Verify repository method was called once
    }

    // Test for handling invalid course ID when retrieving reviews
    @Test
    public void testGetReviewsByCourseId_InvalidCourseId() {
        // Given: An invalid courseId
        int invalidCourseId = -1; // Invalid course ID

        // When & Then: Assert that calling the service method throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> courseReviewService.getReviewsByCourseId(invalidCourseId));
    }

    // Test for handling an empty list of reviews for a course
    @Test
    public void testGetReviewsByCourseId_EmptyList() {
        // Given: A courseId and an empty list of reviews
        int courseId = 1;
        List<CourseReview> emptyReviews = Arrays.asList(); // No reviews for the course

        // Mock the repository to return an empty list of reviews
        when(courseReviewRepository.findByCourseId(courseId)).thenReturn(emptyReviews);

        // When: Call the service method
        List<CourseReview> result = courseReviewService.getReviewsByCourseId(courseId);

        // Then: Assert that the result is an empty list
        assertTrue(result.isEmpty(), "The review list should be empty.");
        verify(courseReviewRepository, times(1)).findByCourseId(courseId); // Verify repository method was called once
    }
}
