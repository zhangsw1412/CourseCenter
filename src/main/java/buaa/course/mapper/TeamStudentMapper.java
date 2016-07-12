package buaa.course.mapper;

import java.util.List;

import buaa.course.model.TeamStudent;

public interface TeamStudentMapper {
	TeamStudent getTeamStudent(int id);
	int addTeamStudent(TeamStudent teamStudent);
	int deleteTeamStudent(int id);
	int updateTeamStudent(TeamStudent teamStudent);
	List<TeamStudent> getAllTeamStudents();
	int countTeamStudents();
	List<TeamStudent> getTeamStudentsByRange(int start,int lines);
}
