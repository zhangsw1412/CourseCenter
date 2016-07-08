package buaa.course.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import buaa.course.model.User;
import buaa.course.service.UserService;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
@Controller
public class UserController
{
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/userList")
	public String userList(Map<String, Object> map)
	{
		map.put("users", userService.getAllUsers());
		return "user/userList";
	}

	@RequestMapping(value = "cancelUser/{num}")
	public String cancelUser(@PathVariable(value = "num") Integer num)
	{
		User user = userService.getUserByNum(num);
		user.setValid(false);
		userService.updateUser(user);
		return "redirect:/userList";
	}
}
