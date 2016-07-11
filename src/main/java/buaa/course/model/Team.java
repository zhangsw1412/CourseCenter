package buaa.course.model;

public class Team {
	private int id;
	private String name;
	private int leaderId;
	private int maxNum;
	private int num;
	private boolean applicable = true;

	public Team() {
		super();
	}
	public Team(String name, int leaderId, int maxNum, int num, boolean applicable) {
		super();
		this.name = name;
		this.leaderId = leaderId;
		this.maxNum = maxNum;
		this.num = num;
		this.applicable = applicable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public boolean isApplicable() {
		return applicable;
	}

	public void setApplicable(boolean applicable) {
		this.applicable = applicable;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", leaderId=" + leaderId + ", maxNum=" + maxNum + ", num=" + num + ", applicable=" + applicable + "]";
	}
}
