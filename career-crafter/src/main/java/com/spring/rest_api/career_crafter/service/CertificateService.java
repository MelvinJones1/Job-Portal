package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    // Save or update a Certificate
    public Certificate saveCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    // Get a Certificate by ID
    public Certificate getCertificateById(int id) {
        return certificateRepository.findById(id).orElse(null);  // Return null if not found
    }

    // Delete a Certificate by ID
    public void deleteCertificate(int id) {
        certificateRepository.deleteById(id);
    }
}
