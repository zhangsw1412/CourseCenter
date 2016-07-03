package buaa.course.model;

public class SemesterCourse {
	private int id;
	private int semesterId;
	private int courseId;
	public SemesterCourse() {
		super();
	}
	public SemesterCourse(int semesterId, int courseId) {
		super();
		this.semesterId = semesterId;
		this.courseId = courseId;
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
	@Override
	public String toString() {
		return "SemesterCourse [id=" + id + ", semesterId=" + semesterId + ", courseId=" + courseId + "]";
	}
	
}
