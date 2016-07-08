package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Message;

public interface MessageMapper {
	Message getMessage(int id);
	int addMessage(Message message);
	int deleteMessage(int id);
	int updateMessage(Message message);
	List<Message> getAllMessages();
	int countMessages();
	List<Message> getMessagesByRange(int start,int row);
	List<Message> getMessagesBySemesterCourseId(int semesterCourseId);
}
