package buaa.course.mapper;

import buaa.course.model.CourseTeacher;

import java.util.List;

public interface CourseTeacherMapper {
	CourseTeacher getCourseTeacher(int id);
	int addCourseTeacher(CourseTeacher courseTeacher);
	int deleteCourseTeacher(int id);
	int updateCourseTeacher(CourseTeacher courseTeacher);
	List<CourseTeacher> getAllCourseTeachers();
	int countCourseTeachers();
	List<CourseTeacher> getCourseTeachersByRange(int start,int row);
	List<CourseTeacher> getCourseTeacherByTeacher(int teacherId);
	List<CourseTeacher> getCourseTeacherBySemesterCourseId(int semesterCourseId);
}
