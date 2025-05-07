package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.service.CertificateService;
import com.spring.rest_api.career_crafter.service.EnrollmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger("CertificateController");

    /**
     * Issues a certificate for the given enrollment ID
     * Retrieves the corresponding Enrollment entity 
     * the certificate generation to the CertificateService
     * 
     * parameter enrollmentId the ID of the enrollment for which to issue a certificate
     * return the newly created Certificate
     */
    @PostMapping("/issue/{enrollmentId}")
    public Certificate issueCertificate(@PathVariable int enrollmentId) {
    	logger.info("adding certificate to enrollement",enrollmentId);
        Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        
        return certificateService.issueCertificate(enrollment);
    }

    /**
     * Retrieves a certificate based on the associated enrollment ID.
     * 
     * @param enrollmentId the ID of the enrollment
     * the Certificate associated with the specified enrollment
     */
    @GetMapping("/byEnrollment/{enrollmentId}")
    public Certificate getCertificateByEnrollment(@PathVariable int enrollmentId) {
    	logger.info("getting certificate by id",enrollmentId);
        return certificateService.getCertificateByEnrollmentId(enrollmentId);
    }
}
