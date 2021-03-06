package buaa.course.controller;

import buaa.course.model.Assignment;
import buaa.course.model.Course;
import buaa.course.model.Homework;
import buaa.course.model.SemesterCourse;
import buaa.course.model.User;
import buaa.course.service.*;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
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
	@Resource(name = "teamService")
	private TeamService teamService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/homeworks/{assignmentId}")
    public ModelAndView homeworksGet(@PathVariable Integer assignmentId, HttpServletRequest request) {
    	User user = (User)request.getSession().getAttribute("user");
    	if(user==null||user.getType()!=1)
    		return new ModelAndView("login");
    	ModelAndView homeworks = new ModelAndView("assignment/homeworks");
    	ModelAndView index = new ModelAndView("index");
    	if(assignmentId==null)
    		return index;
    	List<Homework> homeworklist = homeworkService.getHomeworksByAssignmentId(assignmentId);
    	if(homeworklist==null)
    		return index;
    	Assignment assignment = assignmentService.getAssignmentById(assignmentId);
    	Map<Long,User> studentlist =userService.getUsersMap(homeworklist);
    	homeworks.addObject("assignment",assignment);
    	homeworks.addObject("homeworklist",homeworklist);
    	homeworks.addObject("studentlist",studentlist);
		homeworks.addObject("course", courseService.getCourseBySemesterCourseId(assignmentService.getAssignmentById(assignmentId).getSemesterCourseId()));
    	return homeworks;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/correct/{homeworkId}")
    public ModelAndView correctHomeworkGet(@PathVariable Integer homeworkId, HttpServletRequest request){
    	ModelAndView m= new ModelAndView("assignment/correct");
    	Homework homework = null;
    	if(homeworkId!=null)
    		homework = homeworkService.getHomeworkById(homeworkId);
    	if(homework==null)
    		return new ModelAndView("index");
    	Assignment assignment = assignmentService.getAssignmentById(homework.getAssignmentId());
    	Course course = courseService.getCourseBySemesterCourseId(assignment.getSemesterCourseId());
    	User student = userService.getUserByNum(homework.getStudentId());
    	SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(assignment.getSemesterCourseId());
    	m.addObject("homeworkId",homeworkId);
        m.addObject("homework",homework);
        m.addObject("assignment", assignment);
        m.addObject("course", course);
        m.addObject("student", student);
        m.addObject("semesterId", semesterCourse.getSemesterId());
        m.addObject("semesterCourseId", semesterCourse.getId());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        m.addObject("currentTime",currentTime);
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/assignment/correct/{homeworkId}")
    public ModelAndView correctHomeworkPost(@PathVariable Integer homeworkId, @RequestParam("files") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws IOException{
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
			Assignment assignment = assignmentService.getAssignmentById(homework.getAssignmentId());
			Course course = courseService.getCourseBySemesterCourseId(assignment.getSemesterCourseId());
			User student = userService.getUserByNum(homework.getStudentId());
			m.addObject("homeworkId",homeworkId);
			m.addObject("homework",homework);
			m.addObject("assignment", assignment);
			m.addObject("course", course);
			m.addObject("student", student);
        	m.addObject("illegalScore", "分数形式不合法");
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.addObject("currentTime",currentTime);
        	return m;
        }
        if(score<0||(score>homeworkService.getHighestScore(homeworkId)&&homeworkService.getHighestScore(homeworkId)>0)){
        	m.addObject("scoreOutOfRange", "分数不在允许区间内");
			Assignment assignment = assignmentService.getAssignmentById(homework.getAssignmentId());
			Course course = courseService.getCourseBySemesterCourseId(assignment.getSemesterCourseId());
			User student = userService.getUserByNum(homework.getStudentId());
			m.addObject("homeworkId",homeworkId);
			m.addObject("homework",homework);
			m.addObject("assignment", assignment);
			m.addObject("course", course);
			m.addObject("student", student);
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        m.addObject("currentTime",currentTime);
        	return m;
        }
		if(StringUtils.isEmptyOrWhitespaceOnly(comment)){
			m.addObject("noComment", "评价不能为空!");
			Assignment assignment = assignmentService.getAssignmentById(homework.getAssignmentId());
			Course course = courseService.getCourseBySemesterCourseId(assignment.getSemesterCourseId());
			User student = userService.getUserByNum(homework.getStudentId());
			m.addObject("homeworkId",homeworkId);
			m.addObject("homework",homework);
			m.addObject("assignment", assignment);
			m.addObject("course", course);
			m.addObject("student", student);
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        m.addObject("currentTime",currentTime);
			return m;
		}
		String fileUrl = null;
    	for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = getResourcePath_correct(homework.getAssignmentId(),homeworkId,request);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 转存文件
                try {
                    File temp = new File(filePath + File.separator + file.getOriginalFilename());
                    file.transferTo(temp);
                    fileUrl = getServerPath_correct(homework.getAssignmentId(),homeworkId,file.getOriginalFilename(),request);
                } catch (Exception e) {
                    e.printStackTrace();
                    ModelAndView c = new ModelAndView("assignment/correct");
					c.addObject("message", e.getMessage());
                    return c;
                }
            }
        }
        homework.setCorrectFileUrl(fileUrl);
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
    	Course course=null;
		User user = (User)request.getSession().getAttribute("user");
    	ModelAndView m = new ModelAndView("assignment/submit");
    	ModelAndView index = new ModelAndView("index");
    	Assignment assignment = null;
    	if(assignmentId==null)
    		return index;
    	assignment = assignmentService.getAssignmentById(assignmentId);
    	if(assignment==null)
    		return index;
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    	if(assignment.getStartTime().after(currentTime)){
    		ModelAndView notYet = new ModelAndView("assignment/student_assignments");
    		int semesterCourseId = assignmentService.getAssignmentById(assignmentId).getSemesterCourseId();
    		List<Assignment> assignmentlist = assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId);
    		Map<Long, Homework> homeworks = homeworkService.getHomeworksByAssignments(assignmentlist, user.getNum());
    		notYet.addObject("assignmentlist", assignmentlist);
    		notYet.addObject("homeworks", homeworks);
    		notYet.addObject("course", courseService.getCourseBySemesterCourseId(semesterCourseId));
    		notYet.addObject("currentTime", currentTime);
    		return notYet;
    	}
    	course = courseService.getCourseBySemesterCourseId(assignmentService.getAssignmentById(assignmentId).getSemesterCourseId());
    	m.addObject("assignmentId", assignmentId);
    	m.addObject("assignment",assignment);
		m.addObject("semester", semesterService.getSemesterById(2));
		Homework homework = homeworkService.getHomeworkByAssignment(assignmentId, user.getNum());
		m.addObject("homework", homework);
		if(homework != null){
			m.addObject("submitter", userService.getUserByNum(homework.getStudentId()).getName());
			if(assignment.isTeamAvaliable()){
				m.addObject("teamLeaderId", teamService.getTeamByStudent(assignment.getSemesterCourseId(), homework.getStudentId()).getLeaderId());
			}
		}
		m.addObject("course",course);
		m.addObject("semesterCourseId", courseService.getSemesterCourseBySemesterCourseId(assignmentService.getAssignmentById(assignmentId).getSemesterCourseId()).getId());
		m.addObject("currentTime", currentTime);
		return m;
    }

	@RequestMapping(method = RequestMethod.GET, value = "/assignment/updateHomework/{homeworkId}")
	public ModelAndView updateHomeworkGet(@PathVariable Integer homeworkId, HttpServletRequest request){
		Course course=null;
		User user = (User)request.getSession().getAttribute("user");
		ModelAndView m = new ModelAndView("assignment/updateHomework");
		ModelAndView index = new ModelAndView("index");
		Homework homework = null;
		if(homeworkId==null)
			return index;
		homework = homeworkService.getHomeworkById(homeworkId);
		if(homework==null)
			return index;
		Assignment assignment = assignmentService.getAssignmentById(homework.getAssignmentId());
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		if(assignment.getStartTime().after(currentTime)){
			ModelAndView notYet = new ModelAndView("assignment/student_assignments");
			int semesterCourseId = assignment.getSemesterCourseId();
			List<Assignment> assignmentlist = assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId);
			Map<Long, Homework> homeworks = homeworkService.getHomeworksByAssignments(assignmentlist, user.getNum());
			notYet.addObject("assignmentlist", assignmentlist);
			notYet.addObject("homeworks", homeworks);
			notYet.addObject("course", courseService.getCourseBySemesterCourseId(semesterCourseId));
			notYet.addObject("currentTime", currentTime);
			return notYet;
		}
		course = courseService.getCourseBySemesterCourseId(assignmentService.getAssignmentById(assignment.getId()).getSemesterCourseId());
		m.addObject("assignmentId", assignment.getId());
		m.addObject("assignment",assignment);
		m.addObject("semester", semesterService.getSemesterById((Integer)request.getSession().getAttribute("semesterId")));

		m.addObject("homework", homework);
		if(homework != null){
			m.addObject("submitter", userService.getUserByNum(homework.getStudentId()).getName());
			if(assignment.isTeamAvaliable()){
				m.addObject("teamLeaderId", teamService.getTeamByStudent(assignment.getSemesterCourseId(), homework.getStudentId()).getLeaderId());
			}
		}
		m.addObject("course",course);
		m.addObject("semesterCourseId", courseService.getSemesterCourseBySemesterCourseId(assignmentService.getAssignmentById(assignment.getId()).getSemesterCourseId()).getId());
		m.addObject("currentTime", currentTime);
		return m;
	}

    @RequestMapping(method = RequestMethod.POST, value = "/assignment/submit/{assignmentId}")
    public ModelAndView submitHomeworkPost(@PathVariable Integer assignmentId, @RequestParam("files") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    	if(!StringUtils.isEmptyOrWhitespaceOnly(request.getParameter("delete"))){
    		homeworkService.deleteHomework(Integer.valueOf(request.getParameter("homeworkId")));
    		ModelAndView m = new ModelAndView("assignment/student_assignments");
    		m.addObject("semesterCourseId", semesterCourseId);
    		m.addObject("course", courseService.getCourseBySemesterCourseId(semesterCourseId));
    		List<Assignment> assignmentlist = assignmentService.getAssignmentsBySemesterCourseId(semesterCourseId);
			Map<Long, Homework> homeworks = homeworkService.getHomeworksByAssignments(assignmentlist, user.getNum());
			m.addObject("homeworks", homeworks);
    		m.addObject("assignmentlist", assignmentlist);
    		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    		m.addObject("currentTime", currentTime);
    		return m;
    	}
    	String text = request.getParameter("text");
    	if(StringUtils.isEmptyOrWhitespaceOnly(text)){
    		Course course = courseService.getCourseBySemesterCourseId(assignmentService.getAssignmentById(assignmentId).getSemesterCourseId());
    		submithomework.addObject("assignment", assignment);
    		submithomework.addObject("course", course);
    		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    		submithomework.addObject("currentTime", currentTime);
        	submithomework.addObject("error", "内容不能为空");
        	return submithomework;
        }
    	Timestamp submitTime = new Timestamp(System.currentTimeMillis());
        Homework homework = new Homework();
        homework.setSemesterCourseId(semesterCourseId);
        homework.setStudentId(user.getNum());
        homework.setAssignmentId(assignmentId);
        homework.setText(text);
        homework.setSubmitTime(submitTime);
        homework.setScore(-1);
        homeworkService.createHomework(homework);

		//团队作业相关
		if(assignment.isTeamAvaliable()){
			homeworkService.submitTeamHomework(assignment, homework);
		}

    	String fileUrl = null;
    	int homeworkId = homework.getId();
    	for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = getResourcePath(assignmentId,homeworkId,request);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 转存文件
                try {
                    File temp = new File(filePath + File.separator + file.getOriginalFilename());
                    file.transferTo(temp);
                    fileUrl = getServerPath(assignmentId,homeworkId,file.getOriginalFilename(),request);
                } catch (Exception e) {
                    e.printStackTrace();
                    ModelAndView m = new ModelAndView("assignment/submit");
					m .addObject("message", e.getMessage());
                    return m;
                }
            }
        }
        homework.setFileUrl(fileUrl);
        homeworkService.updateHomework(homework);
        if(true){
        	response.sendRedirect("/assignment/assignments/"+semesterCourseId);        	
        }
        return null;
    }

	@RequestMapping(method = RequestMethod.POST, value = "/assignment/updateHomework/{homeworkId}")
	public ModelAndView updateHomeworkPost(@RequestParam("files") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response, @PathVariable Integer homeworkId) throws IOException {
		User user = (User)request.getSession().getAttribute("user");
		if(user==null||user.getType()!=0)
			return new ModelAndView("login");
		ModelAndView index = new ModelAndView("index");
		if(homeworkId==null)
			return index;
		Homework homework = homeworkService.getHomeworkById(homeworkId);

		if(homework==null)
			return index;
		Assignment assignment = assignmentService.getAssignmentById(homework.getAssignmentId());
		int semesterCourseId = assignment.getSemesterCourseId();

		ModelAndView updateHomework = new ModelAndView("assignment/updateHomework");

		String text = request.getParameter("text");
		if(StringUtils.isEmptyOrWhitespaceOnly(text)){
			Course course = courseService.getCourseBySemesterCourseId(assignmentService.getAssignmentById(assignment.getId()).getSemesterCourseId());
			updateHomework.addObject("assignment", assignment);
			updateHomework.addObject("course", course);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			updateHomework.addObject("currentTime", currentTime);
			updateHomework.addObject("error", "内容不能为空");
			return updateHomework;
		}
		Timestamp submitTime = new Timestamp(System.currentTimeMillis());

		homework.setSemesterCourseId(semesterCourseId);
		homework.setStudentId(user.getNum());
		homework.setAssignmentId(assignment.getId());
		homework.setText(text);
		homework.setSubmitTime(submitTime);
		homework.setScore(-1);

		String fileUrl = null;

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				// 文件保存路径
				String filePath = getResourcePath(assignment.getId(),homeworkId,request);
				File dir = new File(filePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// 转存文件
				try {
					File temp = new File(filePath + File.separator + file.getOriginalFilename());
					file.transferTo(temp);
					fileUrl = getServerPath(assignment.getId(),homeworkId,file.getOriginalFilename(),request);
				} catch (Exception e) {
					e.printStackTrace();
					ModelAndView m = new ModelAndView("assignment/submit");
					m .addObject("message", e.getMessage());
					return m;
				}
			}
		}
		homework.setFileUrl(fileUrl);
		homeworkService.updateHomework(homework);
		if(true){
			response.sendRedirect("/assignment/assignments/"+semesterCourseId);
		}
		return null;
	}

	private String getResourcePath(Integer assignmentId, Integer homeworkId, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("/")
                + "resource" + File.separator + "assignment-" + assignmentId + File.separator + "homework-" +homeworkId;
        return filePath;
	}
	
	private String getServerPath(Integer assignmentId, Integer homeworkId, String originalFilename,HttpServletRequest request) {		
		String dir = getServerDir(assignmentId, homeworkId, request);
		return dir+originalFilename;
	}

	private String getServerDir(Integer assignmentId, Integer homeworkId, HttpServletRequest request) {
		return "/"+request.getContextPath()+"resource/"+ "assignment-" + assignmentId +"/homework-" +homeworkId+"/";
	}
	
	private String getResourcePath_correct(Integer assignmentId, Integer homeworkId, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("/")
                + "resource" + File.separator + "assignment-" + assignmentId + File.separator + "homeworkcorrect-" +homeworkId;
        return filePath;
	}
	
	private String getServerPath_correct(Integer assignmentId, Integer homeworkId, String originalFilename,HttpServletRequest request) {		
		String dir = getServerDir_correct(assignmentId, homeworkId, request);
		return dir+originalFilename;
	}
	
	private String getServerDir_correct(Integer assignmentId, Integer homeworkId, HttpServletRequest request) {
		return "/"+request.getContextPath()+"resource/"+ "assignment-" + assignmentId +"/homeworkcorrect-" +homeworkId+"/";
	}
}
