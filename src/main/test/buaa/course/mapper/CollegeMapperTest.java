package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.College;
import junit.framework.Assert;

public class CollegeMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	CollegeMapper mapper = context.getBean(CollegeMapper.class);
	int id;
	@Test
	public void test(){
		College college=new College("asd1","软件学院");
		Assert.assertEquals(1, mapper.addCollege(college));
		id = college.getId();
		Assert.assertEquals(college.toString(), mapper.getCollege(college.getId()).toString());
		int count = mapper.countColleges();
		college.setName("计院");
		Assert.assertEquals(1, mapper.updateCollege(college));
		Assert.assertEquals(college.toString(), mapper.getCollege(college.getId()).toString());
		Assert.assertEquals(count, mapper.getAllColleges().size());
		Assert.assertEquals(count, mapper.countColleges());
		Assert.assertEquals(1, mapper.getCollegesByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteCollege(id));
	}
}
