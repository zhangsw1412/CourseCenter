package buaa.course.mapper;

import buaa.course.model.SemesterCourse;

import java.util.List;

public interface SemesterCourseMapper {
	SemesterCourse getSemesterCourse(int id);
	SemesterCourse getSemesterCourseByTwoIds(int semesterId, int courseId);
	int addSemesterCourse(SemesterCourse semesterCourse);
	int deleteSemesterCourse(int id);
	int updateSemesterCourse(SemesterCourse semesterCourse);
	List<SemesterCourse> getAllSemesterCourses();
	int countSemesterCourses();
	List<SemesterCourse> getSemesterCoursesByRange(int start,int lines);
	List<SemesterCourse> getSemesterCourseBySemesterId(int semesterId);
}
