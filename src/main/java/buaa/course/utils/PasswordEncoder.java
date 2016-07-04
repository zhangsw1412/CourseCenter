package buaa.course.utils;

import buaa.course.service.UserService;
import buaa.course.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
@Service
public class PasswordEncoder {
    @Resource(name = "userService")
    private UserService userService;

    public static String encode(String password){
        String result = DigestUtils.sha1Hex(password);
        return result;
    }

    public boolean isPasswordValid(String userId, String password) {
        User user = userService.getUser(userId);
        return user.getPassword().equals(encode(password));
    }
}
