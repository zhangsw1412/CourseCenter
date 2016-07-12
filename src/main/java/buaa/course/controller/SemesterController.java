package buaa.course.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import buaa.course.model.SemesterCourse;
import buaa.course.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import buaa.course.model.Course;
import buaa.course.model.Semester;
import buaa.course.service.CourseService;
import buaa.course.service.SemesterService;

@Controller
public class SemesterController
{
	@Resource(name = "semesterService")
	private SemesterService semesterService;

	@Resource(name = "courseService")
	private CourseService courseService;

	/**
	 * 获取学期列表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/semesterList")
	public String semestereList(Map<String, Object> map)
	{
		map.put("semesterList", semesterService.getAllSemesters());
		return "semester/semesterList";
	}

	/**
	 * 新增学期页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addSemester", method = RequestMethod.GET)
	public String addSemester(Map<String, Object> map)
	{
		map.put("semester", new Semester());
		return "semester/inputSemester";
	}

	/**
	 * 保存学期
	 * @param schoolYear
	 * @param season
	 * @param startDate
	 * @param endDate
	 * @param weeks
	 * @return
	 */
	@RequestMapping(value = "/saveSemester", method = RequestMethod.POST)
	public String saveSemester(
			@RequestParam(value = "schoolYear") Integer schoolYear,
			@RequestParam(value = "season") Integer season,
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,
			@RequestParam(value = "weeks") Integer weeks)
	{
		semesterService.saveSemester(
				new Semester(schoolYear, season, startDate, endDate, weeks));
		return "redirect:semesterList";
	}

	/**
	 * 更新学期页面
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateSemester/{id}", method = RequestMethod.GET)
	public String updateSemester(@PathVariable(value = "id") Integer id,
			Map<String, Object> map)
	{
		map.put("semester", semesterService.getSemesterById(id));
		return "semester/inputSemester";
	}

	/**
	 * 删除学期
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteSemester/{id}", method = RequestMethod.GET)
	public String deleteSemester(@PathVariable(value = "id") Integer id)
	{
		semesterService.deleteSemester(id);
		return "redirect:/semesterList";
	}

	/**
	 * 课程列表页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/adminCourse")
	public String adminCourse(Map<String, Object> map)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		if ((month >= 9 && month <= 12) || month == 1 || month == 2)
		{
			month = 0;
		}
		else if (month >= 3 && month <= 6)
		{
			month = 1;
		}
		else if (month >= 7 && month <= 8)
		{
			month = 2;
		}
		map.put("courses",
				semesterService.getCoursesOfCurrentSemester(year, month));
		return "course/admin_course";
	}

	@RequestMapping(value = "/adminCourseInfo/{id}")
	public String adminCourseInfo(@PathVariable(value = "id") Integer courseId,
			Map<String, Object> map, HttpServletRequest request)
	{
		Course course = courseService.getCourseById(courseId);
		map.put("course", course);
		int semesterId = (int) request.getSession().getAttribute("semesterId");
		map.put("students",
				semesterService.getCourseStudents(semesterId, courseId));
		SemesterCourse semesterCourse=courseService.getSemesterCourseBySemesterCourseId(semesterId,courseId);
		List<User> teachers=courseService.getTeachers(semesterCourse.getId());
		map.put("teachers",teachers);
		return "course/admin_courseInfo";
	}
}
