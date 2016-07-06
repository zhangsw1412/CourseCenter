package buaa.course.service;

import buaa.course.mapper.AssignmentMapper;
import buaa.course.mapper.HomeworkMapper;
import buaa.course.model.Assignment;
import buaa.course.model.Homework;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

	public int getHighestScore(int homeworkId) {
		return assignmentMapper.getAssignment(homeworkMapper.getHomework(homeworkId).getAssignmentId()).getHighestScore();
	}

	public List<Homework> getHomeworksByAssignmentId(int assignmentId) {
		return homeworkMapper.getHomeworksByAssignmentId(assignmentId);
	}

    public Map<Long, Homework> getHomeworksByAssignments(List<Assignment> assignmentlist, int studentId) {
        Map<Long, Homework> result = new HashMap<>();
        for(Assignment assignment : assignmentlist){
            Homework homework = getHomeworkByAssignment(assignment.getId(), studentId);
            result.put(Long.valueOf(assignment.getId()), homework);
        }
        return result;
    }

    public Homework getHomeworkByAssignment(int assignmentId, int num) {
        return homeworkMapper.getHomeworkByAssignment(assignmentId, num);
    }
}
