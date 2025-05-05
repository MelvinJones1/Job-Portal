package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class CourseModuleService {

    private static final String UPLOAD_DIR = "C:/Users/ragip/OneDrive/Documents/JAVA FULL STACK HEX/Job-Portal/uploads";

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private CourseModuleRepository moduleRepo;

    public String addModule(int courseId, String title, MultipartFile[] files) throws IOException {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        StringBuilder urls = new StringBuilder();
        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            urls.append("/uploads/").append(fileName).append(",");
        }

        CourseModule module = new CourseModule();
        module.setCourse(course);
        module.setTitle(title);
        module.setUrl(urls.toString().replaceAll(",$", ""));

        moduleRepo.save(module);
        return "Module created";
    }

    public CourseModule getModule(int id) {
        return moduleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));
    }

    public CourseModule updateModule(int id, String title, MultipartFile[] files) throws IOException {
        CourseModule module = moduleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        if (title != null && !title.trim().isEmpty()) {
            module.setTitle(title);
        }

        if (files != null && files.length > 0) {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            StringBuilder urls = new StringBuilder();
            for (MultipartFile file : files) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);
                urls.append("/uploads/").append(fileName).append(",");
            }
            module.setUrl(urls.toString().replaceAll(",$", ""));
        }

        return moduleRepo.save(module);
    }

    public List<CourseModule> getModulesByCourse(int courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return moduleRepo.findByCourse(course);
    }

    public String deleteModule(int id) {
        CourseModule module = moduleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        moduleRepo.delete(module);
        return "Module deleted successfully";
    }
    public CourseModule getCourseModuleById(int courseId) {
        return moduleRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Module not found with ID: " + courseId));
    }
}
