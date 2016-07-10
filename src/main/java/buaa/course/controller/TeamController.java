package buaa.course.controller;

import buaa.course.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 熊纪元 on 2016/7/10.
 */
@Controller
public class TeamController {
    @RequestMapping(method = RequestMethod.GET, value = "/teams")
    public ModelAndView teams(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user.getType() != 0){
            response.sendRedirect("/index");
            return null;
        }
        return null;
    }
}
