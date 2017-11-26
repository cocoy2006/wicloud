package molab.main.java.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Agentinfo implements java.io.Serializable {

	// Fields

	private String userName;
	private Integer id;
	private String passwd;
	private String userType;
	private String shopadd;
	private String shopName;
	private String contract;
	@SuppressWarnings("unchecked")
	private Set monindexes = new HashSet(0);
	@SuppressWarnings("unchecked")
	private Set groupindexes = new HashSet(0);
	// Constructors

	/** default constructor */
	public Agentinfo() {
	}

	/** minimal constructor */
	public Agentinfo(String userName, String passwd, String userType) {
		this.userName = userName;
		this.passwd = passwd;
		this.userType = userType;
	}

	/** full constructor */
	@SuppressWarnings("unchecked")
	public Agentinfo(String userName, Integer id, String passwd,
			String userType, String shopadd, String shopName, String contract,
			Set monindexes,Set groupindexes) {
		this.userName = userName;
		this.id = id;
		this.passwd = passwd;
		this.userType = userType;
		this.shopadd = shopadd;
		this.shopName = shopName;
		this.contract = contract;
		this.monindexes = monindexes;
		this.groupindexes= groupindexes;
	}

	// Property accessors

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getShopadd() {
		return this.shopadd;
	}

	public void setShopadd(String shopadd) {
		this.shopadd = shopadd;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getContract() {
		return this.contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	@SuppressWarnings("unchecked")
	public Set getMonindexes() {
		return this.monindexes;
	}

	@SuppressWarnings("unchecked")
	public void setMonindexes(Set monindexes) {
		this.monindexes = monindexes;
	}

	@SuppressWarnings("unchecked")
	public Set getGroupindexes() {
		return this.groupindexes;
	}

	@SuppressWarnings("unchecked")
	public void setGroupindexes(Set groupindexes) {
		this.groupindexes = groupindexes;
	}

}