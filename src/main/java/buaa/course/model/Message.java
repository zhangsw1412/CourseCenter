package buaa.course.model;

import java.sql.Timestamp;

public class Message {
	private int id;
	private int semesterCourseId;
	private int userNum;
	private String userId;
	private String userName;
	private String content;
	private Timestamp createTime;
	
	public Message() {
		super();
	}



	public Message(int semesterCourseId, int userNum, String userId, String userName, String content, Timestamp createTime) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.userNum = userNum;
		this.userId = userId;

		this.userName = userName;
		this.content = content;
		this.createTime = createTime;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", semesterCourseId=" + semesterCourseId +
				", userNum=" + userNum +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
