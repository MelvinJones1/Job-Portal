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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

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

    @InjectMocks
    private CourseService courseService;

    private Course course;

    @BeforeEach
    void setup() {
        course = new Course();
        course.setId(1);
        course.setTitle("Java Basics");
        course.setDescription("Intro to Java");
        course.setCategory("Programming");
    }

    @Test
    void testAddCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course saved = courseService.addCourse(course);

        assertNotNull(saved);
        assertEquals("Java Basics", saved.getTitle());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courseList = List.of(course);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Course> coursePage = new PageImpl<>(courseList);

        when(courseRepository.findAll(pageable)).thenReturn(coursePage);

        Page<Course> result = courseService.getAllCourses(pageable);

        assertEquals(1, result.getTotalElements());
        verify(courseRepository).findAll(pageable);
    }

    @Test
    void testGetCourseById_Success() throws InvalidIDException {
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        Course result = courseService.getCourseById(1);

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
    }

    @Test
    void testGetCourseById_NotFound() {
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> courseService.getCourseById(1));
    }

    @Test
    void testUpdateCourse_Success() throws InvalidIDException {
        Course updatedCourse = new Course();
        updatedCourse.setTitle("Updated Java");
        updatedCourse.setDescription("Updated Desc");
        updatedCourse.setCategory("Updated Category");

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(updatedCourse);

        Course result = courseService.updateCourse(1, updatedCourse);

        assertEquals("Updated Java", result.getTitle());
        verify(courseRepository).save(course);
    }

    @Test
    void testDeleteCourse_Success() throws InvalidIDException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(101);

        Certificate certificate = new Certificate();
        certificate.setId(201);
        certificate.setEnrollment(enrollment);

        List<Enrollment> enrollments = List.of(enrollment);
        List<JobSeekerCertificates> links = new ArrayList<>();

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByCourseId(1)).thenReturn(enrollments);
        when(certificateRepository.findByEnrollmentId(101)).thenReturn(certificate);
        when(jobSeeekerCertificatesRepository.findByCertificates(certificate)).thenReturn(links);

        courseService.deleteCourse(1);

        verify(jobSeeekerCertificatesRepository).deleteAll(links);
        verify(certificateRepository).delete(certificate);
        verify(enrollmentRepository).deleteAll(enrollments);
        verify(courseModuleRepository).deleteByCourseId(1);
        verify(courseReviewRepository).deleteByCourseId(1);
        verify(courseRepository).delete(course);
    }

    @Test
    void testGetCourseCount() {
        when(courseRepository.count()).thenReturn(5L);

        long count = courseService.getCourseCount();

        assertEquals(5L, count);
        verify(courseRepository).count();
    }

    @Test
    void testSearchCoursesByTitle() {
        when(courseRepository.findByTitleContaining("Java")).thenReturn(List.of(course));

        List<Course> results = courseService.searchCoursesByTitle("Java");

        assertEquals(1, results.size());
        assertEquals("Java Basics", results.get(0).getTitle());
    }
}
