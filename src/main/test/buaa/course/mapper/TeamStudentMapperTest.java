package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.TeamStudent;
import junit.framework.Assert;

public class TeamStudentMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	TeamStudentMapper mapper = context.getBean(TeamStudentMapper.class);
	int id;
	@Test
	public void test(){
		TeamStudent teamStudent=new TeamStudent(11111111,111111);
		Assert.assertEquals(1, mapper.addTeamStudent(teamStudent));
		id = teamStudent.getId();
		Assert.assertEquals(teamStudent.toString(), mapper.getTeamStudent(teamStudent.getId()).toString());
		int count = mapper.countTeamStudents();
		teamStudent.setStudentId(22222222);
		Assert.assertEquals(1, mapper.updateTeamStudent(teamStudent));
		Assert.assertEquals(teamStudent.toString(), mapper.getTeamStudent(teamStudent.getId()).toString());
		Assert.assertEquals(count, mapper.getAllTeamStudents().size());
		Assert.assertEquals(count, mapper.countTeamStudents());
		Assert.assertEquals(1, mapper.getTeamStudentsByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteTeamStudent(id));
	}
}
