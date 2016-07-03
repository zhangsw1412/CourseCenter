package buaa.course.mapper;

import java.util.List;

import buaa.course.model.User;

public interface UserMapper {
	User getUser(int id);
	int addUser(User user);
	int deleteUser(int id);
	int updateUser(User user);
	List<User> getAllUsers();
	int countUsers();
	List<User> getUsersByRange(int start,int lines);
}
