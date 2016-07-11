package buaa.course.mapper;

import buaa.course.model.User;

import java.util.List;

public interface UserMapper {
	User getUser(String id);
	User getUserByNum(int num);
	int addUser(User user);
	int deleteUser(int id);
	int updateUser(User user);
	List<User> getAllUsers();
	int countUsers();
	List<User> getUsersByRange(int start,int lines);
	List<User> getUsersByTeamId(int teamId);
}
