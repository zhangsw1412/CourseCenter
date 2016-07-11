package buaa.course.model;

import java.security.Timestamp;

/**
 * Created by 熊纪元 on 2016/7/10.
 */
public class CourseApplication {
    private int id;
    private int teamId;
    private int semesterCourseId;
    private Timestamp applyTime;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSemesterCourseId() {
        return semesterCourseId;
    }

    public void setSemesterCourseId(int semesterCourseId) {
        this.semesterCourseId = semesterCourseId;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseApplication{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", semesterCourseId=" + semesterCourseId +
                ", applyTime=" + applyTime +
                ", status=" + status +
                '}';
    }
}
