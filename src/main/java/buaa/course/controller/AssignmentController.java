package buaa.course.controller;

import buaa.course.model.Assignment;
import buaa.course.model.Course;
import buaa.course.model.User;
import buaa.course.service.AssignmentService;
import buaa.course.service.CourseService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AssignmentController {
    @Resource(name = "assignmentService")
    private AssignmentService assignmentService;
    
    @Resource(name = "courseService")
    private CourseService courseService;


    @RequestMapping(method = RequestMethod.GET, value = "/assignments/{semesterCourseId}")
    public ModelAndView assignmentsGet(@PathVariable Integer semesterCourseId, HttpServletRequest request) {
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()==2)
    		return new ModelAndView("login");*/
    	ModelAndView m= new ModelAndView("assignments");
    	if(semesterCourseId!=null){
    		m.addObject("assignmentlist",assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId));
    	}
    	return m;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/assign/{semesterCourseId}")
    public ModelAndView assignGet(@PathVariable Integer semesterCourseId, HttpServletRequest request) {
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()==1)
    		return new ModelAndView("login");*/
    	if(semesterCourseId==null)
    		return new ModelAndView("assignmentlist");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	ModelAndView m = new ModelAndView("assign");
    	m.addObject("course",course);
    	m.addObject("semesterCourseId", semesterCourseId);
    	return m;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/assign/{semesterCourseId}")
    public ModelAndView assignPost(@PathVariable Integer semesterCourseId, HttpServletRequest request){
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()==1)
    		return new ModelAndView("login");*/
    	if(semesterCourseId==null)
    		return new ModelAndView("assignmentlist");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	ModelAndView m = new ModelAndView("assign");
    	m.addObject("course",course);
    	
    	String name = request.getParameter("name");
    	if(name==null){
        	m.addObject("error", "作业名称不能为空");
        	return m;
        }
    	
    	String basicRequirement = request.getParameter("basicrequirement");
    	if(basicRequirement==null){
        	m.addObject("error", "请填写作业基本要求");
        	return m;
        }
    	
    	String startTime_s = request.getParameter("starttime");
    	if(startTime_s==null){
        	m.addObject("error", "开始时间不能为空");
        	return m;
        }
    	Timestamp startTime;
    	try{
        	startTime = Timestamp.valueOf(startTime_s);
    	}
    	catch(Exception e){
        	m.addObject("error", "开始时间不合法");
        	return m;
    	}
    	
    	String deadline_s = request.getParameter("deadline");
    	if(deadline_s==null){
        	m.addObject("error", "截止时间不能为空");
        	return m;
        }
    	Timestamp deadline;
    	try{
        	deadline = Timestamp.valueOf(deadline_s);
    	}
    	catch(Exception e){
        	m.addObject("error", "截止时间不合法");
        	return m;
    	}
    	
    	String teamAvaliable_s = request.getParameter("teamavaliable");
    	if(teamAvaliable_s==null){
        	m.addObject("error", "请选择是否允许团队参与");
        	return m;
    	}
    	boolean teamAvaliable;
    	try{
        	teamAvaliable = Boolean.valueOf(teamAvaliable_s);
    	}
    	catch(Exception e){
        	m.addObject("error", "请选择是否允许团队参与");
        	return m;
    	}
    	
    	String highestScore_s = request.getParameter("highestscore");
    	if(highestScore_s==null){
        	m.addObject("error", "分数上限不能为空");
        	return m;
    	}
    	int highestScore;
    	try{
    		highestScore = Integer.valueOf(highestScore_s);
    	}
    	catch(Exception e){
        	m.addObject("error", "分数上限不合法");
        	return m;
    	}
    	if(highestScore<=0||highestScore>=100){
        	m.addObject("error", "分数上限不合法");
        	return m;
    	}
    	
    	Assignment assignment = new Assignment();
    	assignment.setSemesterCourseId(semesterCourseId);
    	assignment.setName(name);
    	assignment.setBasicRequirement(basicRequirement);
    	assignment.setFileUrl("1");
    	assignment.setStartTime(startTime);
    	assignment.setDeadline(deadline);
    	assignment.setTeamAvaliable(teamAvaliable);
    	assignment.setHighestScore(highestScore);
    	assignmentService.createAssignment(assignment);
    	ModelAndView assignments = new ModelAndView("assignments");
    	assignments.addObject("assignmentlist",assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId));
    	return assignments;
    }
}
