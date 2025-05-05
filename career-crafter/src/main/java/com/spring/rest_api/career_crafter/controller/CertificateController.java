package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.service.CertificateService;
import com.spring.rest_api.career_crafter.service.AssignmentService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private AssignmentService assignmentService;

    // Add a new Certificate for a given Assignment
    @PostMapping("/{assignmentId}")
    public Certificate addCertificate(@PathVariable int assignmentId, @RequestBody Certificate certificate) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(assignmentId);

        if (assignment == null) {
            return null;  // Return null if the assignment is not found
        }

        certificate.setId(0);  // Ensure the ID is reset for a new certificate
        Certificate savedCertificate = certificateService.saveCertificate(certificate); // Save the certificate

        // Link the new certificate to the assignment
        assignment.setCertificate(savedCertificate);
        assignmentService.addAssignment(assignment);  // Update the assignment with the new certificate

        return savedCertificate;
    }

    // Update an existing Certificate
    @PutMapping("/{id}")
    public Certificate updateCertificate(@PathVariable int id, @RequestBody Certificate certificateDetails) {
        Certificate existingCertificate = certificateService.getCertificateById(id);

        if (existingCertificate == null) {
            return null;  // Return null if the certificate is not found
        }

        // Update fields of the existing certificate
        existingCertificate.setCertificateUrl(certificateDetails.getCertificateUrl());
        return certificateService.saveCertificate(existingCertificate); // Save updated certificate
    }

    // Delete a Certificate
    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable int id) {
        Certificate existingCertificate = certificateService.getCertificateById(id);

        if (existingCertificate != null) {
            certificateService.deleteCertificate(id); // Delete certificate if found
        }
    }
}
