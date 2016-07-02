package buaa.course.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 熊纪元 on 2016/7/2.
 */
@Controller
public class BasicServlet {
    @RequestMapping("/hello.do")
    public ModelAndView index(){
        ModelAndView m = new ModelAndView("index");
        m.addObject("message", "hello world");
        return m;
    }
}
