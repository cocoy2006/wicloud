package molab.main.java.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Monindex entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Monindex implements java.io.Serializable {

	// Fields

	private String monid;
	private String monModle;
	private String monAdd;
	private String wallType;
	private Integer wallExp;
	private Integer wallDis;
	private String userName;
	private String monname;
	private Integer groupid;
	private Integer total;
	@SuppressWarnings("unchecked")
	private Set origdatatemps = new HashSet(0);
	@SuppressWarnings("unchecked")
	private Set realtimedatas = new HashSet(0);
	@SuppressWarnings("unchecked")
	private Set realtimedataIns = new HashSet(0);

	// Constructors

	/** default constructor */
	public Monindex() {
	}
	public Monindex(String monid, String monname) {
		this.monid = monid;
		this.monname = monname;
	}
	/** minimal constructor */
	public Monindex(String monid, String userName, String monname) {
		this.monid = monid;
		this.userName = userName;
		this.monname = monname;
	}

	/** full constructor */
	@SuppressWarnings("unchecked")
	public Monindex(String monid, String userName, String monModle,
			String monAdd, String wallType, Integer wallExp, Integer wallDis,
			String monname, Integer groupid, Integer total, Set origdatatemps,
			Set realtimedatas, Set realtimedataIns) {
		this.monid = monid;
		this.userName = userName;
		this.monModle = monModle;
		this.monAdd = monAdd;
		this.wallType = wallType;
		this.wallExp = wallExp;
		this.wallDis = wallDis;
		this.monname = monname;
		this.groupid = groupid;
		this.total = total;
		this.origdatatemps = origdatatemps;
		this.realtimedatas = realtimedatas;
		this.realtimedataIns = realtimedataIns;
	}

	// Property accessors

	public String getMonid() {
		return this.monid;
	}

	public void setMonid(String monid) {
		this.monid = monid;
	}

	public String getMonModle() {
		return this.monModle;
	}

	public void setMonModle(String monModle) {
		this.monModle = monModle;
	}

	public String getMonAdd() {
		return this.monAdd;
	}

	public void setMonAdd(String monAdd) {
		this.monAdd = monAdd;
	}

	public String getWallType() {
		return this.wallType;
	}

	public void setWallType(String wallType) {
		this.wallType = wallType;
	}

	public Integer getWallExp() {
		return this.wallExp;
	}

	public void setWallExp(Integer wallExp) {
		this.wallExp = wallExp;
	}

	public Integer getWallDis() {
		return this.wallDis;
	}

	public void setWallDis(Integer wallDis) {
		this.wallDis = wallDis;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMonname() {
		return this.monname;
	}

	public void setMonname(String monname) {
		this.monname = monname;
	}
	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@SuppressWarnings("unchecked")
	public Set getOrigdatatemps() {
		return this.origdatatemps;
	}

	@SuppressWarnings("unchecked")
	public void setOrigdatatemps(Set origdatatemps) {
		this.origdatatemps = origdatatemps;
	}

	@SuppressWarnings("unchecked")
	public Set getRealtimedatas() {
		return this.realtimedatas;
	}

	@SuppressWarnings("unchecked")
	public void setRealtimedatas(Set realtimedatas) {
		this.realtimedatas = realtimedatas;
	}

	@SuppressWarnings("unchecked")
	public Set getRealtimedataIns() {
		return this.realtimedataIns;
	}

	@SuppressWarnings("unchecked")
	public void setRealtimedataIns(Set realtimedataIns) {
		this.realtimedataIns = realtimedataIns;
	}

}