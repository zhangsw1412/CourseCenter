package buaa.course.controller;

import buaa.course.service.UserService;
import buaa.course.model.User;
import buaa.course.utils.IpUtil;
import buaa.course.utils.PasswordEncoder;
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

/**
 * Created by 熊纪元 on 2016/7/2.
 */
@Controller
public class BasicController {
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET,value = "/hello/{name}")
    public ModelAndView hello(@PathVariable String name, HttpServletResponse response) throws IOException {
       ModelAndView m = new ModelAndView("hello");
        m.addObject("message", name);
        return m;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView loginGet(){
        return new ModelAndView("login");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            request.setAttribute("user",user);
            return new ModelAndView("index");
        }else{
            m.addObject("error", "用户名或密码错误");
            return m;
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("login");
    }
}
