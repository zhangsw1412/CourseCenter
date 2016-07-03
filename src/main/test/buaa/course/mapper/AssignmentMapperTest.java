package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Assignment;
import junit.framework.Assert;

public class AssignmentMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	AssignmentMapper mapper = context.getBean(AssignmentMapper.class);
	int id;
	@Test
	public void test(){
		Assignment assignment=new Assignment(1,"无","F:\\sample\\sample.txt",0,0,false,100);
		Assert.assertEquals(1, mapper.addAssignment(assignment));
		id = assignment.getId();
		Assert.assertEquals(assignment.toString(), mapper.getAssignment(assignment.getId()).toString());
		int count = mapper.countAssignments();
		assignment.setHighestScore(50);
		Assert.assertEquals(1, mapper.updateAssignment(assignment));
		Assert.assertEquals(assignment.toString(), mapper.getAssignment(assignment.getId()).toString());
		Assert.assertEquals(count, mapper.getAllAssignments().size());
		Assert.assertEquals(count, mapper.countAssignments());
		Assert.assertEquals(1, mapper.getAssignmentsByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteAssignment(id));
	}
}
