package buaa.course.mapper;

import buaa.course.model.Team;

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
	void applyToTeam(Integer userId, Integer teamId);
}
