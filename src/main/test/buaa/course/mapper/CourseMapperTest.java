package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Course;
import junit.framework.Assert;

public class CourseMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	CourseMapper mapper = context.getBean(CourseMapper.class);
	int id;
	@Test
	public void test(){
		Course course=new Course("asd1","软件工程过程",1,32,2,false,false);
		Assert.assertEquals(1, mapper.addCourse(course));
		id = course.getId();
		Assert.assertEquals(course.toString(), mapper.getCourse(course.getId()).toString());
		int count = mapper.countCourses();
		course.setName("软件工程实践");
		Assert.assertEquals(1, mapper.updateCourse(course));
		Assert.assertEquals(course.toString(), mapper.getCourse(course.getId()).toString());
		Assert.assertEquals(count, mapper.getAllCourses().size());
		Assert.assertEquals(count, mapper.countCourses());
		Assert.assertEquals(1, mapper.getCoursesByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteCourse(id));
	}
}
