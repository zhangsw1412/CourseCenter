package buaa.course.controller;

import buaa.course.model.*;
import buaa.course.service.CourseService;
import buaa.course.service.TeamService;
import buaa.course.service.UserService;

import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    
    @Resource(name = "userService")
    private UserService userService;

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
        User user = (User) request.getSession().getAttribute("user");
    	ModelAndView m = new ModelAndView("team/team_details");
        List<User> members = userService.getUsersByTeamId(teamId);
        boolean isInTeam = false;
        boolean isApplied = false;
        for(User member : members){
        	if(member.getNum()==user.getNum())
        		isInTeam = true;
        }
        if(!isInTeam){
        	List<TeamApplication> teamApplications = teamService.getTeamApplicationsByStudentId(user.getNum());
        	for(TeamApplication item : teamApplications){
        		if(item.getTeamId()==teamId)
        			isApplied=true;
        	}
        }
        List<TeamApplication> applications = teamService.getTeamApplicationsByTeamId(teamId);
        Map<Long, User> applicants = getApplicants(applications);
        m.addObject("team", teamService.getTeam(teamId));
        m.addObject("applications", applications);
        m.addObject("applicants", applicants);
        m.addObject("members", members);
        m.addObject("isInTeam", isInTeam);
        m.addObject("isApplied", isApplied);
        return m;
    }

    private Map<Long, User> getApplicants(List<TeamApplication> applications) {
        Map<Long, User> result = new HashMap<>();
        for(TeamApplication application : applications){
        	result.put(Long.valueOf(application.getUserId()), userService.getUserByNum(application.getUserId()));
        }
        return result;
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
        team.setApplicable(team.getMaxNum()>team.getNum());
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
        return m;
	}

    @RequestMapping(method = RequestMethod.GET, value = "/team/{teamId}/appoint/{studentNum}")
    public void appointTeamLeader(@PathVariable Integer teamId, @PathVariable Integer studentNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        List<User> members = userService.getUsersByTeamId(teamId);
        boolean isInTeam = false;
        for(User member : members){
        	if(member.getNum()==studentNum)
        		isInTeam = true;
        }
        if(user.getNum()==teamService.getTeam(teamId).getLeaderId()&&isInTeam){
        	Team team = teamService.getTeam(teamId);
        	team.setLeaderId(studentNum);
            team.setLeaderName(userService.getUserByNum(studentNum).getName());
        	teamService.updateTeam(team);
        }
        response.sendRedirect("/team/team_details/"+teamId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/team/applyToTeam/{teamId}")
    public void applyToTeam(@PathVariable Integer teamId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        int userId = user.getNum();
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

    @RequestMapping(method = RequestMethod.GET, value = "/team/handleTeamApplication/{applicationId}/handleType/{handleType}" )
    public void handleTeamApplication(@PathVariable Integer applicationId, HttpServletRequest request, HttpServletResponse response, @PathVariable Integer handleType) throws IOException {
        checkUser(request, response);
        TeamApplication application = teamService.getTeamApplicationByTeamApplicationId(applicationId);
        teamService.handleTeamApplication(application.getUserId(), applicationId, handleType);
        Team team = teamService.getTeamByApplicationId(applicationId);
        response.sendRedirect("/team/team_details/"+team.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/team/deleteTeamApplication/{applicationId}" )
    public void deleteTeamApplication(@PathVariable Integer applicationId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        Integer userId = user.getNum();
        teamService.deleteTeamApplication(userId, applicationId);
        TeamApplication application = teamService.getTeamApplicationByTeamApplicationId(applicationId);
        Team team = teamService.getTeamByApplicationId(applicationId);
        if(userId==team.getLeaderId())
        	response.sendRedirect("/team/team_details/"+team.getId());
        else if(userId==application.getUserId())
        	response.sendRedirect("/team/my_teams");
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/team/changeStatus/{teamId}")
    public void changeTeamStatus(@PathVariable("teamId") Integer teamId, HttpServletRequest request, HttpServletResponse response) throws IOException{
        checkUser(request, response);
        Team team = teamService.getTeam(teamId);
        if(team.isApplicable()){
        	team.setApplicable(false);
        }
        else if(team.getMaxNum()>team.getNum()){
        	team.setApplicable(true);
        }
        teamService.updateTeam(team);
        response.sendRedirect("/team/team_details/"+teamId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/semester/{semesterId}/team_courses/{teamId}")
    public ModelAndView teamCourses(@PathVariable("semesterId") Integer semesterId, HttpServletRequest request, HttpServletResponse response, @PathVariable("teamId") Integer teamId) throws IOException {
        User user = checkUser(request, response);
        Team team = teamService.getTeam(teamId);
        if(user.getNum()!=team.getLeaderId())
        	response.sendRedirect("/index");
        ModelAndView m = new ModelAndView("/team/team_course_list");
        List<Course> courses = courseService.getTeamAvaliableCourses(semesterId);
        m.addObject("team_courses", courses);
        Map<Long, Long> courseStatusMap = getCourseStatusMap(semesterId, courses, teamId);
        m.addObject("courseStatusMap", courseStatusMap);
        m.addObject("teachersMap", courseService.getTeachersName(semesterId, courses));
        m.addObject("teamId", teamId);
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value="/team/apply_course")
    public void applyCourse(@RequestParam Integer semesterId, @RequestParam Integer courseId, @RequestParam Integer teamId,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        Team team = teamService.getTeam(teamId);

        if(team != null && team.getLeaderId() == user.getNum()){
            SemesterCourse sc = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
            teamService.applyCourse(sc.getId(), teamId);
        }
        response.sendRedirect("/semester/"+semesterId+"/team_courses/"+teamId);
    }

    @RequestMapping(method = RequestMethod.GET, value="/semester/{semesterId}/course/{courseId}/applications")
    public ModelAndView courseApplications(HttpServletRequest request, HttpServletResponse response, @PathVariable("semesterId") Integer semesterId, @PathVariable("courseId") Integer courseId) throws IOException {
        ModelAndView m = new ModelAndView("/team/course_applications");
        SemesterCourse sc = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
        if(sc != null){
            m.addObject("unhandledApp", courseService.getUnHandledApplicationsByCourse(sc.getId()));
            m.addObject("handledApp", courseService.getHandledApplicationsByCourse(sc.getId()));
            Map<Long, Team> teams = new HashMap<>();
            for(CourseApplication application : courseService.getApplicationsByCourse(sc.getId())){
                teams.put(Long.valueOf(application.getTeamId()), teamService.getTeam(application.getTeamId()));
            }
            m.addObject("teams", teams);
        }
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value="/team/permitCourseApplication")
    public void ppermitApplication(@RequestParam Integer applicationId,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        CourseApplication application = courseService.getCourseApplicationById(applicationId);
        application.setStatus(1);
        courseService.updateCourseApplication(application);
        Team team = teamService.getTeam(application.getTeamId());
        SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(application.getSemesterCourseId());
        List<CourseStudent> courseStudents = new ArrayList<>();
        for(Integer studentId : teamService.getTeamMemberIds(team)){
            CourseStudent cs = courseService.getCourseStudentByCourseAndStudent(semesterCourse.getId(), studentId);

            if(cs != null){
                courseStudents.add(cs);
            }
        }
        if(courseStudents.size() != 0){
            for(Integer studentId : teamService.getTeamMemberIds(team)){
                CourseStudent cs = new CourseStudent();
                cs.setSemesterCourseId(semesterCourse.getId());
                cs.setStudentId(studentId);
                cs.setTeamId(team.getId());
                courseService.createCourseStudent(cs);
            }

        }
        response.sendRedirect("/semester/"+semesterCourse.getSemesterId()+"/course/"+semesterCourse.getCourseId()+"/applications");
    }

    @RequestMapping(method = RequestMethod.POST, value="/team/denyCourseApplication")
    public void denyApplication(@RequestParam Integer applicationId,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        CourseApplication application = courseService.getCourseApplicationById(applicationId);

        courseService.deleteCourseApplication(application);
        SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(application.getSemesterCourseId());
        response.sendRedirect("/semester/"+semesterCourse.getSemesterId()+"/course/"+semesterCourse.getCourseId()+"/applications");
    }

    private Map<Long,Long> getCourseStatusMap(int semesterId, List<Course> courses, int teamId) {
        Map<Long, Long> result = new HashMap<>();
        for(Course course : courses){
            SemesterCourse sc = courseService.getSemesterCourseBySemesterCourseId(semesterId, course.getId());
            result.put(Long.valueOf(course.getId()), getCourseStatus(sc.getId(), teamId));
        }
        return result;
    }

    private Long getCourseStatus(int semesterCourseId, int teamId) {
        int csCount = courseService.getCourseByCourseIdAndTeamId(semesterCourseId, teamId);
        if(csCount > 0){
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
