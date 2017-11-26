package molab.main.java.entity;



/**
 * Groupindex entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Groupindex implements java.io.Serializable {

	// Fields

	private Integer groupid;
	private String groupname;
	private String userName;
	private Userinfo userinfo;

	// Constructors

	/** default constructor */
	public Groupindex() {
	}

	/** minimal constructor */

	public Groupindex(Integer groupid, String groupname, String username,Userinfo userinfo) {
		
		this.groupid = groupid;
		this.groupname = groupname;
		this.userName = username;
		this.userinfo=userinfo;
	}
	public Integer getGroupid() {
		return groupid;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
}
