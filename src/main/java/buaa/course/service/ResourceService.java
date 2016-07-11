package buaa.course.service;

import buaa.course.mapper.ResourceMapper;
import buaa.course.mapper.SemesterCourseMapper;
import buaa.course.model.ResourceCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
@Service
public class ResourceService {
    @Resource(name = "resourceMapper")
    private ResourceMapper resourceMapper;

    @Resource(name = "semesterCourseMapper")
    private SemesterCourseMapper semesterCourseMapper;

    public int createResource(buaa.course.model.Resource resource) {
        return resourceMapper.addResource(resource);
    }

    public List<buaa.course.model.Resource> getResourcesByCourse(int semesterId, int courseId) {
        return resourceMapper.getResourcesByCourse(semesterCourseMapper.getSemesterCourseByTwoIds(semesterId, courseId).getId());
    }

    public List<ResourceCategory> getResourcesInCategory(int semesterId, int courseId) {
        return resourceMapper.getResourcesInCategory(semesterCourseMapper.getSemesterCourseByTwoIds(semesterId, courseId).getId());
    }

    public List<buaa.course.model.Resource> getResourcesByCategory(int semesterId, int courseId, String category) {
        return resourceMapper.getResourcesByCategory(semesterCourseMapper.getSemesterCourseByTwoIds(semesterId, courseId).getId(), category);
    }

    public void deleteResourcesByCategory(int id, String category) {
        resourceMapper.deleteResourceByCategory(id, category);
    }

    public int createResourceCategory(int id, String category) {
        return resourceMapper.createResourceCategory(id, category);
    }
}
