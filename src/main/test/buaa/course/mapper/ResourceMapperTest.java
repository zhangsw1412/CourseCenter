package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Resource;
import junit.framework.Assert;

public class ResourceMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	ResourceMapper mapper = context.getBean(ResourceMapper.class);
	int id;
	@Test
	public void test(){
		Resource resource=new Resource(1,"F:\\sample\\sample.txt","资料");
		Assert.assertEquals(1, mapper.addResource(resource));
		id = resource.getId();
		Assert.assertEquals(resource.toString(), mapper.getResource(resource.getId()).toString());
		int count = mapper.countResources();
		resource.setCategory("课件");
		Assert.assertEquals(1, mapper.updateResource(resource));
		Assert.assertEquals(resource.toString(), mapper.getResource(resource.getId()).toString());
		Assert.assertEquals(count, mapper.getAllResources().size());
		Assert.assertEquals(count, mapper.countResources());
		Assert.assertEquals(1, mapper.getResourcesByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteResource(id));
	}
}
