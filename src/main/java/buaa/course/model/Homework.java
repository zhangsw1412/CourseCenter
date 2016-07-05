package buaa.course.model;

import java.sql.Timestamp;

public class Homework {
	private int id;
	private int semesterCourseId;
	private int studentId;
	private int assignmentId;
	private String text;
	private String fileUrl;
	private int score;
	private String comment;
	private Timestamp submitTime;
	public Homework() {
		super();
	}
	public Homework(int semesterCourseId, int studentId, int assignmentId, String text, String fileUrl, int score, String comment,
		
					Timestamp submitTime) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.studentId = studentId;
		this.assignmentId = assignmentId;
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
	public Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	@Override
	public String toString() {
		return "Homework [id=" + id + ", semesterCourseId=" + semesterCourseId + ", studentId=" + studentId + ", text="
				+ text + ", fileUrl=" + fileUrl + ", score=" + score + ", comment=" + comment + ", submitTime="
				+ submitTime + "]";
	}
}
