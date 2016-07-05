package buaa.course.model;

import java.sql.Timestamp;
import java.util.Date;

public class Semester
{
	private int id;
	private int schoolYear;
	private int season;
	private Date startDate;
	private Date endDate;
	private int weeks;

	public Semester()
	{
		super();
	}

	public Semester(int id, int schoolYear, int season, Date startDate,
			Date endDate, int weeks)
	{
		super();
		this.id = id;
		this.schoolYear = schoolYear;
		this.season = season;
		this.startDate = startDate;
		this.endDate = endDate;
		this.weeks = weeks;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getSchoolYear()
	{
		return schoolYear;
	}

	public void setSchoolYear(int schoolYear)
	{
		this.schoolYear = schoolYear;
	}

	public int getSeason()
	{
		return season;
	}

	public void setSeason(int season)
	{
		this.season = season;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Timestamp startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Timestamp endDate)
	{
		this.endDate = endDate;
	}

	public int getWeeks()
	{
		return weeks;
	}

	public void setWeeks(int weeks)
	{
		this.weeks = weeks;
	}

	@Override
	public String toString()
	{
		return "Semester [id=" + id + ", schoolYear=" + schoolYear + ", season="
				+ season + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", weeks=" + weeks + "]";
	}

}
