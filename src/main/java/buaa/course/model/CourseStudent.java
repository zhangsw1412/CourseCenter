package buaa.course.model;

public class CourseStudent {
	private int id;
	private int semesterCourseId;
	private int studentId;
	private int teamId;
	public CourseStudent() {
		super();
	}
	public CourseStudent(int semesterCourseId, int studentId, int teamId) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.studentId = studentId;
		this.teamId = teamId;
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
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	@Override
	public String toString() {
		return "CourseStudent [id=" + id + ", semesterCourseId=" + semesterCourseId + ", studentId=" + studentId
				+ ", teamId=" + teamId + "]";
	}
	
}
