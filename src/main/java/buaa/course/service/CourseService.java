package buaa.course.service;

import buaa.course.mapper.CourseMapper;
import buaa.course.mapper.SemesterCourseMapper;
import buaa.course.model.Course;
import buaa.course.model.SemesterCourse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class CourseService {
    @Resource(name = "courseMapper")
    private CourseMapper courseMapper;

    @Resource(name = "semesterCourseMapper")
    private SemesterCourseMapper semesterCourseMapper;

    public Course getCourseById(int id) {
        return courseMapper.getCourse(id);
    }

    public int createCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    public int deleteCourse(int id) {
        return courseMapper.deleteCourse(id);
    }

    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    public List<Course> getCoursesByRange(int start, int length) {
        return courseMapper.getCoursesByRange(start, length);
    }

    public int countCourses() {
        return courseMapper.countCourses();
    }

    public Course getCourseByCourseCode(String courseCode) {
        return courseMapper.getCourseByCourseCode(courseCode);
    }

    public Course getCourseBySemesterCourseId(int semester_course_id) {
        SemesterCourse sc = semesterCourseMapper.getSemesterCourse(semester_course_id);
        return courseMapper.getCourse(sc.getCourseId());
    }
}
