package buaa.course.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 熊纪元 on 2016/7/2.
 */
@Controller
public class BasicServlet {
    @RequestMapping(method = RequestMethod.GET,value = "/hello/{name}")
    public ModelAndView hello(@PathVariable String name){
        ModelAndView m = new ModelAndView("hello");
        m.addObject("message", name);
        return m;
    }
}
