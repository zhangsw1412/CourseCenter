package buaa.course.model;

public class College {
	private int id;
	private String collegeCode;
	private String name;
	public College() {
		super();
	}
	public College(int id, String collegeCode, String name) {
		super();
		this.id = id;
		this.collegeCode = collegeCode;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "College [id=" + id + ", collegeCode=" + collegeCode + ", name=" + name + "]";
	}
	
}
