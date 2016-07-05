package buaa.course.service;

import buaa.course.mapper.AssignmentMapper;
import buaa.course.mapper.HomeworkMapper;
import buaa.course.model.Homework;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class HomeworkService {
    @Resource(name = "homeworkMapper")
    private HomeworkMapper homeworkMapper;
    @Resource(name = "assignmentMapper")
    private AssignmentMapper assignmentMapper;

    public Homework getHomeworkById(int id) {
        return homeworkMapper.getHomework(id);
    }

    public int createHomework(Homework homework) {
        return homeworkMapper.addHomework(homework);
    }

    public int updateHomework(Homework homework) {
        return homeworkMapper.updateHomework(homework);
    }

    public int deleteHomework(int id) {
        return homeworkMapper.deleteHomework(id);
    }

    public List<Homework> getAllHomeworks() {
        return homeworkMapper.getAllHomeworks();
    }

    public List<Homework> getHomeworksByRange(int start, int length) {
        return homeworkMapper.getHomeworksByRange(start, length);
    }

    public int countHomeworks() {
        return homeworkMapper.countHomeworks();
    }
    
    public List<Homework> getHomeworkByIds(int studentId, int assignmentId)
    {
    	return homeworkMapper.getHomeworkByIds(studentId, assignmentId);
    }

	public int getHighestScore(int homeworkId) {
		return assignmentMapper.getAssignment(homeworkMapper.getHomework(homeworkId).getAssignmentId()).getHighestScore();
	}
}
