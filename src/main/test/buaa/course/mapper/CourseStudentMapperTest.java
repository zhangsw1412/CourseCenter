package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.CourseStudent;
import junit.framework.Assert;

public class CourseStudentMapperTest
{
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:application-config.xml");
	CourseStudentMapper courseStudentMapper = context
			.getBean(CourseStudentMapper.class);

	int id;

	@Test
	public void test()
	{
		CourseStudent courseStudent = new CourseStudent();
		Assert.assertEquals(1,
				courseStudentMapper.addCourseStudent(courseStudent));
		id = courseStudent.getId();

		Assert.assertEquals(courseStudent.toString(),
				courseStudentMapper.getCourseStudent(id).toString());

		courseStudent.setTeamId(2);
		Assert.assertEquals(1,
				courseStudentMapper.updateCourseStudent(courseStudent));
		Assert.assertEquals(courseStudent.toString(),
				courseStudentMapper.getCourseStudent(id).toString());

		int count = courseStudentMapper.countCourseStudents();
		Assert.assertEquals(count,
				courseStudentMapper.getAllCourseStudents().size());

		Assert.assertEquals(1,
				courseStudentMapper.getCourseStudentsByRange(0, 1).size());
	}

	@After
	public void after()
	{
		Assert.assertEquals(1, courseStudentMapper.deleteCourseStudent(id));
	}
}
