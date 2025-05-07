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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {

    // Mock the InstructorRepository, so it doesn’t actually connect to the DB during tests
    @Mock
    private InstructorRepository instructorRepository;

    // Mock the AuthService, as the logic for instructor sign-up is handled in that service
    @Mock
    private AuthService authService;

    // Inject the mocks into the service class to test the actual logic
    @InjectMocks
    private InstructorService instructorService;

    // Setup common test data for instructor
    private Instructor instructor;

    @BeforeEach
    public void setup() {
        // Initialize an instructor object with basic details for reuse across tests
        instructor = new Instructor();
        instructor.setId(1);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setEmail("john.doe@example.com");
    }

    // Test for retrieving instructor profile by username - Success scenario
    @Test
    public void testGetInstructorProfile_Success() {
        // Arrange: Set up mock behavior for the repository when searching by username
        String username = "john.doe";
        when(instructorRepository.findByUserUsername(username)).thenReturn(instructor);

        // Act: Call the service method to retrieve the instructor's profile
        Instructor result = instructorService.getInstructorProfile(username);

        // Assert: Validate the result is not null and the first name matches
        assertNotNull(result);
        assertEquals("John", result.getFirstName());

        // Verify that the repository method was called exactly once with the username
        verify(instructorRepository, times(1)).findByUserUsername(username);
    }

    // Test for retrieving instructor profile by username - Not Found scenario
    @Test
    public void testGetInstructorProfile_NotFound() {
        // Arrange: Set up mock behavior for a non-existing username
        String username = "nonexistent.username";
        when(instructorRepository.findByUserUsername(username)).thenReturn(null);

        // Act & Assert: Call the service method and expect an exception to be thrown
        assertThrows(RuntimeException.class, () -> instructorService.getInstructorProfile(username));
    }

    // Test for retrieving a single instructor by ID - Success scenario
    @Test
    public void testGetSingleInstructor_Success() throws InvalidIDException {
        // Arrange: Set up mock behavior for the repository when searching by instructor ID
        int instructorId = 1;
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));

        // Act: Call the service method to retrieve the instructor
        Instructor result = instructorService.getSingleInstructor(instructorId);

        // Assert: Validate the result is not null and the ID matches
        assertNotNull(result);
        assertEquals(1, result.getId());

        // Verify that the repository method was called exactly once with the instructor ID
        verify(instructorRepository, times(1)).findById(instructorId);
    }

    // Test for retrieving a single instructor by ID - Not Found scenario
    @Test
    public void testGetSingleInstructor_NotFound() throws InvalidIDException {
        // Arrange: Set up mock behavior for a non-existing instructor ID
        int instructorId = 1;
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.empty());

        // Act & Assert: Call the service method and expect an InvalidIDException to be thrown
        assertThrows(InvalidIDException.class, () -> instructorService.getSingleInstructor(instructorId));
    }

    // Test for updating an instructor's profile - Success scenario
    @Test
    public void testUpdateInstructorProfile_Success() throws InvalidIDException {
        // Arrange: Set up the updated instructor object with new details
        int instructorId = 1;
        Instructor updatedInstructor = new Instructor();
        updatedInstructor.setFirstName("Jane");
        updatedInstructor.setLastName("Smith");
        updatedInstructor.setEmail("jane.smith@example.com");

        // Set up mock behavior for finding the instructor by ID and saving the updated instructor
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // Act: Call the service method to update the instructor's profile
        Instructor result = instructorService.updateInstructorProfile(instructorId, updatedInstructor);

        // Assert: Validate the result's updated first name, last name, and ensure save method was called
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    // Test for adding a new instructor - Success scenario
    @Test
    public void testAddInstructor_Success() throws InvalidUsernameException {
        // Arrange: Set up a new user and new instructor
        User user = new User();
        user.setUsername("johndoe");

        Instructor newInstructor = new Instructor();
        newInstructor.setUser(user);

        // Set up mock behavior for user sign-up and saving the new instructor
        when(authService.instructorSignUp(any(User.class))).thenReturn(user);
        when(instructorRepository.save(any(Instructor.class))).thenReturn(newInstructor);

        // Act: Call the service method to add the new instructor
        Instructor result = instructorService.addInstructor(newInstructor);

        // Assert: Validate that the new instructor is not null and has the expected username
        assertNotNull(result);
        assertEquals("johndoe", result.getUser().getUsername());

        // Verify that the repository save method was called exactly once
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    // Test for deleting an instructor by ID - Success scenario
    @Test
    public void testDeleteInstructorById_Success() {
        // Arrange: Set up the instructor to be deleted
        Instructor instructorToDelete = new Instructor();
        instructorToDelete.setId(1);

        // Set up mock behavior for delete (do nothing since we’re just verifying the call)
        doNothing().when(instructorRepository).delete(instructorToDelete);

        // Act: Call the service method to delete the instructor
        instructorService.deleteInstructorById(instructorToDelete);

        // Assert: Verify that the delete method was called exactly once
        verify(instructorRepository, times(1)).delete(instructorToDelete);
    }
}
