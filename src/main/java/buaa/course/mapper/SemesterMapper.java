package buaa.course.mapper;

import buaa.course.model.Course;
import buaa.course.model.Semester;
import buaa.course.model.User;

import java.util.List;

public interface SemesterMapper
{
	Semester getSemester(int id);
	int addSemester(Semester semester);
	int deleteSemester(int id);
	int updateSemester(Semester semester);
	List<Semester> getAllSemesters();
	int countSemesters();
	List<Semester> getSemestersByRange(int start, int lines);
	List<Course> getCoursesOfCurrentSemester(int year, int month);
	int getCurrentSemesterId(int year,int month);
	List<User> getCourseStudents(int semesterId,int courseId);
}
