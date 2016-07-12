package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Semester;
import junit.framework.Assert;

public class SemesterMapperTest
{
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:application-config.xml");
	SemesterMapper semesterMapper = context.getBean(SemesterMapper.class);

	int id;

	@Test
	public void test()
	{
		Semester semester = new Semester();
		Assert.assertEquals(1, semesterMapper.addSemester(semester));
		id = semester.getId();
		Assert.assertEquals(semester.toString(),semesterMapper.getSemester(id).toString());
		semester.setSchoolYear(2);
		Assert.assertEquals(1, semesterMapper.updateSemester(semester));
		Assert.assertEquals(semester.toString(),semesterMapper.getSemester(id).toString());
		int count = semesterMapper.countSemesters();
		Assert.assertEquals(count, semesterMapper.getAllSemesters().size());
		Assert.assertEquals(1, semesterMapper.getSemestersByRange(0, 1).size());
	}

	@After
	public void after()
	{
		Assert.assertEquals(1, semesterMapper.deleteSemester(id));
	}
}
