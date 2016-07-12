package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Assignment;

public interface AssignmentMapper {
	Assignment getAssignment(int id);
	int addAssignment(Assignment assignment);
	int deleteAssignment(int id);
	int updateAssignment(Assignment assignment);
	List<Assignment> getAllAssignments();
	int countAssignments();
	List<Assignment> getAssignmentsByRange(int start,int row);
	List<Assignment> getAssignmentsBySemesterCourseId(int semesterCourseId);
}
