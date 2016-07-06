package buaa.course.controller;

import buaa.course.model.Assignment;
import buaa.course.model.Course;
import buaa.course.model.Homework;
import buaa.course.model.User;
import buaa.course.service.*;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class HomeworkController {
    @Resource(name = "homeworkService")
    private HomeworkService homeworkService;
    @Resource(name = "assignmentService")
    private AssignmentService assignmentService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "courseService")
    private CourseService courseService;
	@Resource(name = "semesterService")
	private SemesterService semesterService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/homeworks/{assignmentId}")
    public ModelAndView homeworksGet(@PathVariable Integer assignmentId, HttpServletRequest request) {
    	User user = (User)request.getSession().getAttribute("user");
    	List<Course> courses;
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	ModelAndView homeworks = new ModelAndView("assignment/homeworks");
    	ModelAndView index = new ModelAndView("index");
    	if(assignmentId==null)
    		return index;
    	List<Homework> homeworklist = homeworkService.getHomeworksByAssignmentId(assignmentId);
    	if(homeworklist==null)
    		return index;
    	Map<Long,User> studentlist =userService.getUsersMap(homeworklist);
    	courses = courseService.getCoursesByTeacher(2, user.getNum());
    	homeworks.addObject("homeworklist",homeworklist);
    	homeworks.addObject("studentlist",studentlist);
    	homeworks.addObject("courses", courses);
		homeworks.addObject("semester", semesterService.getSemesterById(2));
    	return homeworks;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/correct/{homeworkId}")
    public ModelAndView correctHomeworkGet(@PathVariable Integer homeworkId, HttpServletRequest request){
    	User user = (User)request.getSession().getAttribute("user");
    	List<Course> courses;
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	ModelAndView m= new ModelAndView("assignment/correct");
    	Homework homework = null;
    	if(homeworkId!=null)
    		homework = homeworkService.getHomeworkById(homeworkId);
    	if(homework==null)
    		return new ModelAndView("index");
    	Assignment assignment = assignmentService.getAssignmentById(homework.getAssignmentId());
    	Course course = courseService.getCourseBySemesterCourseId(assignment.getSemesterCourseId());
    	User student = userService.getUserByNum(homework.getStudentId());
    	courses = courseService.getCoursesByTeacher(2, user.getNum());
    	m.addObject("homeworkId",homeworkId);
        m.addObject("homework",homework);
        m.addObject("assignment", assignment);
        m.addObject("course", course);
        m.addObject("student", student);
        m.addObject("courses", courses);
		m.addObject("semester", semesterService.getSemesterById(2));
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/assignment/correct/{homeworkId}")
    public ModelAndView correctHomeworkPost(@PathVariable Integer homeworkId, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
        ModelAndView m = new ModelAndView("assignment/correct");
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
        	response.sendRedirect("/assignment/homeworks/"+homework.getAssignmentId());        	
        }
        return null;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/submit/{assignmentId}")
    public ModelAndView submitHomeworkGet(@PathVariable Integer assignmentId, HttpServletRequest request){
    	User user = (User)request.getSession().getAttribute("user");
    	List<Course> courses;
    	Course course=null;
    	if(user==null||user.getType()!=0)
    		return new ModelAndView("login");
    	ModelAndView m = new ModelAndView("assignment/submit");
    	ModelAndView index = new ModelAndView("index");
    	Assignment assignment = null;
    	if(assignmentId==null)
    		return index;
    	assignment = assignmentService.getAssignmentById(assignmentId);
    	if(assignment==null)
    		return index;
    	courses = courseService.getCoursesByStudent(2, user.getNum());
    	course = courseService.getCourseBySemesterCourseId(assignmentService.getAssignmentById(assignmentId).getSemesterCourseId());
    	m.addObject("assignmentId", assignmentId);
    	m.addObject("assignment",assignment);
    	m.addObject("courses", courses);
		m.addObject("semester", semesterService.getSemesterById(2));		m.addObject("course",course);    	return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/assignment/submit/{assignmentId}")
    public ModelAndView submitHomeworkPost(@PathVariable Integer assignmentId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=0)
    		return new ModelAndView("login");
    	ModelAndView index = new ModelAndView("index");
    	if(assignmentId==null)
    		return index;
    	Assignment assignment = assignmentService.getAssignmentById(assignmentId);
    	if(assignment==null)
    		return index;
    	int semesterCourseId = assignment.getSemesterCourseId();
    	if(semesterCourseId==0)
    		return index;
    	ModelAndView submithomework = new ModelAndView("assignment/submit");
    	String text = request.getParameter("text");
    	if(StringUtils.isNullOrEmpty(text)){
    		submithomework.addObject("assignment", assignment);
    		
        	submithomework.addObject("error", "内容不能为空");
        	return submithomework;
        }
    	Timestamp submitTime = new Timestamp(System.currentTimeMillis());
        Homework homework = new Homework();
        homework.setSemesterCourseId(semesterCourseId);
        homework.setStudentId(user.getNum());
        homework.setAssignmentId(assignmentId);
        homework.setText(text);
        homework.setFileUrl("1");
        homework.setSubmitTime(submitTime);
        homeworkService.createHomework(homework);
        if(true){
        	response.sendRedirect("/assignment/assignments/"+semesterCourseId);        	
        }
        return null;
    }
}
