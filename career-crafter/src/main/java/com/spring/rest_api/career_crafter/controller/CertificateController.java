
package com.spring.rest_api.career_crafter.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.service.CertificateService;
@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    Logger logger = LoggerFactory.getLogger("CertificateController");

    // Add certificate to the course content based on ID
    @PostMapping("/add/{contentId}")
    public Certificate addCertificate(@PathVariable int contentId, @RequestBody Certificate certificate) {
        logger.info("Adding certificate for content ID: {}", contentId);
        return certificateService.addCertificateToCourseContent(contentId, certificate);
    }

    // Get paginated certificates by content ID
    @GetMapping("/getPaginated/{contentId}")
    public List<Certificate> getCertificatesByContent(@PathVariable int contentId,
                                                       @RequestParam int page,
                                                       @RequestParam int size) {
        logger.info("Fetching certificates for content ID: {} with pagination.", contentId);
        Pageable pageable = PageRequest.of(page, size);
        return certificateService.getCertificatesByContent(contentId, pageable);
    }

    // Update a certificate by ID
    @PutMapping("/update/{certificateId}")
    public Certificate updateCertificate(@PathVariable int certificateId, @RequestBody Certificate updatedData) {
        logger.info("Updating certificate with ID: {}", certificateId);
        return certificateService.updateCertificate(certificateId, updatedData);
    }

    // Delete a certificate by ID
    @DeleteMapping("/{certificateId}")
    public void deleteCertificate(@PathVariable int certificateId) {
        logger.info("Deleting certificate with ID: {}", certificateId);
        certificateService.deleteCertificate(certificateId);
    }

    // Download certificate by ID
    @GetMapping("/download/{certificateId}")
    public String downloadCertificate(@PathVariable int certificateId) {
        logger.info("Downloading certificate with ID: {}", certificateId);
        Certificate certificate = certificateService.getCertificateById(certificateId);
        return "Certificate file is available at: " + certificate.getCertificateUrl();
    }
}