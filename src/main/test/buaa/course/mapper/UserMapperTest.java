package buaa.course.mapper;

import buaa.course.model.User;
import buaa.course.service.UserService;
import buaa.course.utils.PasswordEncoder;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	UserMapper mapper = context.getBean(UserMapper.class);
	UserService logic = context.getBean(UserService.class);
	PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
	int num;

	@Test
	public void test(){
		User user=new User("asd1","ewoifjoewf","杰克",false,0,true,System.currentTimeMillis()/1000,"192.168.1.1");
		Assert.assertEquals(1, mapper.addUser(user));
		num = user.getNum();
		Assert.assertEquals(user.toString(), mapper.getUser(user.getId()).toString());
		int count = mapper.countUsers();
		user.setName("Jack");
		Assert.assertEquals(1, mapper.updateUser(user));
		Assert.assertEquals(user.toString(), mapper.getUser(user.getId()).toString());
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
