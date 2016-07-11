package buaa.course.service;

import buaa.course.mapper.UserMapper;
import buaa.course.model.Homework;
import buaa.course.model.User;
import buaa.course.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class UserService {
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public User getUser(String id) {
        return userMapper.getUser(id);
    }
    
    public User getUserByNum(int num) {
        return userMapper.getUserByNum(num);
    }
    

    public int createUser(User user) {
        String pwd = PasswordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
        return userMapper.addUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public List<User> getUsersByRange(int start, int length) {
        return userMapper.getUsersByRange(start, length);
    }

    public int countUsers() {
        return userMapper.countUsers();
    }

	public Map<Long, User> getUsersMap(List<Homework> homeworklist) {
		Map<Long,User> map=new HashMap<>();
		for(Homework homework:homeworklist){
			map.put(Long.valueOf(homework.getStudentId()), userMapper.getUserByNum(homework.getStudentId()));
		}
		return map;
	}

	public List<User> getUsersByTeamId(int teamId) {
		return userMapper.getUsersByTeamId(teamId);
	}
}
