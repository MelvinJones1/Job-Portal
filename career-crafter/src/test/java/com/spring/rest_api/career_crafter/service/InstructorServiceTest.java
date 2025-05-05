package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.exception.InvalidUsernameException;
import com.spring.rest_api.career_crafter.model.Instructor;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private InstructorService instructorService;

    private Instructor instructor;

    @BeforeEach
    public void setup() {
        instructor = new Instructor();
        instructor.setId(1);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setEmail("john.doe@example.com");
    }

    // Test for getInstructorProfile
    @Test
    public void testGetInstructorProfile_Success() {
        // Given
        String username = "john.doe";
        when(instructorRepository.findByUserUsername(username)).thenReturn(instructor);

        // When
        Instructor result = instructorService.getInstructorProfile(username);

        // Then
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(instructorRepository, times(1)).findByUserUsername(username);
    }

    @Test
    public void testGetInstructorProfile_NotFound() {
        // Given
        String username = "nonexistent.username";
        when(instructorRepository.findByUserUsername(username)).thenReturn(null);

        // When & Then
        assertThrows(RuntimeException.class, () -> instructorService.getInstructorProfile(username));
    }

    // Test for getSingleInstructor
    @Test
    public void testGetSingleInstructor_Success() throws InvalidIDException {
        // Given
        int instructorId = 1;
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));

        // When
        Instructor result = instructorService.getSingleInstructor(instructorId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(instructorRepository, times(1)).findById(instructorId);
    }

    @Test
    public void testGetSingleInstructor_NotFound() throws InvalidIDException {
        // Given
        int instructorId = 1;
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(InvalidIDException.class, () -> instructorService.getSingleInstructor(instructorId));
    }

    // Test for updateInstructorProfile
    @Test
    public void testUpdateInstructorProfile_Success() throws InvalidIDException {
        // Given
        int instructorId = 1;
        Instructor updatedInstructor = new Instructor();
        updatedInstructor.setFirstName("Jane");
        updatedInstructor.setLastName("Smith");
        updatedInstructor.setEmail("jane.smith@example.com");
        
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // When
        Instructor result = instructorService.updateInstructorProfile(instructorId, updatedInstructor);

        // Then
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    


    // Test for addInstructor
    @Test
    public void testAddInstructor_Success() throws InvalidUsernameException {
        // Given
        User user = new User();
        user.setUsername("johndoe");
        
        Instructor newInstructor = new Instructor();
        newInstructor.setUser(user);
        
        when(authService.instructorSignUp(any(User.class))).thenReturn(user);
        when(instructorRepository.save(any(Instructor.class))).thenReturn(newInstructor);

        // When
        Instructor result = instructorService.addInstructor(newInstructor);

        // Then
        assertNotNull(result);
        assertEquals("johndoe", result.getUser().getUsername());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    // Test for deleteInstructorById
    @Test
    public void testDeleteInstructorById_Success() {
        // Given
        Instructor instructorToDelete = new Instructor();
        instructorToDelete.setId(1);
        
        doNothing().when(instructorRepository).delete(instructorToDelete);

        // When
        instructorService.deleteInstructorById(instructorToDelete);

        // Then
        verify(instructorRepository, times(1)).delete(instructorToDelete);
    }
}
