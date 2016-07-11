package buaa.course.controller;

import buaa.course.model.Team;
import buaa.course.model.User;
import buaa.course.service.TeamService;
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
        m.addObject("teams", teams);
        return m;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teamDetail/{teamId}")
    public ModelAndView teamDetail(@PathVariable Integer teamId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView m = new ModelAndView("team/teamDetail");
        m.addObject("team", teamService.getTeam(teamId));
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/applyToTeam/{teamId}")
    public void applyToTeam(@PathVariable Integer teamId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = checkUser(request, response);
        Integer userId = user.getNum();
        teamService.applyToTeam(userId, teamId);
        response.sendRedirect("/team/myTeams");
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

}
