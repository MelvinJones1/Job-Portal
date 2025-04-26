
package com.spring.rest_api.career_crafter.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.model.CourseContent;
import com.spring.rest_api.career_crafter.repository.CertificateRepository;
import com.spring.rest_api.career_crafter.repository.CourseContentRepository;
@Service
public class CertificateService {

    @Autowired
    private CourseContentRepository courseContentRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    // Add certificate to the course content
    public Certificate addCertificateToCourseContent(int courseContentId, Certificate certificate) {
        CourseContent content = courseContentRepository.findById(courseContentId)
                .orElseThrow(() -> new RuntimeException("CourseContent not found"));
        certificate.setCourseContent(content);
        return certificateRepository.save(certificate);
    }

    // Get certificates by content ID (with pagination)
    public List<Certificate> getCertificatesByContent(int contentId, Pageable pageable) {
        return certificateRepository.findByCourseContentId(contentId, pageable).getContent();
    }

    // Update certificate
    public Certificate updateCertificate(int certificateId, Certificate updatedData) {
        Certificate existing = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        existing.setCertificateUrl(updatedData.getCertificateUrl());
        return certificateRepository.save(existing);
    }

    // Delete certificate
    public void deleteCertificate(int certificateId) {
        Certificate existing = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        certificateRepository.delete(existing);
    }

    // Get certificate by ID
    public Certificate getCertificateById(int certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }
}