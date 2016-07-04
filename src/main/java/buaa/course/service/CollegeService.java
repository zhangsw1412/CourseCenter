package buaa.course.service;

import buaa.course.mapper.CollegeMapper;
import buaa.course.model.College;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class CollegeService {
    @Resource(name = "collegeMapper")
    private CollegeMapper collegeMapper;

    public College getCollegeById(int id) {
        return collegeMapper.getCollege(id);
    }

    public int createCollege(College college) {
        return collegeMapper.addCollege(college);
    }

    public int updateCollege(College college) {
        return collegeMapper.updateCollege(college);
    }

    public int deleteCollege(int id) {
        return collegeMapper.deleteCollege(id);
    }

    public List<College> getAllColleges() {
        return collegeMapper.getAllColleges();
    }

    public List<College> getCollegesByRange(int start, int length) {
        return collegeMapper.getCollegesByRange(start, length);
    }

    public int countColleges() {
        return collegeMapper.countColleges();
    }
}
