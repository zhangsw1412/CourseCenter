package buaa.course.logic;

import buaa.course.mapper.UserMapper;
import buaa.course.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class UserLogic {
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    public int createUser(User user) {
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
