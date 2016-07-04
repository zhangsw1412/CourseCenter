package buaa.course.controller;

import buaa.course.service.HomeworkService;
import buaa.course.model.Homework;
import buaa.course.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    
    @RequestMapping(method = RequestMethod.GET, value = "/correcthomework/{homeworkId}")
    public ModelAndView correctHomeworkGet(@PathVariable Integer homeworkId, HttpServletRequest request){
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("index");
	    Homework homework = homeworkService.getHomeworkById(homeworkId);
	    ModelAndView m= new ModelAndView("correcthomework");
	    m.addObject("homework",homework);
	    return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/correcthomework/{homeworkId}")
    public ModelAndView correctHomeworkPost(@PathVariable Integer homeworkId, HttpServletRequest request) throws IOException {
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("index");
        ModelAndView m = new ModelAndView("index");
        String score_s = request.getParameter("userId");
        String comment = request.getParameter("comment");
        Homework homework = homeworkService.getHomeworkById(homeworkId);
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
        return new ModelAndView("index");
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/submithomework/{assignmentId}")
    public ModelAndView submitHomeworkGet(@PathVariable Integer assignmentId, HttpServletRequest request){
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=0)
    		return new ModelAndView("/index");
    	return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/submithomework/{assignmentId}")
    public ModelAndView submitHomeworkPost(@PathVariable Integer assignmentId, HttpServletRequest request) throws IOException {
    	User user = (User)request.getSession().getAttribute("user");
    	int semesterCourseId = assignmentService.getAssignment(assignmentId).getSemesterCourseId();
    	String text = request.getParameter("text");
    	Timestamp submitTime = new Timestamp(System.currentTimeMillis());
    	if(user==null||user.getType()!=0)
    		return new ModelAndView("index");
        ModelAndView m = new ModelAndView("index");
        String score_s = request.getParameter("userId");
        String comment = request.getParameter("comment");
        Homework homework = new Homework();
        homework.setSemesterCourseId(semesterCourseId);
        homework.setStudentId(user.getNum());
        homework.setAssignmentId(assignmentId);
        homework.setText(text);
        homework.setSubmitTime(submitTime);
        homeworkService.updateHomework(homework);
        return new ModelAndView("index");
    }
}
