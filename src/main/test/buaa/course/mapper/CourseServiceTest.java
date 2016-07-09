package buaa.course.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.course.model.Course;
import buaa.course.service.CourseService;

public class CourseServiceTest { 
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	
	@Test
	 public void main() {
		CourseService courseService = context.getBean(CourseService.class);
		List<Course> courses=courseService.getCoursesByStudent(2, 1);
		for(Course course:courses)
			System.out.println(course);
	}
}
