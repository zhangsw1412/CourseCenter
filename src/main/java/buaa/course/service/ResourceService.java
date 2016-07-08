package buaa.course.service;

import buaa.course.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
@Service
public class ResourceService {
    @Resource(name = "resourceMapper")
    private ResourceMapper resourceMapper;

    public int createResource(buaa.course.model.Resource resource) {
        return resourceMapper.addResource(resource);
    }
}
