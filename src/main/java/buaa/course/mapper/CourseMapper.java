package buaa.course.mapper;

import buaa.course.model.Course;

import java.util.List;

public interface CourseMapper {
	Course getCourse(int id);
	int addCourse(Course course);
	int deleteCourse(int id);
	int updateCourse(Course course);
	List<Course> getAllCourses();
	int countCourses();
	List<Course> getCoursesByRange(int start,int lines);
	Course getCourseByCourseCode(String courseCode);
	List<Course> getCoursesBySemesterIdAndStudentId(int semesterId, int studentId);
}