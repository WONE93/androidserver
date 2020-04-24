package co.edu.app.member.model;

public class MemberVO {

	private String userid;
	private String userpw;
	private String username;
	private String jobid;
	private String hobby;
	private String info;
	private String gender;
	private String regdate;
	
	public String getUserid() {
		return userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public String getUsername() {
		return username;
	}
	public String getJobid() {
		return jobid;
	}
	public String getHobby() {
		return hobby;
	}
	public String getInfo() {
		return info;
	}
	public String getGender() {
		return gender;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setJobid(String job) {
		this.jobid = job;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
