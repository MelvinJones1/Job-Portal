package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.*;
import com.spring.rest_api.career_crafter.repository.*;

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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    // Mocking repositories to simulate database behavior
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseModuleRepository courseModuleRepository;
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private CertificateRepository certificateRepository;
    @Mock
    private JobSeeekerCertificatesRepository jobSeeekerCertificatesRepository;
    @Mock
    private CourseReviewRepository courseReviewRepository;

    // Injecting mocks into the service under test
    @InjectMocks
    private CourseService courseService;

    private Course course;

    // Setup method to initialize test data
    @BeforeEach
    void setup() {
        course = new Course();
        course.setId(1);
        course.setTitle("Java Basics");
        course.setDescription("Intro to Java");
        course.setCategory("Programming");
    }

    // Test for adding a course
    @Test
    void testAddCourse() {
        // Stubbing the save method to return the course object
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Calling the method to be tested
        Course saved = courseService.addCourse(course);

        // Asserting that the course is not null and has the expected title
        assertNotNull(saved);
        assertEquals("Java Basics", saved.getTitle());

        // Verifying that the save method was called exactly once
        verify(courseRepository, times(1)).save(course);
    }

    // Test for getting all courses with pagination
    @Test
    void testGetAllCourses() {
        List<Course> courseList = List.of(course);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Course> coursePage = new PageImpl<>(courseList);

        // Stubbing the findAll method to return a Page containing the course
        when(courseRepository.findAll(pageable)).thenReturn(coursePage);

        // Calling the method to get all courses
        Page<Course> result = courseService.getAllCourses(pageable);

        // Asserting the total elements returned and verifying the repository interaction
        assertEquals(1, result.getTotalElements());
        verify(courseRepository).findAll(pageable);
    }

    // Test for successfully getting a course by ID
    @Test
    void testGetCourseById_Success() throws InvalidIDException {
        // Stubbing the findById method to return the course
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        // Calling the method to get the course by ID
        Course result = courseService.getCourseById(1);

        // Asserting that the course is returned and has the expected title
        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
    }

    // Test for handling course not found scenario
    @Test
    void testGetCourseById_NotFound() {
        // Stubbing the findById method to return empty (course not found)
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        // Asserting that InvalidIDException is thrown
        assertThrows(InvalidIDException.class, () -> courseService.getCourseById(1));
    }

    // Test for successfully updating a course
    @Test
    void testUpdateCourse_Success() throws InvalidIDException {
        // Creating an updated course object with new details
        Course updatedCourse = new Course();
        updatedCourse.setTitle("Updated Java");
        updatedCourse.setDescription("Updated Desc");
        updatedCourse.setCategory("Updated Category");

        // Stubbing the findById method to return the existing course
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        // Stubbing the save method to return the updated course
        when(courseRepository.save(any(Course.class))).thenReturn(updatedCourse);

        // Calling the update method
        Course result = courseService.updateCourse(1, updatedCourse);

        // Asserting that the course is updated successfully
        assertEquals("Updated Java", result.getTitle());
        // Verifying that save was called on the course repository
        verify(courseRepository).save(course);
    }

    // Test for successfully deleting a course
    @Test
    void testDeleteCourse_Success() throws InvalidIDException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(101);

        Certificate certificate = new Certificate();
        certificate.setId(201);
        certificate.setEnrollment(enrollment);

        List<Enrollment> enrollments = List.of(enrollment);
        List<JobSeekerCertificates> links = new ArrayList<>();

        // Stubbing methods to return necessary data for deletion
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByCourseId(1)).thenReturn(enrollments);
        when(certificateRepository.findByEnrollmentId(101)).thenReturn(certificate);
        when(jobSeeekerCertificatesRepository.findByCertificates(certificate)).thenReturn(links);

        // Calling the delete method
        courseService.deleteCourse(1);

        // Verifying that all dependent entities are deleted
        verify(jobSeeekerCertificatesRepository).deleteAll(links);
        verify(certificateRepository).delete(certificate);
        verify(enrollmentRepository).deleteAll(enrollments);
        verify(courseModuleRepository).deleteByCourseId(1);
        verify(courseReviewRepository).deleteByCourseId(1);
        verify(courseRepository).delete(course);
    }

    // Test for getting the count of all courses
    @Test
    void testGetCourseCount() {
        // Stubbing the count method to return 5
        when(courseRepository.count()).thenReturn(5L);

        // Calling the method to get course count
        long count = courseService.getCourseCount();

        // Asserting that the count matches the expected value
        assertEquals(5L, count);
        // Verifying that count method was called on the repository
        verify(courseRepository).count();
    }

    // Test for searching courses by title
    @Test
    void testSearchCoursesByTitle() {
        // Stubbing the findByTitleContaining method to return a list of courses matching the title
        when(courseRepository.findByTitleContaining("Java")).thenReturn(List.of(course));

        // Calling the method to search for courses
        List<Course> results = courseService.searchCoursesByTitle("Java");

        // Asserting that the result list has the expected course
        assertEquals(1, results.size());
        assertEquals("Java Basics", results.get(0).getTitle());
    }
}
