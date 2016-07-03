package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.CourseTeacher;
import junit.framework.Assert;

public class CourseTeacherMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	CourseTeacherMapper mapper = context.getBean(CourseTeacherMapper.class);
	int id;
	@Test
	public void test(){
		CourseTeacher courseTeacher=new CourseTeacher(1,2);
		Assert.assertEquals(1, mapper.addCourseTeacher(courseTeacher));
		id = courseTeacher.getId();
		Assert.assertEquals(courseTeacher.toString(), mapper.getCourseTeacher(courseTeacher.getId()).toString());
		int count = mapper.countCourseTeachers();
		courseTeacher.setTeacherId(123);
		Assert.assertEquals(1, mapper.updateCourseTeacher(courseTeacher));
		Assert.assertEquals(courseTeacher.toString(), mapper.getCourseTeacher(courseTeacher.getId()).toString());
		Assert.assertEquals(count, mapper.getAllCourseTeachers().size());
		Assert.assertEquals(count, mapper.countCourseTeachers());
		Assert.assertEquals(1, mapper.getCourseTeachersByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteCourseTeacher(id));
	}
}
