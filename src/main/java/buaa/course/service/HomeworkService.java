package buaa.course.service;

import buaa.course.mapper.AssignmentMapper;
import buaa.course.mapper.HomeworkMapper;
import buaa.course.model.Assignment;
import buaa.course.model.Homework;
import buaa.course.model.Team;
import buaa.course.model.TeamStudent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HomeworkService {
    @Resource(name = "homeworkMapper")
    private HomeworkMapper homeworkMapper;
    @Resource(name = "assignmentService")
    private AssignmentService assignmentService;

    @Resource(name = "teamService")
    private TeamService teamService;

    public Homework getHomeworkById(int id) {
        return homeworkMapper.getHomework(id);
    }

    public int createHomework(Homework homework) {
        return homeworkMapper.addHomework(homework);
    }

    public int updateHomework(Homework homework) {
        return homeworkMapper.updateHomework(homework);
    }

    public int deleteHomework(int id) {
        return homeworkMapper.deleteHomework(id);
    }

    public List<Homework> getAllHomeworks() {
        return homeworkMapper.getAllHomeworks();
    }

    public List<Homework> getHomeworksByRange(int start, int length) {
        return homeworkMapper.getHomeworksByRange(start, length);
    }

    public int countHomeworks() {
        return homeworkMapper.countHomeworks();
    }

	public int getHighestScore(int homeworkId) {
		return assignmentService.getAssignmentById(homeworkMapper.getHomework(homeworkId).getAssignmentId()).getHighestScore();
	}

	public List<Homework> getHomeworksByAssignmentId(int assignmentId) {
		return homeworkMapper.getHomeworksByAssignmentId(assignmentId);
	}

    public Map<Long, Homework> getHomeworksByAssignments(List<Assignment> assignmentlist, int studentId) {
        Map<Long, Homework> result = new HashMap<>();
        for(Assignment assignment : assignmentlist){
            Homework homework = getHomeworkByAssignment(assignment.getId(), studentId);
            result.put(Long.valueOf(assignment.getId()), homework);

            if(assignment.isTeamAvaliable()){
                Team team = teamService.getTeamByStudent(assignment.getSemesterCourseId(), studentId);
                if(team != null){
                    for(Integer student : teamService.getTeamMemberIds(team)){
                        homework = getHomeworkByAssignment(assignment.getId(), student);
                        if(homework != null){
                            result.put(Long.valueOf(assignment.getId()), homework);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public Homework getHomeworkByAssignment(int assignmentId, int studentId) {
        Homework homework = getHomeWorkByAssignmentByAssignmentIdAndStudentId(assignmentId, studentId);
        if(homework != null)
            return homework;

        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        if(assignment.isTeamAvaliable()){
            Team team = teamService.getTeamByStudent(assignment.getSemesterCourseId(), studentId);
            if(team != null){
                for(Integer student : teamService.getTeamMemberIds(team)){
                    homework = getHomeWorkByAssignmentByAssignmentIdAndStudentId(assignment.getId(), student);
                    if(homework != null){
                        return homework;
                    }
                }
            }
        }
        return homework;
    }

    public Homework getHomeWorkByAssignmentByAssignmentIdAndStudentId(int assignmentId, int studentId) {
        return homeworkMapper.getHomeworkByAssignment(assignmentId, studentId);
    }

    public void submitTeamHomework(Assignment assignment, Homework homework) {

    }
}
