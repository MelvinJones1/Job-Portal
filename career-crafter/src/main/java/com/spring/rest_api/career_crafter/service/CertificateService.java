package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Certificate;

import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CertificateService {

	@Autowired
	private CertificateRepository certificateRepository;

	public Certificate issueCertificate(Enrollment enrollment) {
		if (enrollment.isCompleted()) {
			Certificate certificate = new Certificate();
			certificate.setCertificateUrl("https://career-crafter.com/certificates/" + enrollment.getId() + ".pdf");
			certificate.setEnrollment(enrollment);
			return certificateRepository.save(certificate);
		}
		throw new IllegalStateException("Enrollment not completed or progress is not 100%");
	}

	public Certificate getCertificateByEnrollmentId(int enrollmentId) {
		return certificateRepository.findByEnrollmentId(enrollmentId);
	}
}
