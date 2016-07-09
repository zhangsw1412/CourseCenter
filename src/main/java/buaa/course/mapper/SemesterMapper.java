package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Course;
import buaa.course.model.Semester;
import buaa.course.model.User;

public interface SemesterMapper
{
	Semester getSemester(Integer id);

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
