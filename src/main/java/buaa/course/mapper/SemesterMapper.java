package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Semester;

public interface SemesterMapper {
	Semester getSemester(int id);
	int addSemester(Semester semester);
	int deleteSemester(int id);
	int updateSemester(Semester semester);
	List<Semester> getAllSemesters();
	int countSemesters();
	List<Semester> getSemestersByRange(int start,int lines);
}
