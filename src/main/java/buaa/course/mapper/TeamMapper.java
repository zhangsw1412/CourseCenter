package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Team;

public interface TeamMapper {
	Team getTeam(int id);
	int addTeam(Team team);
	int deleteTeam(int id);
	int updateTeam(Team team);
	List<Team> getAllTeams();
	int countTeams();
	List<Team> getTeamsByRange(int start,int lines);
}
