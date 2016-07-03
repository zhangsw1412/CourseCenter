package buaa.course.mapper;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Team;
import junit.framework.Assert;

public class TeamMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	TeamMapper mapper = context.getBean(TeamMapper.class);
	int id;
	@Test
	public void test(){
		Team team=new Team("小组",11111111,20);
		Assert.assertEquals(1, mapper.addTeam(team));
		id = team.getId();
		Assert.assertEquals(team.toString(), mapper.getTeam(team.getId()).toString());
		int count = mapper.countTeams();
		team.setName("大组");
		Assert.assertEquals(1, mapper.updateTeam(team));
		Assert.assertEquals(team.toString(), mapper.getTeam(team.getId()).toString());
		Assert.assertEquals(count, mapper.getAllTeams().size());
		Assert.assertEquals(count, mapper.countTeams());
		Assert.assertEquals(1, mapper.getTeamsByRange(0,1).size());
	}
	@After
	public void after()
	{
		Assert.assertEquals(1, mapper.deleteTeam(id));
	}
}
