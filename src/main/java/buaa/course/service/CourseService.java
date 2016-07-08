package buaa.course.service;

import buaa.course.mapper.*;
import buaa.course.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 熊纪元 on 2016/7/3.
 */
@Service
public class CourseService {
    @Resource(name = "courseMapper")
    private CourseMapper courseMapper;

    @Resource(name = "semesterCourseMapper")
    private SemesterCourseMapper semesterCourseMapper;

    @Resource(name = "courseTeacherMapper")
    private CourseTeacherMapper courseTeacherMapper;

    @Resource(name = "courseStudentMapper")
    private CourseStudentMapper courseStudentMapper;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

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

    public Course getCourseBySemesterCourseId(int semesterCourseId) {
        SemesterCourse sc = semesterCourseMapper.getSemesterCourse(semesterCourseId);
        if(sc!=null)
        	return courseMapper.getCourse(sc.getCourseId());
        else
        	return null;
    }

    public Course getCourseBySemesterCourseId(int semesterId, int courseId) {
        SemesterCourse sc = semesterCourseMapper.getSemesterCourseByTwoIds(semesterId, courseId);
        if(sc != null)
            return courseMapper.getCourse(sc.getCourseId());
        else return null;
    }

    public SemesterCourse getSemesterCourseBySemesterCourseId(int semesterId, int courseId) {
        SemesterCourse sc = semesterCourseMapper.getSemesterCourseByTwoIds(semesterId, courseId);
        return semesterCourseMapper.getSemesterCourseByTwoIds(semesterId, courseId);
    }

    public List<Course> getCoursesBySemesterId(int semesterId) {
        List<SemesterCourse> semesterCourses = semesterCourseMapper.getSemesterCourseBySemesterId(semesterId);
        List<Course> courses = new ArrayList<>();
        for(SemesterCourse semesterCourse : semesterCourses){
            courses.add(getCourseById(semesterCourse.getCourseId()));
        }

        return courses;
    }

    public List<Course> getCoursesByTeacher(int semesterId, int teacherId) {
        List<CourseTeacher> courseTeachers = courseTeacherMapper.getCourseTeacherByTeacher(teacherId);
        List<SemesterCourse> semesterCourses = semesterCourseMapper.getSemesterCourseBySemesterId(semesterId);
        List<Course> courses = new ArrayList<>();

        for(CourseTeacher courseTeacher : courseTeachers){
            for(SemesterCourse semesterCourse : semesterCourses){
                if(courseTeacher.getSemesterCourseId() == semesterCourse.getId()){
                    courses.add(getCourseById(semesterCourse.getCourseId()));
                }
            }
        }

        return courses;
    }

    public List<Course> getCoursesByStudent(int semesterId, int studentId) {
        List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentByStudent(studentId);
        List<SemesterCourse> semesterCourses = semesterCourseMapper.getSemesterCourseBySemesterId(semesterId);
        List<Course> courses = new ArrayList<>();
        for(CourseStudent courseStudent : courseStudents){
            for(SemesterCourse semesterCourse : semesterCourses){
                if(courseStudent.getSemesterCourseId() == semesterCourse.getId()){
                    courses.add(getCourseById(semesterCourse.getCourseId()));
                }
            }
        }

        return courses;
    }

    public Map<Long, List<String>> getTeachersName(int semesterId, List<Course> courses) {
        Map<Long, List<String>> result = new HashMap<>();
        for(Course course : courses){
            result.put(Long.valueOf(course.getId()), getTeachersName(semesterId, course));
        }

        return result;
    }

    public List<String> getTeachersName(int semesterId, Course course) {
        SemesterCourse semesterCourse = this.getSemesterCourseBySemesterCourseId(semesterId, course.getId());

        List<String> names = new ArrayList<>();
        for(User user : getTeachers(semesterCourse.getId())){
            names.add(user.getName());
        }

        return names;
    }

    private List<User> getTeachers(int semesterCourseId) {
        List<User> users = new ArrayList<>();
        for(CourseTeacher courseTeacher : courseTeacherMapper.getCourseTeacherBySemesterCourseId(semesterCourseId)){
            users.add(userMapper.getUserByNum(courseTeacher.getTeacherId()));
        }
        return users;
    }

    public Map<Long, Integer> getSemesterCourseIdMap(int semesterId, List<Course> courses) {
        Map<Long, Integer> result = new HashMap<>();
        for(Course course : courses){
            SemesterCourse semesterCourse = this.getSemesterCourseBySemesterCourseId(semesterId, course.getId());
            result.put(Long.valueOf(course.getId()), semesterCourse.getId());
        }
        return result;
    }
}
