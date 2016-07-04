package buaa.course.model;

public class Homework {
	private int id;
	private int semesterCourseId;
	private int studentId;
	private String text;
	private String fileUrl;
	private int score;
	private String comment;
	private int submitTime;
	public Homework() {
		super();
	}
	public Homework(int semesterCourseId, int studentId, String text, String fileUrl, int score, String comment,
			int submitTime) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.studentId = studentId;
		this.text = text;
		this.fileUrl = fileUrl;
		this.score = score;
		this.comment = comment;
		this.submitTime = submitTime;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(int submitTime) {
		this.submitTime = submitTime;
	}
	@Override
	public String toString() {
		return "Homework [id=" + id + ", semesterCourseId=" + semesterCourseId + ", studentId=" + studentId + ", text="
				+ text + ", fileUrl=" + fileUrl + ", score=" + score + ", comment=" + comment + ", submitTime="
				+ submitTime + "]";
	}
}