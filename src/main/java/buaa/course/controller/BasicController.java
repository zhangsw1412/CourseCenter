package buaa.course.controller;

import buaa.course.model.Course;
import buaa.course.model.User;
import buaa.course.service.CourseService;
import buaa.course.service.SemesterService;
import buaa.course.service.UserService;
import buaa.course.utils.IpUtil;
import buaa.course.utils.PasswordEncoder;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/2.
 */
@Controller
public class BasicController {
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "semesterService")
    private SemesterService semesterService;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView loginGet(){
        return new ModelAndView("login");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ModelAndView loginPost(HttpServletRequest request) throws IOException {
        ModelAndView m = new ModelAndView("login");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        if(StringUtils.isNullOrEmpty(userId) || StringUtils.isNullOrEmpty(password)){
            m.addObject("error", "用户名或密码为空");
            return m;
        }
        if(passwordEncoder.isPasswordValid(userId, password)){
            User user = userService.getUser(userId);
            if(!user.isValid()){
                m.addObject("error", "用户已被禁用");
                return m;
            }
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            userService.updateUser(user);
            request.getSession().setAttribute("user", user);

            List<Course> courses = new ArrayList<>();
            if(user.getType() == 1){
                courses = courseService.getCoursesByTeacher(2, user.getNum());
            }else{
                courses = courseService.getCoursesByStudent(2, user.getNum());
            }

            if(user.getType() == 0){
                m =  new ModelAndView("student");
                m.addObject("semester", semesterService.getSemesterById(2));
                m.addObject("courses", courses);
            }else if(user.getType() == 1){
                m = new ModelAndView("teacher");
                m.addObject("semester", semesterService.getSemesterById(2));
                m.addObject("courses", courses);
            }else if(user.getType() == 2){
                return new ModelAndView("admin");
            }
        }else{
            m.addObject("error", "用户名或密码错误");
        }
        return m;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("login");
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView m = null;
        List<Course> courses = new ArrayList<>();
        User user = (User)request.getSession().getAttribute("user");
        if(user.getType() == 0){
            m =  new ModelAndView("student");
            courses = courseService.getCoursesByTeacher(2, user.getNum());
        }else if(user.getType() == 1){
            m = new ModelAndView("teacher");
            courses = courseService.getCoursesByStudent(2, user.getNum());
        }
        m.addObject("semester", semesterService.getSemesterById(2));
        m.addObject("courses", courses);
        return m;
    }

}
