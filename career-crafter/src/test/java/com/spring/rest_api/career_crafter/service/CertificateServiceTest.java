package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.repository.CertificateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class CertificateServiceTest {

    @Mock
    private CertificateRepository certificateRepository;

    @InjectMocks
    private CertificateService certificateService;

    private Certificate certificate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        certificate = new Certificate();
        certificate.setCertificateUrl("https://example.com/certificate.pdf");
    }

    // Test for saving a certificate
    @Test
    void testSaveCertificate() {
        // Mock the save behavior
        Mockito.when(certificateRepository.save(any(Certificate.class))).thenReturn(certificate);

        // Call the method
        Certificate savedCertificate = certificateService.saveCertificate(certificate);

        // Verify the result
        assertNotNull(savedCertificate);
        assertEquals("https://example.com/certificate.pdf", savedCertificate.getCertificateUrl());
        Mockito.verify(certificateRepository, Mockito.times(1)).save(certificate);
    }

    // Test for getting a certificate by ID
    @Test
    void testGetCertificateById() {
        // Mock the repository behavior for finding a certificate by ID
        Mockito.when(certificateRepository.findById(1)).thenReturn(java.util.Optional.of(certificate));

        // Call the method
        Certificate foundCertificate = certificateService.getCertificateById(1);

        // Verify the result
        assertNotNull(foundCertificate);
        assertEquals("https://example.com/certificate.pdf", foundCertificate.getCertificateUrl());
        Mockito.verify(certificateRepository, Mockito.times(1)).findById(1);
    }

    // Test for not finding a certificate by ID
    @Test
    void testGetCertificateById_NotFound() {
        // Mock the repository behavior for not finding a certificate by ID
        Mockito.when(certificateRepository.findById(1)).thenReturn(java.util.Optional.empty());

        // Call the method
        Certificate foundCertificate = certificateService.getCertificateById(1);

        // Verify the result (should be null)
        assertNull(foundCertificate);
        Mockito.verify(certificateRepository, Mockito.times(1)).findById(1);
    }

    // Test for deleting a certificate
    @Test
    void testDeleteCertificate() {
        // Perform delete operation, mock no exception
        Mockito.doNothing().when(certificateRepository).deleteById(1);

        // Call the method
        certificateService.deleteCertificate(1);

        // Verify that deleteById was called
        Mockito.verify(certificateRepository, Mockito.times(1)).deleteById(1);
    }

    // Test for deleting a certificate that does not exist (repository behavior should not change)
    @Test
    void testDeleteCertificate_NotFound() {
        // Mock delete behavior for a non-existing certificate
        Mockito.doNothing().when(certificateRepository).deleteById(1);

        // Call the method (nothing should happen as it's a mock)
        certificateService.deleteCertificate(1);

        // Verify deleteById was still called (no exception thrown)
        Mockito.verify(certificateRepository, Mockito.times(1)).deleteById(1);
    }
}
