package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.SemesterCourse;
import junit.framework.Assert;

public class SemesterCourseMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	SemesterCourseMapper mapper = context.getBean(SemesterCourseMapper.class);
	int id;
	@Test
	public void test(){
		SemesterCourse semesterCourse=new SemesterCourse(50,123);
		Assert.assertEquals(1, mapper.addSemesterCourse(semesterCourse));
		id = semesterCourse.getId();
		Assert.assertEquals(semesterCourse.toString(), mapper.getSemesterCourse(semesterCourse.getId()).toString());
		int count = mapper.countSemesterCourses();
		semesterCourse.setCourseId(234);
		Assert.assertEquals(1, mapper.updateSemesterCourse(semesterCourse));
		Assert.assertEquals(semesterCourse.toString(), mapper.getSemesterCourse(semesterCourse.getId()).toString());
		Assert.assertEquals(count, mapper.getAllSemesterCourses().size());
		Assert.assertEquals(count, mapper.countSemesterCourses());
		Assert.assertEquals(1, mapper.getSemesterCoursesByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteSemesterCourse(id));
	}
}
