package buaa.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 熊纪元 on 2016/7/8.
 */
@Controller
public class ChatController {
    @RequestMapping(method = RequestMethod.GET, value = "/semester/{semesterId}/course/{courseId}/chat")
    public ModelAndView chat(@PathVariable Integer semesterId, @PathVariable Integer courseId) {
        return null;
    }
}
