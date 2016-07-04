package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Homework;

public interface HomeworkMapper {
	Homework getHomework(int id);
	int addHomework(Homework homework);
	int deleteHomework(int id);
	int updateHomework(Homework homework);
	List<Homework> getAllHomeworks();
	int countHomeworks();
	List<Homework> getHomeworksByRange(int start,int row);
	List<Homework> getHomeworkByIds(int studentId,int assignment);
}
