package buaa.course.model;

public class Semester {
	private int id;
	private int schoolYear;
	private int season;
	private int startDate;
	private int endDate;
	private int weeks;
	public Semester() {
		super();
	}
	public Semester(int schoolYear, int season, int startDate, int endDate, int weeks) {
		super();
		this.schoolYear = schoolYear;
		this.season = season;
		this.startDate = startDate;
		this.endDate = endDate;
		this.weeks = weeks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	public int getWeeks() {
		return weeks;
	}
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	@Override
	public String toString() {
		return "Semester [id=" + id + ", schoolYear=" + schoolYear + ", season=" + season + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", weeks=" + weeks + "]";
	}
	
}
