package buaa.course.mapper;

<<<<<<< HEAD
import java.util.List;

import buaa.course.model.Course;
=======
>>>>>>> 68eb1da515dfef9277f71b5e86c53a84392d2aaa
import buaa.course.model.Semester;
import buaa.course.model.User;

<<<<<<< HEAD
public interface SemesterMapper
{
	Semester getSemester(Integer id);

=======
import java.util.List;

public interface SemesterMapper {
	Semester getSemester(int id);
>>>>>>> 68eb1da515dfef9277f71b5e86c53a84392d2aaa
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
