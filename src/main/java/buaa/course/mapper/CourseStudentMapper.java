package buaa.course.mapper;

import java.util.List;

import buaa.course.model.CourseStudent;

public interface CourseStudentMapper {
	CourseStudent getCourseStudent(int id);
	int addCourseStudent(CourseStudent courseStudent);
	int deleteCourseStudent(int id);
	int updateCourseStudent(CourseStudent courseStudent);
	List<CourseStudent> getAllCourseStudents();
	int countCourseStudents();
	List<CourseStudent> getCourseStudentsByRange(int start,int lines);
}
