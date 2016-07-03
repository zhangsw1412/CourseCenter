package buaa.course.mapper;

import java.util.List;

import buaa.course.model.SemesterCourse;

public interface SemesterCourseMapper {
	SemesterCourse getSemesterCourse(int id);
	int addSemesterCourse(SemesterCourse semesterCourse);
	int deleteSemesterCourse(int id);
	int updateSemesterCourse(SemesterCourse semesterCourse);
	List<SemesterCourse> getAllSemesterCourses();
	int countSemesterCourses();
	List<SemesterCourse> getSemesterCoursesByRange(int start,int lines);
}
