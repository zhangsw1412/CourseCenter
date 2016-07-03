package buaa.course.model;

public class TeamStudent {
	private int id;
	private int studentId;
	private int teamId;
	public TeamStudent() {
		super();
	}
	public TeamStudent(int studentId, int teamId) {
		super();
		this.studentId = studentId;
		this.teamId = teamId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "TeamStudent [id=" + id + ", studentId=" + studentId + ", teamId=" + teamId + "]";
	}
	
}
