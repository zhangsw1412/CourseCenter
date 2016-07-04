package buaa.course.service;

import buaa.course.mapper.UserMapper;
import buaa.course.model.User;
import buaa.course.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
