package buaa.course.mapper;

import java.util.List;

import buaa.course.model.CourseTeacher;

public interface CourseTeacherMapper {
	CourseTeacher getCourseTeacher(int id);
	int addCourseTeacher(CourseTeacher courseTeacher);
	int deleteCourseTeacher(int id);
	int updateCourseTeacher(CourseTeacher courseTeacher);
	List<CourseTeacher> getAllCourseTeachers();
	int countCourseTeachers();
	List<CourseTeacher> getCourseTeachersByRange(int start,int row);
}
