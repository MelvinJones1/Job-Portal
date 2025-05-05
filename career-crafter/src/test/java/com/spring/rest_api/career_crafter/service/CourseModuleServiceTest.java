
package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseModuleServiceTest {

    @InjectMocks
    private CourseModuleService courseModuleService;

    @Mock
    private CourseRepository courseRepo;

    @Mock
    private CourseModuleRepository moduleRepo;

    private Course course;
    private CourseModule module;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new Course();
        course.setId(1);

        module = new CourseModule();
        module.setId(1);
        module.setCourse(course);
        module.setTitle("Intro to Java");
        module.setUrl("/uploads/sample.pdf");
    }

    @Test
    void testAddModuleSuccess() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "sample.pdf", "application/pdf", "dummy content".getBytes());
        MultipartFile[] files = new MultipartFile[]{file};

        when(courseRepo.findById(1)).thenReturn(Optional.of(course));
        when(moduleRepo.save(any(CourseModule.class))).thenReturn(module);

        String result = courseModuleService.addModule(1, "Intro to Java", files);
        assertEquals("Module created", result);

        verify(courseRepo).findById(1);
        verify(moduleRepo).save(any(CourseModule.class));
    }

    @Test
    void testAddModuleCourseNotFound() {
        when(courseRepo.findById(1)).thenReturn(Optional.empty());

        MultipartFile[] files = new MultipartFile[]{};
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            courseModuleService.addModule(1, "Test Module", files);
        });

        assertEquals("Course not found", ex.getMessage());
    }

    @Test
    void testGetModuleSuccess() {
        when(moduleRepo.findById(1)).thenReturn(Optional.of(module));

        CourseModule found = courseModuleService.getModule(1);
        assertEquals("Intro to Java", found.getTitle());
    }

    @Test
    void testGetModuleNotFound() {
        when(moduleRepo.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseModuleService.getModule(1);
        });
        assertEquals("Module not found", exception.getMessage());
    }

    @Test
    void testUpdateModuleWithTitleOnly() throws IOException {
        when(moduleRepo.findById(1)).thenReturn(Optional.of(module));
        when(moduleRepo.save(any(CourseModule.class))).thenReturn(module);

        CourseModule updated = courseModuleService.updateModule(1, "Updated Title", null);

        assertEquals("Updated Title", updated.getTitle());
        verify(moduleRepo).save(any(CourseModule.class));
    }


    @Test
    void testGetModulesByCourseSuccess() {
        when(courseRepo.findById(1)).thenReturn(Optional.of(course));
        when(moduleRepo.findByCourse(course)).thenReturn(Collections.singletonList(module));

        List<CourseModule> modules = courseModuleService.getModulesByCourse(1);
        assertEquals(1, modules.size());
        assertEquals("Intro to Java", modules.get(0).getTitle());
    }

    @Test
    void testGetModulesByCourseNotFound() {
        when(courseRepo.findById(1)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> {
            courseModuleService.getModulesByCourse(1);
        });

        assertEquals("Course not found", ex.getMessage());
    }

    @Test
    void testDeleteModuleSuccess() {
        when(moduleRepo.findById(1)).thenReturn(Optional.of(module));

        String result = courseModuleService.deleteModule(1);
        assertEquals("Module deleted successfully", result);

        verify(moduleRepo).delete(module);
    }

    @Test
    void testDeleteModuleNotFound() {
        when(moduleRepo.findById(1)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> {
            courseModuleService.deleteModule(1);
        });

        assertEquals("Module not found", ex.getMessage());
    }
}
