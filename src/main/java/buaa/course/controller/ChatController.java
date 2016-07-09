package buaa.course.controller;

import buaa.course.model.Message;
import buaa.course.model.SemesterCourse;
import buaa.course.model.User;
import buaa.course.service.CourseService;
import buaa.course.service.MessageService;
import buaa.course.service.SemesterService;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/8.
 */
@Controller
public class ChatController {
    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "semesterService")
    private SemesterService semesterService;

    @Resource(name = "messageService")
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET, value = "/semester/{semesterId}/course/{courseId}/chat")
    public ModelAndView chatGet(@PathVariable Integer semesterId, @PathVariable Integer courseId) {
        ModelAndView m = new ModelAndView("user/chat");
        SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
        List<Message> messageList = messageService.getMessagesBySemesterCourseId(semesterCourse.getId());
        m.addObject("messages", messageList);
        m.addObject("course", courseService.getCourseById(courseId));
        return m;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/semester/{semesterId}/course/{courseId}/chat")
    public ModelAndView chatPost(@PathVariable Integer semesterId, @PathVariable Integer courseId, HttpServletRequest request) {
        String content = request.getParameter("content");
        if(!StringUtils.isNullOrEmpty(content)){
            Message message = new Message();
            User user = (User)request.getSession().getAttribute("user");
            SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
            message.setContent(content);
            message.setCreateTime(new Timestamp(System.currentTimeMillis()));
            message.setSemesterCourseId(semesterCourse.getId());
            message.setUserId(user.getId());
            message.setUserName(user.getName());

            messageService.createMessage(message);
        }
        return chatGet(semesterId,courseId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/semester/{semesterId}/course/{courseId}/chat/ajax")
    public ModelAndView chatAjaxGet(@PathVariable Integer semesterId, @PathVariable Integer courseId, @RequestParam Integer size) {
        ModelAndView m = new ModelAndView("user/chatAjax");
        int count = messageService.countMessages();
        List<Message> messageList = new ArrayList<>();
        if(size < count){
            SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
            messageList = messageService.getMessagesBySemesterCourseIdInRange(semesterCourse.getId(), size-1, count-size);
        }
        m.addObject("messages", messageList);
        return m;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ajax/json")
    public @ResponseBody Message chatAjaxGet() {
       return new Message(2,"13211130","熊纪元","hello world",new Timestamp(System.currentTimeMillis()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/semester/{semesterId}/course/{courseId}/chat/ajax")
    public ModelAndView chatAjaxPost(@PathVariable Integer semesterId, @PathVariable Integer courseId, @RequestParam Integer size, HttpServletRequest request) {
        String content = request.getParameter("content");
        if(!StringUtils.isNullOrEmpty(content)){
            Message message = new Message();
            User user = (User)request.getSession().getAttribute("user");
            SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
            message.setContent(content);
            message.setCreateTime(new Timestamp(System.currentTimeMillis()));
            message.setSemesterCourseId(semesterCourse.getId());
            message.setUserId(user.getId());
            message.setUserName(user.getName());

            messageService.createMessage(message);
        }
        return chatAjaxGet(semesterId,courseId, messageService.countMessages()-1);
    }
}
