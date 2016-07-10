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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
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
		ModelAndView m = new ModelAndView();
		List<Assignment> assignmentlist = assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId);
    	if(user.getType() == 0){
    		m = new ModelAndView("assignment/student_assignments");
    		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			Map<Long, Homework> homeworks = homeworkService.getHomeworksByAssignments(assignmentlist, user.getNum());
			m.addObject("homeworks", homeworks);
			m.addObject("currentTime", currentTime);
		}else if(user.getType() == 1){
			m = new ModelAndView("assignment/teacher_assignments");
		}
    	if(semesterCourseId!=null){
    		m.addObject("assignmentlist", assignmentlist);
    	}
		m.addObject("course", courseService.getCourseBySemesterCourseId(semesterCourseId));
    	int semesterId = courseService.getSemesterCourseBySemesterCourseId(semesterCourseId).getSemesterId();
    	m.addObject("semesterId",semesterId);
    	return m;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/assign/{semesterCourseId}")
    public ModelAndView assignGet(@PathVariable Integer semesterCourseId, HttpServletRequest request) {
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	if(semesterCourseId==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	if(course==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    	Timestamp toTime = new Timestamp(System.currentTimeMillis()+604800000);
    	ModelAndView m = new ModelAndView("assignment/assign");
    	m.addObject("course",course);
    	m.addObject("semesterCourseId", semesterCourseId);
    	m.addObject("currentTime", currentTime.toString().substring(0,16));
    	m.addObject("toTime", toTime.toString().substring(0, 16));
    	return m;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/assignment/assign/{semesterCourseId}")
    public ModelAndView assignPost(@PathVariable Integer semesterCourseId, @RequestParam("files") MultipartFile[] files, MultipartHttpServletRequest  request){
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	if(semesterCourseId==null)
    		return new ModelAndView("assignment/teacher_assignments");
    	Course course = courseService.getCourseBySemesterCourseId(semesterCourseId);
    	ModelAndView m = new ModelAndView("assignment/assign");
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    	Timestamp toTime = new Timestamp(System.currentTimeMillis()+604800000);
    	m.addObject("currentTime", currentTime.toString().substring(0,16));
    	m.addObject("toTime", toTime.toString().substring(0, 16));
    	m.addObject("course",course);
    	m.addObject("semesterCourseId",semesterCourseId);
    	String name = request.getParameter("name");
    	if(StringUtils.isNullOrEmpty(name)){
        	m.addObject("error", "作业名称不能为空");
        	return m;
        }
    	
    	String basicRequirement = request.getParameter("basicrequirement");
    	if(StringUtils.isNullOrEmpty(basicRequirement)){
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
        	startTime = Timestamp.valueOf(startTime_s+":00");
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
        	deadline = Timestamp.valueOf(deadline_s+":00");
    	}
    	catch(Exception e){
        	m.addObject("error", "截止时间不合法");
        	return m;
    	}
    	if(startTime.after(deadline)){
        	m.addObject("error", "截止时间须晚于开始时间");
        	return m;
    	}
    	String teamAvaliable_s = request.getParameter("teamavaliable");
    	boolean teamAvaliable;
    	teamAvaliable = teamAvaliable_s==null?false:true;
    	String highestScore_s = request.getParameter("highestscore");
    	int highestScore;
    	if(StringUtils.isNullOrEmpty(highestScore_s)){
    		highestScore = -1;
    	}
    	else{
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
    	}
    	Assignment assignment = new Assignment();
    	assignment.setSemesterCourseId(semesterCourseId);
    	assignment.setName(name);
    	assignment.setBasicRequirement(basicRequirement);
    	assignment.setStartTime(startTime);
    	assignment.setDeadline(deadline);
    	assignment.setTeamAvaliable(teamAvaliable);
    	assignment.setHighestScore(highestScore);
    	assignmentService.createAssignment(assignment);
    	int assignmentId = assignment.getId();
    	String fileUrl = null;
    	for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = getResourcePath(semesterCourseId,assignmentId, request);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 转存文件
                try {
                    File temp = new File(filePath + File.separator + file.getOriginalFilename());
                    file.transferTo(temp);
                    fileUrl = getServerPath(semesterCourseId,assignmentId,file.getOriginalFilename(),request);
                } catch (Exception e) {
                    e.printStackTrace();
                    m.addObject("message", e.getMessage());
                    return m;
                }
            }
        }
    	assignment.setFileUrl(fileUrl);
    	assignmentService.updateAssignment(assignment);
    	ModelAndView assignments = new ModelAndView("assignment/teacher_assignments");
    	assignments.addObject("assignmentlist",assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId));
    	return assignments;
    }

	private String getServerPath(Integer semesterCourseId, Integer assignmentId, String originalFilename,HttpServletRequest request) {		
		String dir = getServerDir(semesterCourseId, assignmentId, request);
		return dir+originalFilename;
	}

	private String getServerDir(Integer semesterCourseId, Integer assignmentId, HttpServletRequest request) {
		return "/"+request.getContextPath()+"resource/"+ "semestercourse-" + semesterCourseId +"/assignment-" +assignmentId+"/";
	}

	private String getResourcePath(Integer semesterCourseId, Integer assignmentId, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("/")
                + "resource" + File.separator + "semestercourse-" + semesterCourseId + File.separator + "assignment-" +assignmentId;
        return filePath;
	}
    
}
