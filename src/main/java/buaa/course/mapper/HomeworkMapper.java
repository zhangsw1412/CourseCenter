package buaa.course.mapper;

import buaa.course.model.Homework;

import java.util.List;

public interface HomeworkMapper {
	Homework getHomework(int id);
	int addHomework(Homework homework);
	int deleteHomework(int id);
	int updateHomework(Homework homework);
	List<Homework> getAllHomeworks();
	int countHomeworks();
	List<Homework> getHomeworksByRange(int start,int row);
	List<Homework> getHomeworksByAssignmentId(int assignmentId);
	Homework getHomeworkByAssignment(int assignmentId, int studentId);
}
