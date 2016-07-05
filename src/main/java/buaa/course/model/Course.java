package buaa.course.model;

public class Course {
	private int id;
	private String courseCode;
	private String name;
	private int collegeId;
	private int period;
	private int credit;
	private boolean type;
	private boolean teamAvaliable;

	public Course() {
		super();
	}
	public Course(String courseCode, String name, int collegeId, int period, int credit, boolean type,
			boolean teamAvaliable) {
		super();
		this.courseCode = courseCode;
		this.name = name;
		this.collegeId = collegeId;
		this.period = period;
		this.credit = credit;
		this.type = type;
		this.teamAvaliable = teamAvaliable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public boolean isTeamAvaliable() {
		return teamAvaliable;
	}
	public void setTeamAvaliable(boolean teamAvaliable) {
		this.teamAvaliable = teamAvaliable;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseCode=" + courseCode + ", name=" + name + ", collegeId=" + collegeId
				+ ", period=" + period + ", credit=" + credit + ", type=" + type + ", teamAvaliable=" + teamAvaliable
				+ "]";
	}
}
