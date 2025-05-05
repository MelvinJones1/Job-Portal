package com.spring.rest_api.career_crafter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.repository.AssignmentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {

    @InjectMocks
    private AssignmentService assignmentService;

    @Mock
    private AssignmentRepository assignmentRepository;

    Assignment a1;
    Assignment a2;
    Assignment a3;

    @BeforeEach
    public void init() {
        a1 = new Assignment();
        a1.setId(1);
        a1.setTitle("Assignment 1");
        a1.setDescription("Description 1");
        a1.setUrl("http://url1.com");
        a1.setSubmissionDeadline(LocalDate.of(2025, 5, 20));

        a2 = new Assignment();
        a2.setId(2);
        a2.setTitle("Assignment 2");
        a2.setDescription("Description 2");
        a2.setUrl("http://url2.com");
        a2.setSubmissionDeadline(LocalDate.of(2025, 5, 25));

        a3 = new Assignment();
        a3.setId(3);
        a3.setTitle("Assignment 3");
        a3.setDescription("Description 3");
        a3.setUrl("http://url3.com");
        a3.setSubmissionDeadline(LocalDate.of(2025, 6, 1));
    }

    @Test
    public void testAddAssignment() {
        when(assignmentRepository.save(a1)).thenReturn(a1);
        assertEquals(a1, assignmentService.addAssignment(a1));
        verify(assignmentRepository, times(1)).save(a1);
    }

    @Test
    public void testGetAllAssignments() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Assignment> list = Arrays.asList(a1, a2);
        Page<Assignment> page = new PageImpl<>(list);

        when(assignmentRepository.findAll(pageable)).thenReturn(page);
        assertEquals(2, assignmentService.getAllAssignments(pageable).getTotalElements());
        verify(assignmentRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetSingleAssignment_ValidId() throws InvalidIDException {
        when(assignmentRepository.findById(1)).thenReturn(Optional.of(a1));
        assertEquals(a1, assignmentService.getSingleAssignment(1));
        verify(assignmentRepository, times(1)).findById(1);
    }

    @Test
    public void testGetSingleAssignment_InvalidId() {
        when(assignmentRepository.findById(10)).thenReturn(Optional.empty());
        InvalidIDException ex = assertThrows(InvalidIDException.class, () -> {
            assignmentService.getSingleAssignment(10);
        });
        assertEquals("Assignment with ID 10 not found.", ex.getMessage());
        verify(assignmentRepository, times(1)).findById(10);
    }

    @Test
    public void testUpdateTheAssignment_ValidId() throws InvalidIDException {
        Assignment updateAssignment = new Assignment();
        updateAssignment.setTitle("Updated Title");
        updateAssignment.setDescription("Updated Description");
        updateAssignment.setUrl("http://updated.com");
        updateAssignment.setSubmissionDeadline(LocalDate.of(2025, 6, 30));

        when(assignmentRepository.findById(1)).thenReturn(Optional.of(a1));
        when(assignmentRepository.save(any(Assignment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Assignment updated = assignmentService.updateTheAssignment(1, updateAssignment);

        assertEquals("Updated Title", updated.getTitle());
        assertEquals("Updated Description", updated.getDescription());
        assertEquals("http://updated.com", updated.getUrl());
        assertEquals(LocalDate.of(2025, 6, 30), updated.getSubmissionDeadline());

        verify(assignmentRepository, times(1)).findById(1);
        verify(assignmentRepository, times(1)).save(a1);
    }

    @Test
    public void testUpdateTheAssignment_InvalidId() {
        when(assignmentRepository.findById(99)).thenReturn(Optional.empty());

        InvalidIDException ex = assertThrows(InvalidIDException.class, () -> {
            assignmentService.updateTheAssignment(99, a2);
        });

        assertEquals("Assignment with ID 99 not found.", ex.getMessage());
        verify(assignmentRepository, times(1)).findById(99);
        verify(assignmentRepository, times(0)).save(any(Assignment.class));
    }

    @Test
    public void testDeleteAssignmentById_ValidId() throws InvalidIDException {
        when(assignmentRepository.findById(2)).thenReturn(Optional.of(a2));
        assignmentService.deleteAssignmentById(2);
        verify(assignmentRepository, times(1)).findById(2);
        verify(assignmentRepository, times(1)).delete(a2);
    }

    @Test
    public void testDeleteAssignmentById_InvalidId() {
        when(assignmentRepository.findById(404)).thenReturn(Optional.empty());

        InvalidIDException ex = assertThrows(InvalidIDException.class, () -> {
            assignmentService.deleteAssignmentById(404);
        });

        assertEquals("Assignment with ID 404 not found.", ex.getMessage());
        verify(assignmentRepository, times(1)).findById(404);
        verify(assignmentRepository, times(0)).delete(any(Assignment.class));
    }

    @Test
    public void testGetTotalAssignmentCount() {
        when(assignmentRepository.count()).thenReturn(3L);
        assertEquals(3L, assignmentService.getTotalAssignmentCount());
        verify(assignmentRepository, times(1)).count();
    }
}
