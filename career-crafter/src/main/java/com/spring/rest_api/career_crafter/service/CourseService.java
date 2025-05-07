package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.Enrollment;

import com.spring.rest_api.career_crafter.model.JobSeekerCertificates;
import com.spring.rest_api.career_crafter.repository.CertificateRepository;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;
import com.spring.rest_api.career_crafter.repository.CourseReviewRepository;
import com.spring.rest_api.career_crafter.repository.EnrollmentRepository;
import com.spring.rest_api.career_crafter.repository.JobSeeekerCertificatesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseModuleRepository courseModuleRepository;
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private CertificateRepository certificateRepository;
	@Autowired
	private JobSeeekerCertificatesRepository jobSeeekerCertificatesRepository;

	@Autowired
	private CourseReviewRepository courseReviewRepository;

	/**
	 * ; Adds a new course with the provided details.
	 *
	 * @param course The course to be added
	 * @return The saved course entity
	 */
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	/**
	 * Retrieves all courses with pagination support.
	 *
	 * @param pageable The pagination details
	 * @return A page of courses
	 */
	public Page<Course> getAllCourses(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

	/**
	 * Retrieves a course by its ID.
	 *
	 * @param courseId The ID of the course to retrieve
	 * @return The course with the specified ID
	 * @throws InvalidIDException If the course is not found
	 */
	public Course getCourseById(int courseId) throws InvalidIDException {
		return courseRepository.findById(courseId).orElseThrow(() -> new InvalidIDException("Course not found"));
	}

	/**
	 * Updates an existing course with the provided data.
	 *
	 * @param courseId     The ID of the course to update
	 * @param updateCourse The course data to update with
	 * @return The updated course
	 * @throws InvalidIDException If the course is not found
	 */
	public Course updateCourse(int courseId, Course updateCourse) throws InvalidIDException {
		Course existingCourse = courseRepository.findById(courseId)
				.orElseThrow(() -> new InvalidIDException("Course not found"));

		// Update fields
		existingCourse.setTitle(updateCourse.getTitle());
		existingCourse.setDescription(updateCourse.getDescription());
		existingCourse.setCategory(updateCourse.getCategory()); // Update the category name
		// Update other fields as needed

		return courseRepository.save(existingCourse);
	}

	/**
	 * Deletes a course by its ID.
	 * 
	 * This method also ensures that associated course modules are deleted before
	 * the course itself.
	 *
	 * @param courseId The ID of the course to delete
	 * @throws InvalidIDException If the course is not found
	 */
	@Transactional
	public void deleteCourse(int courseId) throws InvalidIDException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new InvalidIDException("Course not found"));

		// Step 1: Get enrollments for this course
		List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

		// Step 2: For each enrollment, handle certificate and job seeker links
		for (Enrollment enrollment : enrollments) {
			Certificate certificate = certificateRepository.findByEnrollmentId(enrollment.getId());

			if (certificate != null) {
				List<JobSeekerCertificates> jobSeekerLinks = jobSeeekerCertificatesRepository
						.findByCertificates(certificate);
				jobSeeekerCertificatesRepository.deleteAll(jobSeekerLinks);
				certificateRepository.delete(certificate);
			}
		}

		// Step 3: Delete enrollments
		enrollmentRepository.deleteAll(enrollments);

		// Step 4: Delete course modules
		courseModuleRepository.deleteByCourseId(courseId);

		// Step 4.5: Delete course reviews
		courseReviewRepository.deleteByCourseId(courseId); // <-- Add this line

		// Step 5: Delete the course
		courseRepository.delete(course);
	}

	/**
	 * Retrieves the total count of courses.
	 *
	 * @return The total number of courses
	 */
	public long getCourseCount() {
		return courseRepository.count();
	}

	/**
	 * Searches for courses by title.
	 * 
	 * This method returns a list of courses whose titles contain the provided
	 * string.
	 *
	 * @param title The title or part of the title to search for
	 * @return A list of courses matching the search criteria
	 */
	public List<Course> searchCoursesByTitle(String title) {

		return courseRepository.findByTitleContaining(title);
	}

}
