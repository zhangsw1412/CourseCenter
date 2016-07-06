package buaa.course.controller;

import java.io.Reader;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import buaa.course.model.Semester;
import buaa.course.service.SemesterService;

@Controller
public class SemesterController
{
	@Resource(name = "semesterService")
	private SemesterService semesterService;

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
	 * 
	 * @param semester
	 * @return
	 */
	@RequestMapping(value = "/saveSemester", method = RequestMethod.POST)
	public String saveSemester(@RequestParam(value="schoolYear") Integer schoolYear,@RequestParam(value="season") Integer season,@RequestParam(value="startDate") Date startDate,@RequestParam(value="endDate") Date endDate,@RequestParam(value="weeks") Integer weeks)
	{
		semesterService.saveSemester(new Semester(schoolYear,season,startDate,endDate,weeks));
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
}
