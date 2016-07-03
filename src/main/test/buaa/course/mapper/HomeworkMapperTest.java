package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Homework;
import junit.framework.Assert;

public class HomeworkMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	HomeworkMapper mapper = context.getBean(HomeworkMapper.class);
	int id;
	@Test
	public void test(){
		Homework homework=new Homework(1,2,"解答","F:\\sample\\sample.txt",99,"无",0);
		Assert.assertEquals(1, mapper.addHomework(homework));
		id = homework.getId();
		Assert.assertEquals(homework.toString(), mapper.getHomework(homework.getId()).toString());
		int count = mapper.countHomeworks();
		homework.setComment("优");
		Assert.assertEquals(1, mapper.updateHomework(homework));
		Assert.assertEquals(homework.toString(), mapper.getHomework(homework.getId()).toString());
		Assert.assertEquals(count, mapper.getAllHomeworks().size());
		Assert.assertEquals(count, mapper.countHomeworks());
		Assert.assertEquals(1, mapper.getHomeworksByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteHomework(id));
	}
}
