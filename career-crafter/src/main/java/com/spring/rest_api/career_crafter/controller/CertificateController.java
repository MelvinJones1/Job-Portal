
package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/get/{contentId}")
    public List<Certificate> getCertificatesByContent(@PathVariable int contentId) {
    	logger.info("getting certificates by contentID");
        return certificateService.getCertificatesByContentId(contentId);
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
}
