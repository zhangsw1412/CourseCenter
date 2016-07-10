package buaa.course.model;

import java.sql.Timestamp;

public class Homework {
	private int id;
	private int semesterCourseId;
	private int studentId;
	private int assignmentId;
	private String text;
	private String fileUrl;
	private int score = -1;
	private String comment;
	private String correctFileUrl;
	private Timestamp submitTime;
	public Homework() {
		super();
	}
	public Homework(int semesterCourseId, int studentId, int assignmentId, String text, String fileUrl, int score, String comment,
		
					String correctFileUrl, Timestamp submitTime) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.studentId = studentId;
		this.assignmentId = assignmentId;
		this.text = text;
		this.fileUrl = fileUrl;
		this.score = score;
		this.comment = comment;
		this.correctFileUrl = correctFileUrl;
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
	public int getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
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
	public String getCorrectFileUrl() {
		return correctFileUrl;
	}
	public void setCorrectFileUrl(String correctFileUrl) {
		this.correctFileUrl = correctFileUrl;
	}
	public Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	@Override
	public String toString() {
		return "Homework [id=" + id + ", semesterCourseId=" + semesterCourseId + ", studentId=" + studentId + ", text="
				+ text + ", fileUrl=" + fileUrl + ", score=" + score + ", comment=" + comment + ", correctFileUrl=" + correctFileUrl 
				+ ", submitTime="+ submitTime + "]";
	}
}
