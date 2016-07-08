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
 * Created by 熊纪元 on 2016/7/3.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "semesterService")
    private SemesterService semesterService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取url地址
        String reqUrl=request.getRequestURI().replace(request.getContextPath(), "");
        //当url地址为登录的url的时候跳过拦截器
        if(reqUrl.contains("/login")) {
            return true;
        }
        User user = (User)request.getSession().getAttribute("user");

        if(user == null){
            System.out.println("========================================================");
            System.out.println("user is null");
            System.out.println("========================================================");
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView m) throws Exception {
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            Integer semesterId = (Integer)request.getSession().getAttribute("semesterId");
            if(semesterId == null){
                semesterId = 2;
            }
            m.addObject("semester", semesterService.getSemesterById(semesterId));
            if(user.getType() == 0){
                m.addObject("courses", courseService.getCoursesByStudent(semesterId, user.getNum()));
            }else if(user.getType() == 1){
                m.addObject("courses", courseService.getCoursesByTeacher(semesterId, user.getNum()));
            }

            System.out.println(o.getClass());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
