
package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/certificta")
public class CertificateController {

	 @Autowired
	 private CertificateService certificateService;
	 //added loggers
	 Logger logger =  LoggerFactory.getLogger("CertificateController"); 
	 //adds the certificate to the content based on id
	@PostMapping("/add/{contentId}")
    public Certificate addCertificate(@PathVariable int contentId, @RequestBody Certificate certificate) {
		logger.info("adding the certificate for the content id"+contentId);
		 return certificateService.addCertificateToCourseContent(contentId, certificate);
	}
	//get the certoficates by content id
	@GetMapping("/getPaginated/{contentId}")
	public ResponseEntity<?> getCertificatesByContent(@PathVariable int contentId,
	                                                           @RequestParam int page,
	                                                           @RequestParam int size) {
	    logger.info("Fetching certificates by content ID with pagination");
	    Pageable pageable = PageRequest.of(page, size);
	    try {
	        List<Certificate> certificates = certificateService.getCertificatesByContent(contentId, pageable);
	        return ResponseEntity.ok(certificates);
	    } catch (Exception e) {
	        logger.error("Error fetching certificates: {}", e.getMessage());
	        return ResponseEntity.status(500).body("Failed to fetch certificates for content ID: " + contentId);
	    }
	}
//update the certifocat by id
    @PutMapping("/update/{certificateId}")
    public Certificate updateCertificate(@PathVariable int certificateId, @RequestBody Certificate updatedData) {
       logger.info("updating the certificate");
       return certificateService.updateCertificate(certificateId, updatedData);
    }

    //delete the certificate by id
    @DeleteMapping("/{certificateId}")
    public void deleteCertificate(@PathVariable int certificateId) {
    	logger.info("deleting the certificate");
        certificateService.deleteCertificate(certificateId);
       
    }
   
    @GetMapping("/download/{certificateId}")
    public ResponseEntity<?> downloadCertificate(@PathVariable int certificateId) {
        logger.info("Downloading certificate ID: {}", certificateId);
        try {
            Certificate certificate = certificateService.getCertificateById(certificateId);
            String certificateUrl = certificate.getCertificateUrl();
            // Logic to download the file (URL fetching can be handled here)
            return ResponseEntity.ok("Certificate file is available at: " + certificateUrl);
        } catch (Exception e) {
            logger.error("Error downloading certificate: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to download certificate ID: " + certificateId);
        }
    }
}
