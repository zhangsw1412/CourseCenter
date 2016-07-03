package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.User;
import junit.framework.Assert;

public class UserMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	UserMapper mapper = context.getBean(UserMapper.class);
	int num;
	@Test
	public void test(){
		User user=new User("asd1","ewoifjoewf","杰克",false,0,true,System.currentTimeMillis()/1000,"192.168.1.1");
		Assert.assertEquals(1, mapper.addUser(user));
		num = user.getNum();
		Assert.assertEquals(user.toString(), mapper.getUser(user.getNum()).toString());
		int count = mapper.countUsers();
		user.setName("Jack");
		Assert.assertEquals(1, mapper.updateUser(user));
		Assert.assertEquals(user.toString(), mapper.getUser(user.getNum()).toString());
		Assert.assertEquals(count, mapper.getAllUsers().size());
		Assert.assertEquals(count, mapper.countUsers());
		Assert.assertEquals(1, mapper.getUsersByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteUser(num));
	}
}
