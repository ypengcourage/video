package com.zhiyou100.video.service.admin;

import java.util.List;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.util.Page;

public interface CourseService {

	List<Course> findAllCourse();

	Page<Course> loadPage(Integer page);

	List<Subject> findAllSubject();

	void addCourse(Course cc);

	Course findCourseById(int id);

	void deleteCourse(int id);

	void updateCourse(Course cc);


}
