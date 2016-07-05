package buaa.course.service;

import buaa.course.mapper.AssignmentMapper;
import buaa.course.model.Assignment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssignmentService {
    @Resource(name = "assignmentMapper")
    private AssignmentMapper assignmentMapper;

    public Assignment getAssignmentById(int id) {
        return assignmentMapper.getAssignment(id);
    }

    public int createAssignment(Assignment assignment) {
        return assignmentMapper.addAssignment(assignment);
    }

    public int updateAssignment(Assignment assignment) {
        return assignmentMapper.updateAssignment(assignment);
    }

    public int deleteAssignment(int id) {
        return assignmentMapper.deleteAssignment(id);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentMapper.getAllAssignments();
    }

    public List<Assignment> getAssignmentsByRange(int start, int length) {
        return assignmentMapper.getAssignmentsByRange(start, length);
    }

    public int countAssignments() {
        return assignmentMapper.countAssignments();
    }

	public List<Assignment> getAssignmentsBySemesterCourseId(int semesterCourseId) {
		return assignmentMapper.getAssignmentSBySemesterCourseId(semesterCourseId);
	}
}
