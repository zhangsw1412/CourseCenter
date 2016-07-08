package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Message;
import junit.framework.Assert;

import java.sql.Timestamp;

public class MessageMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	MessageMapper mapper = context.getBean(MessageMapper.class);
	int id;
	@Test
	public void test(){

		Message message=new Message(1,"123","John","大家好",new Timestamp(System.currentTimeMillis()/1000*1000));		
		Assert.assertEquals(1, mapper.addMessage(message));
		id = message.getId();
		Assert.assertEquals(message.toString(), mapper.getMessage(message.getId()).toString());
		int count = mapper.countMessages();
		message.setContent("优");
		Assert.assertEquals(1, mapper.updateMessage(message));
		Assert.assertEquals(message.toString(), mapper.getMessage(message.getId()).toString());
		Assert.assertEquals(count, mapper.getAllMessages().size());
		Assert.assertEquals(count, mapper.countMessages());
		Assert.assertEquals(1, mapper.getMessagesByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteMessage(id));
	}
}
