package buaa.course.model;

import java.util.Arrays;

public class User {
	private int num;
	private String id;
	private String password;
	private String name;
	private boolean gender;
	private int type;
	private boolean valid;
	private long lastLoginTime;
	private String lastLoginIp;
	public User() {
		super();
	}
	public User(int num, String id, String password, String name, boolean gender, int type, boolean valid,
			long lastLoginTime, String lastLoginIp) {
		super();
		this.num = num;
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.type = type;
		this.valid = valid;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Override
	public String toString() {
		return "User [num=" + num + ", id=" + id + ", password=" + password + ", name=" + name
				+ ", gender=" + gender + ", type=" + type + ", valid=" + valid + ", lastLoginTime=" + lastLoginTime
				+ ", lastLoginIp=" + lastLoginIp + "]";
	}
	
}
