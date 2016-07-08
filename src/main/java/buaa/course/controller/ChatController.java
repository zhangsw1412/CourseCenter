package buaa.course.controller;

import buaa.course.model.SemesterCourse;
import buaa.course.service.CourseService;
import buaa.course.service.SemesterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by 熊纪元 on 2016/7/8.
 */
@Controller
public class ChatController {
    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "semesterService")
    private SemesterService semesterService;
    @RequestMapping(method = RequestMethod.GET, value = "/semester/{semesterId}/course/{courseId}/chat")
    public ModelAndView chatGet(@PathVariable Integer semesterId, @PathVariable Integer courseId) {
        SemesterCourse semesterCourse = courseService.getSemesterCourseBySemesterCourseId(semesterId, courseId);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/semester/{semesterId}/course/{courseId}/chat")
    public ModelAndView chatPost(@PathVariable Integer semesterId, @PathVariable Integer courseId) {
        return null;
    }
}
