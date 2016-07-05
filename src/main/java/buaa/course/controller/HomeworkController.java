package buaa.course.controller;

import buaa.course.model.Assignment;
import buaa.course.model.Homework;
import buaa.course.model.User;
import buaa.course.service.AssignmentService;
import buaa.course.service.HomeworkService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class HomeworkController {
    @Resource(name = "homeworkService")
    private HomeworkService homeworkService;
    @Resource(name = "assignmentService")
    private AssignmentService assignmentService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/homeworks/{assignmentId}")
    public ModelAndView homeworksGet(@PathVariable Integer assignmentId, HttpServletRequest request) {
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()==0)
    		return new ModelAndView("login");*/
    	ModelAndView m= new ModelAndView("homeworks");
    	if(assignmentId!=null){
    		m.addObject("homeworklist",homeworkService.getHomeworksByAssignmentId(assignmentId));
    	}
    	return m;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/correcthomework/{homeworkId}")
    public ModelAndView correctHomeworkGet(@PathVariable Integer homeworkId, HttpServletRequest request){
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");*/
    	ModelAndView m= new ModelAndView("correcthomework");
    	Homework homework = null;
    	if(homeworkId!=null){
    		homework = homeworkService.getHomeworkById(homeworkId);
    	}
    	if(homework==null){
    		return new ModelAndView("index");
    	}
    	else{
    		m.addObject("homeworkId",homeworkId);
        	m.addObject("homework",homework);
        	return m;
    	}
    }

    @RequestMapping(method = RequestMethod.POST, value = "/correcthomework/{homeworkId}")
    public ModelAndView correctHomeworkPost(@PathVariable Integer homeworkId, HttpServletRequest request, HttpServletResponse response) throws IOException{
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");*/
        ModelAndView m = new ModelAndView("correcthomework");
        String score_s = request.getParameter("score");
        String comment = request.getParameter("comment");
        Homework homework = null;
        if(homeworkId!=null)
        	homework = homeworkService.getHomeworkById(homeworkId);
    	if(homework==null)
    		return new ModelAndView("index");
    	m.addObject("homework", homework);
        int score = 0;
        try{
        	score = Integer.valueOf(score_s);
        }
        catch(NumberFormatException e){
        	m.addObject("error", "分数形式不合法");
        	return m;
        }
        if(score<0||score>homeworkService.getHighestScore(homeworkId)){
        	m.addObject("error", "分数不在允许区间内");
        	return m;
        }
        homework.setScore(score);
        homework.setComment(comment);
        homeworkService.updateHomework(homework);
        if(true){
        	response.sendRedirect("/homeworks/"+homework.getAssignmentId());        	
        }
        return null;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/submithomework/{assignmentId}")
    public ModelAndView submitHomeworkGet(@PathVariable Integer assignmentId, HttpServletRequest request){
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=0)
    		return new ModelAndView("login");*/
    	ModelAndView m = new ModelAndView("submithomework");
    	Assignment assignment = null;
    	if(assignmentId!=null){
    		assignment = assignmentService.getAssignmentById(assignmentId);
    	}
    	if(assignment==null){
    		return new ModelAndView("index");
    	}
    	else{
    		m.addObject("assignmentId", assignmentId);
    		m.addObject("assignment",assignment);
    		return m;
    	}
    }

    @RequestMapping(method = RequestMethod.POST, value = "/submithomework/{assignmentId}")
    public ModelAndView submitHomeworkPost(@PathVariable Integer assignmentId, HttpServletRequest request, HttpServletResponse response) throws IOException {
/*    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=0)
    		return new ModelAndView("login");*/
    	ModelAndView index = new ModelAndView("index");
    	if(assignmentId==null)
    		return index;
    	Assignment assignment = assignmentService.getAssignmentById(assignmentId);
    	if(assignment==null)
    		return index;
    	int semesterCourseId = assignment.getSemesterCourseId();
    	if(semesterCourseId==0)
    		return index;
    	ModelAndView submithomework = new ModelAndView("submithomework");
    	String text = request.getParameter("text");
    	if(StringUtils.isNullOrEmpty(text)){
    		submithomework.addObject("assignment", assignment);
    		
        	submithomework.addObject("error", "内容不能为空");
        	return submithomework;
        }
    	Timestamp submitTime = new Timestamp(System.currentTimeMillis());
        Homework homework = new Homework();
        homework.setSemesterCourseId(semesterCourseId);
        homework.setStudentId(1);
        homework.setAssignmentId(assignmentId);
        homework.setText(text);
        homework.setFileUrl("1");
        homework.setSubmitTime(submitTime);
        homeworkService.createHomework(homework);
        if(true){
        	response.sendRedirect("/assignments/"+semesterCourseId);        	
        }
        return null;
    }
}
