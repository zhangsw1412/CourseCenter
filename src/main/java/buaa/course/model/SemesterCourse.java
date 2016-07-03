package buaa.course.model;

public class SemesterCourse {
	private int id;
	private int semesterId;
	private int courseId;
	private String teacher;
	public SemesterCourse() {
		super();
	}
	public SemesterCourse(int semesterId, int courseId, String teacher) {
		super();
		this.semesterId = semesterId;
		this.courseId = courseId;
		this.teacher = teacher;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "SemesterCourse [id=" + id + ", semesterId=" + semesterId + ", courseId=" + courseId + ", teacher="
				+ teacher + "]";
	}
	
}
