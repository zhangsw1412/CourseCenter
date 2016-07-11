package buaa.course.mapper;

import buaa.course.model.Team;
import buaa.course.model.TeamApplication;

import java.util.List;

public interface TeamMapper {
	Team getTeam(int id);
	int addTeam(Team team);
	int deleteTeam(int id);
	int updateTeam(Team team);
	List<Team> getAllTeams();
	int countTeams();
	List<Team> getTeamsByRange(int start,int lines);
	List<Team> getTeamsByStudentId(int id);
	int isUserFromTeam(int num, int id);
	void applyToTeam(int teamId, int userId);
	Team getTeamByApplicationId(int applicationId);
	int getTeamMemberCount(int id);
	void applicationHandled(int applicationId, int handleType);
	List<TeamApplication> getApplicationsByTeamId(int id);
	Long getTeamApplicationStatus(int num, int teamId);
	List<TeamApplication> getTeamApplicationsByStudentId(int id);
	Team getTeamBySemesterCourseIdAndStudentId(int semesterCourseId, int studentId);
	List<Integer> getTeamMemberIds(int id);
}
