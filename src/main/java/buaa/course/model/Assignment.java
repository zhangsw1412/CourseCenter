package buaa.course.model;

import java.sql.Timestamp;

public class Assignment {
	private int id;
	private int semesterCourseId;
	private String basicRequirement;
	private String fileUrl;
	private Timestamp startTime;
	private Timestamp deadline;
	private boolean teamAvaliable;
	private int highestScore;
	public Assignment() {
		super();
	}
	public Assignment(int semesterCourseId, String basicRequirement, String fileUrl, Timestamp startTime, Timestamp deadline,
			boolean teamAvaliable, int highestScore) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.basicRequirement = basicRequirement;
		this.fileUrl = fileUrl;
		this.startTime = startTime;
		this.deadline = deadline;
		this.teamAvaliable = teamAvaliable;
		this.highestScore = highestScore;
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
	public String getBasicRequirement() {
		return basicRequirement;
	}
	public void setBasicRequirement(String basicRequirement) {
		this.basicRequirement = basicRequirement;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getDeadline() {
		return deadline;
	}
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	public boolean isTeamAvaliable() {
		return teamAvaliable;
	}
	public void setTeamAvaliable(boolean teamAvaliable) {
		this.teamAvaliable = teamAvaliable;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	@Override
	public String toString() {
		return "Assignment [id=" + id + ", semesterCourseId=" + semesterCourseId + ", basicRequirement="
				+ basicRequirement + ", fileUrl=" + fileUrl + ", startTime=" + startTime + ", deadline=" + deadline
				+ ", teamAvaliable=" + teamAvaliable + ", highestScore=" + highestScore + "]";
	}
}
