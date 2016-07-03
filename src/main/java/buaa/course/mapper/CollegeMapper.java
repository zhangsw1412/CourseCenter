package buaa.course.mapper;

import java.util.List;

import buaa.course.model.College;

public interface CollegeMapper {
	College getCollege(int id);
	int addCollege(College college);
	int deleteCollege(int id);
	int updateCollege(College college);
	List<College> getAllColleges();
	int countColleges();
	List<College> getCollegesByRange(int start,int row);
}
