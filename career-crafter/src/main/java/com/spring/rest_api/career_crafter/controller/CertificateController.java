
package com.spring.rest_api.career_crafter.controller;

import java.util.List;

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
@RequestMapping("/api/certifictae")
public class CertificateController {

	 @Autowired
	 private CertificateService certificateService;
	@PostMapping("/add/{contentId}")
    public Certificate addCertificate(@PathVariable int contentId, @RequestBody Certificate certificate) {
		 return certificateService.addCertificateToCourseContent(contentId, certificate);
	}

    @GetMapping("/get/{contentId}")
    public List<Certificate> getCertificatesByContent(@PathVariable int contentId) {
        return certificateService.getCertificatesByContentId(contentId);
    }

    @PutMapping("/update/{certificateId}")
    public Certificate updateCertificate(@PathVariable int certificateId, @RequestBody Certificate updatedData) {
        return certificateService.updateCertificate(certificateId, updatedData);
    }

    @DeleteMapping("/{certificateId}")
    public void deleteCertificate(@PathVariable int certificateId) {
        certificateService.deleteCertificate(certificateId);
       
    }
}
