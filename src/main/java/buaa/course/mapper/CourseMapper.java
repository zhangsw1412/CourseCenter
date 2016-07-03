package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Course;

public interface CourseMapper {
	Course getCourse(int id);
	int addCourse(Course course);
	int deleteCourse(int id);
	int updateCourse(Course course);
	List<Course> getAllCourses();
	int countCourses();
	List<Course> getCoursesByRange(int start,int lines);
}
