package buaa.course.model;

import java.sql.Timestamp;

/**
 * Created by 熊纪元 on 2016/7/11.
 */
public class ResourceCategory {
    private int id;
    private int semesterCourseId;
    private String category;
    private Timestamp createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemesterCourseId() {
        return semesterCourseId;
    }

    public void setSemesterCourseId(int semesterCourseId) {
        this.semesterCourseId = semesterCourseId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ResourceCategory{" +
                "id=" + id +
                ", semesterCourseId=" + semesterCourseId +
                ", category='" + category + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
