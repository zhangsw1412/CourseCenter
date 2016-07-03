package buaa.course.mapper;

import java.util.List;

import buaa.course.model.Resource;

public interface ResourceMapper {
	Resource getResource(int id);
	int addResource(Resource resource);
	int deleteResource(int id);
	int updateResource(Resource resource);
	List<Resource> getAllResources();
	int countResources();
	List<Resource> getResourcesByRange(int start,int row);
}
