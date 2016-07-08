package buaa.course.service;

import buaa.course.mapper.MessageMapper;
import buaa.course.model.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {
    @Resource(name = "messageMapper")
    private MessageMapper messageMapper;

    public Message getMessageById(int id) {
        return messageMapper.getMessage(id);
    }

    public int createMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    public int updateMessage(Message message) {
        return messageMapper.updateMessage(message);
    }

    public int deleteMessage(int id) {
        return messageMapper.deleteMessage(id);
    }

    public List<Message> getAllMessages() {
        return messageMapper.getAllMessages();
    }

    public List<Message> getMessagesByRange(int start, int length) {
        return messageMapper.getMessagesByRange(start, length);
    }

    public int countMessages() {
        return messageMapper.countMessages();
    }

	public List<Message> getMessagesBySemesterCourseId(int semesterCourseId) {
		return messageMapper.getMessagesBySemesterCourseId(semesterCourseId);
	}

}
