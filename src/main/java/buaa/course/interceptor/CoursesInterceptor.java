package buaa.course.interceptor;

import buaa.course.model.User;
import buaa.course.service.CourseService;
import buaa.course.service.SemesterService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 熊纪元 on 2016/7/8.
 */
public class CoursesInterceptor implements HandlerInterceptor {
    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "semesterService")
    private SemesterService semesterService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView m) throws Exception {
        User user = (User)request.getSession().getAttribute("user");

        if(user != null){
            Integer semesterId = (Integer)request.getSession().getAttribute("semesterId");
            System.out.println(semesterId);
            if(semesterId == null)
                throw new RuntimeException();
            m.addObject("semester", semesterService.getSemesterById(semesterId));
            if(user.getType() == 0){
                m.addObject("courses", courseService.getCoursesByStudent(semesterId, user.getNum()));
            }else if(user.getType() == 1){
                m.addObject("courses", courseService.getCoursesByTeacher(semesterId, user.getNum()));
            }
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}