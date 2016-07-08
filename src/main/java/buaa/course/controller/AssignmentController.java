package buaa.course.controller;

import buaa.course.model.Assignment;
import buaa.course.model.Course;
import buaa.course.model.Homework;
import buaa.course.model.User;
import buaa.course.service.AssignmentService;
import buaa.course.service.CourseService;
import buaa.course.service.HomeworkService;
import buaa.course.service.SemesterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class AssignmentController {
    @Resource(name = "assignmentService")
    private AssignmentService assignmentService;
    
    @Resource(name = "courseService")
    private CourseService courseService;

	@Resource(name = "semesterService")
	private SemesterService semesterService;

	@Resource(name = "homeworkService")
	private HomeworkService homeworkService;

    @RequestMapping(method = RequestMethod.GET, value = "/assignment/assignments/{semesterCourseId}")
    public ModelAndView assignmentsGet(@PathVariable Integer semesterCourseId, HttpServletRequest request) {
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()==2)
    		return new ModelAndView("login");
		List<Course> courses = new ArrayList<>();
		ModelAndView m = new ModelAndView();
		List<Assignment> assignmentlist = assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId);
    	if(user.getType() == 0){
    		m = new ModelAndView("assignment/student_assignments");
    		courses = courseService.getCoursesByStudent(2, user.getNum());
			Map<Long, Homework> homeworks = homeworkService.getHomeworksByAssignments(assignmentlist, user.getNum());
			m.addObject("homeworks", homeworks);
		}else if(user.getType() == 1){
			m = new ModelAndView("assignment/teacher_assignments");
			courses = courseService.getCoursesByTeacher(2, user.getNum());
		}

    	if(semesterCourseId!=null){
    		m.addObject("assignmentlist", assignmentlist);
    	}
		m.addObject("courses", courses);
		m.addObject("course", courseService.getCourseBySemesterCourseId(semesterCourseId));
		m.addObject("semester", semesterService.getSemesterById(2));


    	return m;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/assign/{semesterCourseId}")
    public ModelAndView assignGet(@PathVariable Integer semesterCourseId, HttpServletRequest request) {
    	User user = (User)request.getSession().getAttribute("user");
    	List<Course> courses;
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	if(semesterCourseId==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	if(course==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	courses = courseService.getCoursesByTeacher(2, user.getNum());
    	ModelAndView m = new ModelAndView("assignment/assign");
    	m.addObject("course",course);
		m.addObject("courses", courses);
		m.addObject("semester", semesterService.getSemesterById(2));
    	m.addObject("semesterCourseId", semesterCourseId);
    	return m;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/assignment/assign/{semesterCourseId}")
    public ModelAndView assignPost(@PathVariable Integer semesterCourseId, @RequestParam("files") MultipartFile[] files, HttpServletRequest request){
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	if(semesterCourseId==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	ModelAndView m = new ModelAndView("assignment/teacher_assign");
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
    	
    	String fileUrl = null;
    	for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = getResourcePath(semesterCourseId, request);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 转存文件
                try {
                    File temp = new File(filePath + File.separator + file.getOriginalFilename());
                    file.transferTo(temp);
                    fileUrl = filePath + File.separator + file.getOriginalFilename();
                } catch (Exception e) {
                    e.printStackTrace();
                    m.addObject("message", e.getMessage());
                    return m;
                }
            }
        }
    	
    	Assignment assignment = new Assignment();
    	assignment.setSemesterCourseId(semesterCourseId);
    	assignment.setName(name);
    	assignment.setBasicRequirement(basicRequirement);
    	assignment.setFileUrl(fileUrl);
    	assignment.setStartTime(startTime);
    	assignment.setDeadline(deadline);
    	assignment.setTeamAvaliable(teamAvaliable);
    	assignment.setHighestScore(highestScore);
    	assignmentService.createAssignment(assignment);
    	ModelAndView assignments = new ModelAndView("assignment/teacher_assignments");
    	assignments.addObject("assignmentlist",assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId));
		m.addObject("semester", semesterService.getSemesterById(2));
    	return assignments;
    }

	private String getResourcePath(Integer semesterCourseId, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("/")
                + "resource" + File.separator + "semestercourse-" + semesterCourseId;
        return filePath;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test/assignTest/{semesterCourseId}")
    public ModelAndView assignTestGet(@PathVariable Integer semesterCourseId, HttpServletRequest request) {
    	User user = (User)request.getSession().getAttribute("user");
    	List<Course> courses;
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	if(semesterCourseId==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	if(course==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	courses = courseService.getCoursesByTeacher(2, user.getNum());
    	ModelAndView m = new ModelAndView("test/assignTest");
    	m.addObject("course",course);
		m.addObject("courses", courses);
		m.addObject("semester", semesterService.getSemesterById(2));

    	m.addObject("semesterCourseId", semesterCourseId);
    	return m;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/test/assignTest/{semesterCourseId}")
    public ModelAndView assignTestPost(@PathVariable Integer semesterCourseId, @RequestParam("files") MultipartFile[] files, HttpServletRequest request){
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	if(semesterCourseId==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	ModelAndView m = new ModelAndView("test/assignTest");
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
    	
    	String fileUrl = null;
    	for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = getResourcePath(semesterCourseId, request);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 转存文件
                try {
                    File temp = new File(filePath + File.separator + file.getOriginalFilename());
                    file.transferTo(temp);
                    fileUrl = filePath + File.separator + file.getOriginalFilename();
                } catch (Exception e) {
                    e.printStackTrace();
                    m.addObject("message", e.getMessage());
                    return m;
                }
            }
        }
    	
    	Assignment assignment = new Assignment();
    	assignment.setSemesterCourseId(semesterCourseId);
    	assignment.setName(name);
    	assignment.setBasicRequirement(basicRequirement);
    	assignment.setFileUrl(fileUrl);
    	assignment.setStartTime(startTime);
    	assignment.setDeadline(deadline);
    	assignment.setTeamAvaliable(teamAvaliable);
    	assignment.setHighestScore(highestScore);
    	assignmentService.createAssignment(assignment);
    	ModelAndView assignments = new ModelAndView("assignment/teacher_assignments");
    	assignments.addObject("assignmentlist",assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId));
		m.addObject("semester", semesterService.getSemesterById(2));
    	return assignments;
    }
    
}
