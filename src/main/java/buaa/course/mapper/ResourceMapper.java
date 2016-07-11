package buaa.course.mapper;

import buaa.course.model.Resource;
import buaa.course.model.ResourceCategory;

import java.util.List;

public interface ResourceMapper {
	Resource getResource(int id);
	int addResource(Resource resource);
	int deleteResource(int id);
	int updateResource(Resource resource);
	List<Resource> getAllResources();
	int countResources();
	List<Resource> getResourcesByRange(int start,int row);
	List<Resource> getResourcesByCourse(int semesterCourseId);
	List<ResourceCategory> getResourcesInCategory(int id);
	List<Resource> getResourcesByCategory(int id, String category);
	int deleteResourceByCategory(int id, String category);
}
