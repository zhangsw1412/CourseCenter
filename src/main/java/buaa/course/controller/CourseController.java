package buaa.course.controller;

import buaa.course.model.Course;
import buaa.course.model.Semester;
import buaa.course.model.User;
import buaa.course.service.CourseService;
import buaa.course.service.SemesterService;
import buaa.course.service.UserService;
import buaa.course.utils.PagingUtil;
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
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
@Controller
public class CourseController {
    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "semesterService")
    private SemesterService semesterService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "pagingUtil")
    private PagingUtil pagingUtil;

    @RequestMapping(method = RequestMethod.GET, value = "/{semesterId}/courseList/{pageNo}")
    public ModelAndView courseList(@PathVariable Integer semesterId, @PathVariable Integer pageNo, HttpServletRequest request) {
//        ModelAndView m = new ModelAndView("course/courseList");
        ModelAndView m = new ModelAndView("course/teacher_course");
        if(semesterId != null){
            User user = (User)request.getSession().getAttribute("user");
            if(user.getType() == 0){
                List<Course> courses = courseService.getCoursesByTeacher(semesterId, user.getNum());
                pagingUtil.setPageRow(10);
                pagingUtil.setTotalRow(courses.size());
                pagingUtil.toNewPage(String.valueOf(pageNo));
                m.addObject("semester", semesterService.getSemesterById(semesterId));
                m.addObject("teacher", user);
                m.addObject("courses", courses.subList(pagingUtil.getFirstIndex(), pagingUtil.getLastIndex()+1));
            }else if(user.getType() == 1){
                List<Course> courses = courseService.getCoursesByStudent(semesterId, user.getNum());
                pagingUtil.setPageRow(10);
                pagingUtil.setTotalRow(courses.size());
                pagingUtil.toNewPage(String.valueOf(pageNo));
                m.addObject("semester", semesterService.getSemesterById(semesterId));
                m.addObject("student", user);
                m.addObject("courses", courses.subList(pagingUtil.getFirstIndex(), pagingUtil.getLastIndex()+1));
            }
            List<Course> courses = courseService.getCoursesBySemesterId(semesterId);
            pagingUtil.setPageRow(10);
            pagingUtil.setTotalRow(courses.size());
            pagingUtil.toNewPage(String.valueOf(pageNo));

            m.addObject("courses", courses.subList(pagingUtil.getFirstIndex(), pagingUtil.getLastIndex()+1));
        }
        return m;
    }

    /**
     * 转到资源上传页面
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/uploadResource/{semesterId}/{courseId}")
    public ModelAndView uploadResourceGet(@PathVariable Integer semesterId, @PathVariable Integer courseId) {
        ModelAndView m = new ModelAndView("course/uploadResource");
        if(semesterId != null && courseId != null){
            Semester semester = semesterService.getSemesterById(semesterId);
            m.addObject("semester", semester);
            Course course = courseService.getCourseBySemesterCourseId(semesterId, courseId);
            m.addObject("course", course);
        }else{
            m.addObject("message", "找不到课程！");
        }
        return m;
    }

    /**
     * 资源上传逻辑
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadResource/{semesterId}/{courseId}")
    public ModelAndView uploadResourcePost(@PathVariable Integer semesterId, @PathVariable Integer courseId, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("course/uploadResource");
        if(files.length <= 0 ){
            m.addObject("message", "未选择文件！");
            return m;
        }
        if(semesterId == null || courseId == null){
            m.addObject("message", "未选择课程！");
            return m;
        }

        for(MultipartFile file : files){
            if(!file.isEmpty()){
                // 文件保存路径
                String filePath = filePath = getResourcePath(semesterId,courseId,request);
                File dir = new File(filePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                // 转存文件
                try{
                    File temp = new File(filePath+ File.separator + file.getOriginalFilename());
                    file.transferTo(temp);
                }catch (Exception e){
                    e.printStackTrace();
                    m.addObject("message", e.getMessage());
                    return m;
                }
            }
        }

        Course course = courseService.getCourseBySemesterCourseId(semesterId, courseId);
        m.addObject("course", course);
        m.addObject("message", "上传成功！");
        return m;
    }

    /***
     * 读取上传文件中得所有文件并返回
     *
     * @return
     */
    @RequestMapping("/{semesterId}/{courseId}/resourceList/{pageNo}")
    public ModelAndView list(@PathVariable Integer semesterId, @PathVariable Integer courseId, @PathVariable Integer pageNo, HttpServletRequest request) {
        String filePath = getResourcePath(semesterId,courseId,request);
        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        ModelAndView m = new ModelAndView("course/resourceList");
        File uploadDest = new File(filePath);
        String[] list = uploadDest.list();
        if(list != null){
            m.addObject("files", Arrays.asList(uploadDest.list()));
        }

        return m;
    }

    private String getResourcePath(int semesterId, int courseId, HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("/")
                + "resource" + File.separator + "semester-"+semesterId + File.separator + "course-" + courseId;
        return filePath;
    }
}
