package buaa.course.model;

public class CourseTeacher {
	private int id;
	private int semesterCourseId;
	private int teacherId;
	public CourseTeacher() {
		super();
	}
	public CourseTeacher(int semesterCourseId, int teacherId) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.teacherId = teacherId;
	}
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
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	@Override
	public String toString() {
		return "CourseStudent [id=" + id + ", semesterCourseId=" + semesterCourseId + ", teacherId=" + teacherId + "]";
	}
	
}
