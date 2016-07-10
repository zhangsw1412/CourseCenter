 package buaa.course.service;

import buaa.course.mapper.TeamMapper;
import buaa.course.model.Team;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class TeamService {
    @Resource(name = "teamMapper")
    private TeamMapper teamMapper;

    public Team getTeam(int id) {
        return teamMapper.getTeam(id);
    }

    public int createTeam(Team team) {
        return teamMapper.addTeam(team);
    }

    public int updateTeam(Team team) {
        return teamMapper.updateTeam(team);
    }

    public int deleteTeam(int id) {
        return teamMapper.deleteTeam(id);
    }

    public List<Team> getAllTeams() {
        return teamMapper.getAllTeams();
    }

    public List<Team> getTeamsByRange(int start, int length) {
        return teamMapper.getTeamsByRange(start, length);
    }

    public int countTeams() {
        return teamMapper.countTeams();
    }

    public List<Team> getTeamsByStudentId(int id) {
        return teamMapper.getTeamsByStudentId(id);
    }

    public boolean isUserFromTeam(int num, Team team) {
        return teamMapper.isUserFromTeam(num, team.getId()) > 0;
    }

    public Long getTeamApplicationStatus(int num, Team team) {
        return null;
    }

    public void applyToTeam(Integer userId, Integer teamId) {
        teamMapper.applyToTeam(userId, teamId);
    }

    public void handleTeamApplication(Integer userId, Integer applicationId, Integer handleType) {
        
    }
}
