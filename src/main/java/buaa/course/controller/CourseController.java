package buaa.course.controller;

import buaa.course.model.Course;
import buaa.course.model.Semester;
import buaa.course.model.SemesterCourse;
import buaa.course.model.User;
import buaa.course.service.CourseService;
import buaa.course.service.ResourceService;
import buaa.course.service.SemesterService;
import buaa.course.utils.PagingUtil;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
@Controller
public class CourseController {
    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "semesterService")
    private SemesterService semesterService;

    @Resource(name = "resourceService")
    private ResourceService resourceService;

    @Resource(name = "pagingUtil")
    private PagingUtil pagingUtil;

    @RequestMapping(method = RequestMethod.GET, value = "/semester/{semesterId}/courseList")
    public ModelAndView courseList(@PathVariable Integer semesterId, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("course/teacher_course");;
        if (semesterId != null) {
            User user = (User) request.getSession().getAttribute("user");
            System.out.println(user);
            if (user.getType() == 1 || user.getType() == 0) {//教师访问课程列表
                List<Course> courses = new ArrayList<>();
                if(user.getType() == 1){
                    courses = courseService.getCoursesByTeacher(semesterId, user.getNum());
                }else{
                    courses = courseService.getCoursesByStudent(semesterId, user.getNum());
                }
                m.addObject("semester", semesterService.getSemesterById(semesterId));
                Map<Long, Integer> semesterCourseIds = courseService.getSemesterCourseIdMap(semesterId, courses);
                m.addObject("semesterCourseIds", semesterCourseIds);

            }
        }
        return m;
    }

    @RequestMapping("/semester/{semesterId}/courseDetail/{courseId}")
    public ModelAndView courseDetail(@PathVariable Integer semesterId, @PathVariable Integer courseId,
                                     HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        ModelAndView m = new ModelAndView("course/student_course");
        if(semesterId == null || courseId == null){
            response.sendRedirect("index");
        }
        if (user.getType() == 1 || user.getType() == 0){
            Course course = courseService.getCourseById(courseId);
            m.addObject("course", course);
            List<String> teachers = courseService.getTeachersName(semesterId, course);
            m.addObject("teachers", teachers);
            SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
            m.addObject("semesterCourseId", semesterCourse.getId());
            m.addObject("countStudent",courseService.countStudents(semesterCourse.getId()));
        }
        return m;
    }

    /**
     * 转到资源上传页面
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/uploadResource/semester/{semesterId}/course/{courseId}/category/{category}")
    public ModelAndView uploadResourceGet(@PathVariable Integer semesterId, @PathVariable Integer courseId,
                                          HttpServletRequest request, @PathVariable String category) {
        ModelAndView m = new ModelAndView("course/upload");
        if (semesterId != null && courseId != null) {
            Semester semester = semesterService.getSemesterById(semesterId);
            m.addObject("semester", semester);
            Course course = courseService.getCourseById(courseId);
            m.addObject("course", course);
        } else {
            m.addObject("message", "找不到课程！");
        }
        m.addObject("category", category);
        return m;
    }

    /**
     * 资源上传逻辑
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadResource/semester/{semesterId}/course/{courseId}")
    public ModelAndView uploadResourcePost(@PathVariable Integer semesterId, @PathVariable Integer courseId, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("course/upload");
        if (files.length <= 0) {
            m.addObject("message", "未选择文件！");
            return m;
        }
        if (semesterId == null || courseId == null) {
            m.addObject("message", "未选择课程！");
            return m;
        }
        String category = request.getParameter("category");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = getResourcePath(semesterId, courseId, request);
                String serverPath = getServerPath(semesterId, courseId, request, file);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 转存文件
                try {
                    File temp = new File(filePath + File.separator + file.getOriginalFilename());
                    file.transferTo(temp);

                    buaa.course.model.Resource r = new buaa.course.model.Resource();
                    r.setFileUrl(serverPath);
                    r.setSemesterCourseId(courseService.getSemesterCourseBySemesterCourseId(semesterId,courseId).getId());
                    r.setCategory(category);
                    r.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    resourceService.createResource(r);
                } catch (Exception e) {
                    e.printStackTrace();
                    m.addObject("message", e.getMessage());
                    return m;
                }
            }
        }

        Course course = courseService.getCourseById(courseId);
        m.addObject("course", course);
        m.addObject("category", category);
        m.addObject("message", "上传成功！");
        return m;
    }

    private String getServerPath(Integer semesterId, Integer courseId, HttpServletRequest request, MultipartFile file) {
        String dir = getServerDir(request, semesterId, courseId);
        return dir + file.getOriginalFilename();
    }

    @RequestMapping("/semester/{semesterId}/course/{courseId}/resources")
    public ModelAndView resourcesInCategory(@PathVariable Integer semesterId, @PathVariable Integer courseId, HttpServletRequest request){
        ModelAndView m = null;
        User user = (User) request.getSession().getAttribute("user");
        if (user.getType() == 0) {//学生查看资源列表
            m = new ModelAndView("course/student_resources_cate");
        }else if(user.getType() == 1){//教师查看资源列表
            m = new ModelAndView("course/teacher_resources_cate");
        }
        m.addObject("course", courseService.getCourseById(courseId));
        m.addObject("categories", resourceService.getResourcesInCategory(semesterId, courseId));
        return m;
    }

    @RequestMapping("/semester/{semesterId}/course/{courseId}/resources/category/{category}")
    public ModelAndView getResourcesInCategory(@PathVariable Integer semesterId, @PathVariable Integer courseId,
                                            @PathVariable String category, HttpServletRequest request){
        ModelAndView m = null;
        User user = (User) request.getSession().getAttribute("user");
        if (user.getType() == 0) {//学生查看资源列表
            m = new ModelAndView("course/student_resources");
        }else if(user.getType() == 1){//教师查看资源列表
            m = new ModelAndView("course/teacher_resources");
        }
        m.addObject("course", courseService.getCourseById(courseId));
        m.addObject("category", category);
        m.addObject("resources", resourceService.getResourcesByCategory(semesterId, courseId, category));
        return m;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/semester/{semesterId}/course/{courseId}/resources/addCategory")
    public ModelAndView addResourcesCategoryGet(@PathVariable Integer semesterId, @PathVariable Integer courseId,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView m = new ModelAndView("course/create_category");
        m.addObject("course", courseService.getCourseById(courseId));
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/semester/{semesterId}/course/{courseId}/resources/addCategory")
    public ModelAndView addResourcesCategoryPost(@PathVariable Integer semesterId, @PathVariable Integer courseId,
                                     HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView m = new ModelAndView("course/create_category");
        String category = request.getParameter("category");
        if(!StringUtils.isEmptyOrWhitespaceOnly(category)){
            SemesterCourse sc = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
            resourceService.createResourceCategory(sc.getId(), category);
            m.addObject("message", "添加成功!");
        }else{
            m.addObject("message", "请输入类别名称！");
        }

        m.addObject("course", courseService.getCourseById(courseId));
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/semester/{semesterId}/course/{courseId}/resources/deleteCategory")
    public void delResourcesCategory(@PathVariable Integer semesterId, @PathVariable Integer courseId,
                                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        String category = request.getParameter("category");
        if(!StringUtils.isEmptyOrWhitespaceOnly(category)){
            SemesterCourse sc = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
            resourceService.deleteResourcesByCategory(sc.getId(), category);
        }
        response.sendRedirect("/semester/"+semesterId+"/course/"+courseId+"/resources");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/semester/{semesterId}/course/{courseId}/deleteResource")
    public void deleteResource(@PathVariable Integer semesterId, @PathVariable Integer courseId,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resourceId = request.getParameter("fileId");
        String category = request.getParameter("category");
        if(!StringUtils.isEmptyOrWhitespaceOnly(resourceId)){
            try{
                resourceService.deleteResourceById(Integer.valueOf(resourceId));
            }catch (Exception e){

            }
        }
        if(StringUtils.isEmptyOrWhitespaceOnly(category)){
            response.sendRedirect("/semester/"+semesterId+"/course/"+courseId+"/resources");
        }
        response.sendRedirect("/semester/"+semesterId+"/course/"+courseId+"/resources");
    }


    /***
     * 读取上传文件中得所有文件并返回
     *
     * @return
     */
    @RequestMapping("/semester/{semesterId}/course/{courseId}/resourceList")
    public ModelAndView list(@PathVariable Integer semesterId, @PathVariable Integer courseId, HttpServletRequest request) {
        String filePath = getResourcePath(semesterId, courseId, request);
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        ModelAndView m = null;
        User user = (User) request.getSession().getAttribute("user");
        if (user.getType() == 0) {//学生查看资源列表
            m = new ModelAndView("course/student_resources");
        }else if(user.getType() == 1){//教师查看资源列表
            m = new ModelAndView("course/teacher_resources");
        }
        m.addObject("course", courseService.getCourseById(courseId));
        m.addObject("resources", resourceService.getResourcesByCourse(semesterId, courseId));
        return m;
    }

    private String getResourcePath(int semesterId, int courseId, HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("/")
                + "resource" + File.separator + "semester-" + semesterId + File.separator + "course-" + courseId;
        return filePath;

    }

    private String getServerDir(HttpServletRequest request, int semesterId, int courseId) {
        return "/"+request.getContextPath() +
                "resource" + "/" + "semester-" + semesterId + "/" + "course-" + courseId + "/";
    }
}
