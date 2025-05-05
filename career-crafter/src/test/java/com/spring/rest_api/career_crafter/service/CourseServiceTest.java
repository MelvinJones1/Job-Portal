package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseModuleRepository courseModuleRepository;

    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        course = new Course();
        course.setId(1);
        course.setTitle("Java");
        course.setDescription("Java course");
        course.setCategory("Programming");
    }

    @Test
    void testAddCourse_ShouldSaveCourse() {
        when(courseRepository.save(course)).thenReturn(course);

        Course saved = courseService.addCourse(course);

        assertEquals("Java", saved.getTitle());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testGetAllCourses_ShouldReturnPageOfCourses() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Course> mockPage = new PageImpl<>(List.of(course));
        when(courseRepository.findAll(pageable)).thenReturn(mockPage);

        Page<Course> result = courseService.getAllCourses(pageable);

        assertEquals(1, result.getTotalElements());
        verify(courseRepository).findAll(pageable);
    }

    @Test
    void testGetCourseById_ValidId_ShouldReturnCourse() throws InvalidIDException {
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        Course found = courseService.getCourseById(1);

        assertEquals(1, found.getId());
        verify(courseRepository).findById(1);
    }

    @Test
    void testGetCourseById_InvalidId_ShouldThrowException() {
        when(courseRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> courseService.getCourseById(2));
    }

    @Test
    void testUpdateCourse_ValidId_ShouldUpdateFields() throws InvalidIDException {
        Course updated = new Course();
        updated.setTitle("Advanced Java");
        updated.setDescription("Updated desc");
        updated.setCategory("Backend");

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course result = courseService.updateCourse(1, updated);

        assertEquals("Advanced Java", result.getTitle());
        assertEquals("Updated desc", result.getDescription());
        assertEquals("Backend", result.getCategory());
        verify(courseRepository).save(course);
    }

    @Test
    void testUpdateCourse_InvalidId_ShouldThrowException() {
        Course updated = new Course();
        when(courseRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> courseService.updateCourse(99, updated));
    }

    @Test
    void testDeleteCourse_ValidId_ShouldDeleteCourseAndModules() throws InvalidIDException {
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        courseService.deleteCourse(1);

        verify(courseModuleRepository).deleteByCourseId(1);
        verify(courseRepository).delete(course);
    }

    @Test
    void testDeleteCourse_InvalidId_ShouldThrowException() {
        when(courseRepository.findById(42)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> courseService.deleteCourse(42));
        verify(courseModuleRepository, never()).deleteByCourseId(anyInt());
    }

    @Test
    void testGetCourseCount_ShouldReturnCorrectCount() {
        when(courseRepository.count()).thenReturn(5L);

        long count = courseService.getCourseCount();

        assertEquals(5L, count);
        verify(courseRepository).count();
    }

    @Test
    void testSearchCoursesByTitle_ShouldReturnMatchingCourses() {
        when(courseRepository.findByTitleContaining("Java")).thenReturn(List.of(course));

        List<Course> results = courseService.searchCoursesByTitle("Java");

        assertEquals(1, results.size());
        verify(courseRepository).findByTitleContaining("Java");
    }
}
