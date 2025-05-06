package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.service.CertificateService;
import com.spring.rest_api.career_crafter.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificates")
//@CrossOrigin(origins = {"http://localhost:5173"})
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/issue/{enrollmentId}")
    public Certificate issueCertificate(@PathVariable int enrollmentId) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        return certificateService.issueCertificate(enrollment);
    }

    @GetMapping("/byEnrollment/{enrollmentId}")
    public Certificate getCertificateByEnrollment(@PathVariable int enrollmentId) {
        return certificateService.getCertificateByEnrollmentId(enrollmentId);
    }
}
