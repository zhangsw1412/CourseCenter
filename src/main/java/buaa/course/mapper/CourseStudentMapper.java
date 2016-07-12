package buaa.course.mapper;

import buaa.course.model.CourseStudent;

import java.util.List;

public interface CourseStudentMapper {
	CourseStudent getCourseStudent(int id);
	int addCourseStudent(CourseStudent courseStudent);
	int deleteCourseStudent(int id);
	int updateCourseStudent(CourseStudent courseStudent);
	List<CourseStudent> getAllCourseStudents();
	int countCourseStudents();
	List<CourseStudent> getCourseStudentsByRange(int start,int lines);
	List<CourseStudent> getCourseStudentByStudent(int studentId);
	int countStudents(int semesterCourseId);
	int getCourseStudentCountByCourseIdAndTeamId(int courseId, int teamId);
	CourseStudent getCourseStudentByCourseAndStudent(int semesterCourseId, int studentId);
}
