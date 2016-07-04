package buaa.course.service;

import buaa.course.mapper.AssignmentMapper;
import buaa.course.model.Assignment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
@Service
public class AssignmentService {
    @Resource(name = "assignmentMapper")
    private AssignmentMapper assignmentMapper;
    public Assignment getAssignment(Integer assignmentId) {
        return assignmentMapper.getAssignment(assignmentId);
    }
}
