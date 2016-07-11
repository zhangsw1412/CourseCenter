package buaa.course.controller;

import buaa.course.model.*;
import buaa.course.service.CourseService;
import buaa.course.service.TeamService;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 熊纪元 on 2016/7/10.
 */
@Controller
public class TeamController {
    @Resource(name = "teamService")
    private TeamService teamService;

    @Resource(name = "courseService")
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET, value = "/team/all_teams")
    public ModelAndView allTeams(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        ModelAndView m = new ModelAndView("team/all_teams");
        List<Team> teams = teamService.getAllTeams();
        m.addObject("teams", teams);
        m.addObject("teamMap", getTeamMap(teams, user.getNum()));
        return m;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/team/my_teams")
    public ModelAndView myTeams(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        ModelAndView m = new ModelAndView("team/my_teams");
        List<Team> teams = teamService.getTeamsByStudentId(user.getNum());
        List<TeamApplication> teamApplications = teamService.getTeamApplicationsByStudentId(user.getNum());
        Map<Long, Team> teamsApplied = getTeamsAppliedMap(teamApplications);
        m.addObject("teams", teams);
        m.addObject("teamApplications", teamApplications);
        m.addObject("teamsApplied", teamsApplied);
        return m;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/team/team_details/{teamId}")
    public ModelAndView teamDetails(@PathVariable Integer teamId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView m = new ModelAndView("team/team_details");
        m.addObject("team", teamService.getTeam(teamId));
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/team/create_team")
    public ModelAndView createTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
                String name = request.getParameter("name");
        String maxNum_s = request.getParameter("maxNum");
        if(StringUtils.isEmptyOrWhitespaceOnly(name)||StringUtils.isEmptyOrWhitespaceOnly(maxNum_s)){
            ModelAndView m = new ModelAndView("team/my_teams");
            List<Team> teams = teamService.getTeamsByStudentId(user.getNum());
            List<TeamApplication> teamApplications = teamService.getTeamApplicationsByStudentId(user.getNum());
            Map<Long, Team> teamsApplied = getTeamsAppliedMap(teamApplications);
            m.addObject("teams", teams);
            m.addObject("teamApplications", teamApplications);
            m.addObject("teamsApplied", teamsApplied);
        	return m;
        }
        Team team = new Team();
        team.setName(name);
        team.setLeaderId(user.getNum());
        team.setLeaderName(user.getName());
        team.setMaxNum(Integer.valueOf(maxNum_s));
        team.setNum(1);
        team.setApplicable(true);
        teamService.createTeam(team);
        TeamStudent teamStudent = new TeamStudent();
        teamStudent.setTeamId(team.getId());
        teamStudent.setStudentId(user.getNum());
        teamService.createTeamStudent(teamStudent);
        ModelAndView m = new ModelAndView("team/my_teams");
        List<Team> teams = teamService.getTeamsByStudentId(user.getNum());
        List<TeamApplication> teamApplications = teamService.getTeamApplicationsByStudentId(user.getNum());
        Map<Long, Team> teamsApplied = getTeamsAppliedMap(teamApplications);
        m.addObject("teams", teams);
        m.addObject("teamApplications", teamApplications);
        m.addObject("teamsApplied", teamsApplied);
        return m;    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/team/apply_team/{teamId}")
    public void applyToTeam(@PathVariable Integer teamId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        Integer userId = user.getNum();
        teamService.applyToTeam(userId, teamId);
        response.sendRedirect("/team/my_teams");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teamApplications/{teamId}")
    public ModelAndView teamApplications(@PathVariable("teamId") Integer teamId) {
        ModelAndView m = new ModelAndView("team/teamApplications");
        Team team = teamService.getTeam(teamId);
        m.addObject("team", team);
        m.addObject("applications", teamService.getApplications(team));
        return m;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/handleTeamApplication/{applicationId}/handleType/{handleType}" )
    public void handleTeamApplication(@PathVariable Integer applicationId, HttpServletRequest request, HttpServletResponse response, @PathVariable Integer handleType) throws IOException {
        User user = checkUser(request, response);
        Integer userId = user.getNum();
        teamService.handleTeamApplication(userId, applicationId, handleType);
        response.sendRedirect("/teamApplications/{teamId}");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteTeamApplication/{applicationId}/handleType/{handleType}" )
    public void deleteTeamApplication(@PathVariable Integer applicationId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        Integer userId = user.getNum();
        teamService.deleteTeamApplication(userId, applicationId);
        response.sendRedirect("/team/my_teams");
    }

    @RequestMapping(method = RequestMethod.GET, value="/semester/{semesterId}/team_courses/{teamId}")
    public ModelAndView teamCourses(@PathVariable("semesterId") Integer semesterId, HttpServletRequest request, HttpServletResponse response, @PathVariable("teamId") Integer teamId) throws IOException {
        User user = checkUser(request, response);
        List<Course> courses = courseService.getTeamAvaliableCourses(semesterId);
        Map<Long, Long> courseStatusMap = getCourseStatusMap(semesterId, courses, teamId);
        ModelAndView m = new ModelAndView("/team/teamCourses");
        m.addObject("courses", courses);
        m.addObject("courseStatusMap", courseStatusMap);
        return m;
    }

    private Map<Long,Long> getCourseStatusMap(int semesterId, List<Course> courses, int teamId) {
        Map<Long, Long> result = new HashMap<>();
        for(Course course : courses){
            SemesterCourse sc = courseService.getSemesterCourseBySemesterCourseId(semesterId, course.getId());
            result.put(Long.valueOf(course.getId()), getCourseStatus(sc.getSemesterId(), teamId));
        }
        return result;
    }

    private Long getCourseStatus(int semesterCourseId, int teamId) {
        CourseStudent cs = courseService.getCourseByCourseIdAndTeamId(semesterCourseId, teamId);
        if(cs != null){
            return 1L;//已加入
        }
        CourseApplication application = courseService.getCourseApplicationByTeamId(semesterCourseId, teamId);
        if(application != null)
            return Long.valueOf(application.getStatus());
        return 3L;
    }


    private User checkUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user.getType() != 0){
            response.sendRedirect("/index");
            return null;
        }
        return user;
    }

    private Map<Long, Long> getTeamMap(List<Team> teams, int num) {
        Map<Long, Long> result = new HashMap<>();
        for(Team team : teams){
            if(teamService.isUserFromTeam(num, team)){
                result.put(Long.valueOf(team.getId()), 1L);
            }else if(teamService.getTeamApplicationStatus(num,team)!=null){
                result.put(Long.valueOf(team.getId()), teamService.getTeamApplicationStatus(num, team));
            }else{
            	result.put(Long.valueOf(team.getId()), 3L);
            }
        }
        return result;
    }
    private Map<Long, Team> getTeamsAppliedMap(List<TeamApplication> teamApplications) {
        Map<Long, Team> result = new HashMap<>();
        for(TeamApplication teamApplication : teamApplications){
        	result.put(Long.valueOf(teamApplication.getTeamId()), teamService.getTeam(teamApplication.getTeamId()));
        }
        return result;
    }
}
