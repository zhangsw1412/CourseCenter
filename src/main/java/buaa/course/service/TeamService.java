 package buaa.course.service;

 import buaa.course.mapper.TeamMapper;
 import buaa.course.mapper.TeamStudentMapper;
 import buaa.course.model.SemesterCourse;
 import buaa.course.model.Team;
 import buaa.course.model.TeamApplication;
 import buaa.course.model.TeamStudent;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.stereotype.Service;

 import javax.annotation.Resource;
 import java.sql.Timestamp;
 import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class TeamService {
    @Resource(name = "teamMapper")
    private TeamMapper teamMapper;

    @Resource(name = "teamStudentMapper")
    private TeamStudentMapper teamStudentMapper;

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
    
    public List<TeamApplication> getTeamApplicationsByStudentId(int id) {
    	return teamMapper.getTeamApplicationsByStudentId(id);
    }
    
    public boolean isUserFromTeam(int num, Team team) {
        return teamMapper.isUserFromTeam(num, team.getId()) > 0;
    }

    public Long getTeamApplicationStatus(int num, Team team) {
    	return teamMapper.getTeamApplicationStatus(num,team.getId());
    }

    public void applyToTeam(Integer userId, Integer teamId) {
        Team team = teamMapper.getTeam(teamId);
        if(isApplicationAllowed(team)){
            TeamApplication application = new TeamApplication();
            application.setApplyTime(new Timestamp(System.currentTimeMillis()));
            application.setTeamId(teamId);
            application.setUserId(userId);
            application.setStatus(0);
            teamMapper.applyToTeam(application);
        }
    }

    public void handleTeamApplication(Integer userId, Integer applicationId, Integer handleType) {
        if(userId == null || applicationId == null || handleType == null)
            throw new RuntimeException();
        Team team = teamMapper.getTeamByApplicationId(applicationId);
        if(handleType == 1){
            teamStudentMapper.addTeamStudent(new TeamStudent(userId, team.getId()));
            team.setNum(team.getNum()+1);
            teamMapper.updateTeam(team);
            teamMapper.applicationHandled(applicationId, handleType);
        }
        if(handleType == 2){
            teamMapper.applicationHandled(applicationId, handleType);
        }
    }

    private boolean isApplicationAllowed(Team team) {
        if(!team.isApplicable())
            return false;
        int teamMemberCount = teamMapper.getTeamMemberCount(team.getId());
        return teamMemberCount < team.getMaxNum();
    }


    public List<TeamApplication> getApplications(Team team) {
        return teamMapper.getApplicationsByTeamId(team.getId());
    }

    public Team getTeamByStudent(int semesterCourseId, int studentId) {
        return teamMapper.getTeamBySemesterCourseIdAndStudentId(semesterCourseId, studentId);
    }

    public List<Integer> getTeamMemberIds(Team team) {
        return teamMapper.getTeamMemberIds(team.getId());
    }
	public int createTeamStudent(TeamStudent teamStudent) {
		return teamStudentMapper.addTeamStudent(teamStudent);
	}
	public int deleteTeamApplication(int userId, int applicationId) {
        Team team = teamMapper.getTeamByApplicationId(applicationId);
        TeamApplication application = teamMapper.getApplicationById(applicationId);
        if(team.getLeaderId() == userId){
            application.setLeaderDelete(true);
        }else{
            application.setStudentDelete(true);
        }
        return teamMapper.updateTeamApplication(application);
    }}
