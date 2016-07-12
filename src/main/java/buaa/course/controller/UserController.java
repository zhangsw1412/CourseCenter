package buaa.course.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import buaa.course.model.User;
import buaa.course.service.UserService;
import buaa.course.utils.PasswordEncoder;

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

	@RequestMapping(value = "userInfo")
	public String userInfo(Map<String, Object> map, HttpServletRequest request)
	{
		map.put("user", request.getSession().getAttribute("user"));
		return "user/userInfo";
	}

	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public String updatePassword(Map<String, Object> map,
			HttpServletRequest request)
	{
		String oldPwd = request.getParameter("old_password");
		String newPwd = request.getParameter("new_password");
		String confPwd = request.getParameter("confirm_password");
		User user = (User) request.getSession().getAttribute("user");
		if (!user.getPassword().equals(PasswordEncoder.encode(oldPwd)))
		{
			map.put("message", "原密码输入错误");
			return "user/userInfo";
		}
		else
		{
			if (!newPwd.equals(confPwd))
			{
				map.put("message", "两次输入新密码不一致");
				return "user/userInfo";
			}
			else
			{
				user.setPassword(PasswordEncoder.encode(newPwd));
				userService.updateUser(user);
				map.put("message", "修改成功");
				return "user/userInfo";
			}
		}
	}
}
