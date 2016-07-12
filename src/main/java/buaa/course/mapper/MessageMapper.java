package buaa.course.mapper;

import buaa.course.model.Message;

import java.sql.Timestamp;
import java.util.List;

public interface MessageMapper {
	Message getMessage(int id);
	int addMessage(Message message);
	int deleteMessage(int id);
	int updateMessage(Message message);
	List<Message> getAllMessages();
	int countMessages();
	List<Message> getMessagesByRange(int start,int row);
	List<Message> getMessagesBySemesterCourseId(int semesterCourseId);
	List<Message> getMessagesBySemesterCourseIdInRange(int semesterCourseId, int start, int length);
	List<Message> getMessagesBySemesterCourseIdAfterTime(int semesterCourseId, Timestamp now);
}
