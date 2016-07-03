package buaa.course.logic;

import buaa.course.mapper.SemesterMapper;
import buaa.course.model.Semester;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class SemesterLogic {
    @Resource(name = "semesterMapper")
    private SemesterMapper semesterMapper;

    public Semester getSemesterById(int id) {
        return semesterMapper.getSemester(id);
    }

    public int createSemester(Semester semester) {
        return semesterMapper.addSemester(semester);
    }

    public int updateSemester(Semester semester) {
        return semesterMapper.updateSemester(semester);
    }

    public int deleteSemester(int id) {
        return semesterMapper.deleteSemester(id);
    }

    public List<Semester> getAllSemesters() {
        return semesterMapper.getAllSemesters();
    }

    public List<Semester> getSemestersByRange(int start, int length) {
        return semesterMapper.getSemestersByRange(start,length);
    }

    public int countSemesters() {
        return semesterMapper.countSemesters();
    }
}
