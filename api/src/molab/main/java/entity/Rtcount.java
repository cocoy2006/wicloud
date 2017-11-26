package molab.main.java.entity;

/**
 * Rtcount entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Rtcount implements java.io.Serializable {

	// Fields

	private RtcountId id;
	private Integer incount;
	private Integer allcount;

	// Constructors

	/** default constructor */
	public Rtcount() {
	}

	/** minimal constructor */
	public Rtcount(RtcountId id) {
		this.id = id;
	}

	/** full constructor */
	public Rtcount(RtcountId id, Integer incount, Integer allcount) {
		this.id = id;
		this.incount = incount;
		this.allcount = allcount;
	}

	// Property accessors

	public RtcountId getId() {
		return this.id;
	}

	public void setId(RtcountId id) {
		this.id = id;
	}

	public Integer getIncount() {
		return this.incount;
	}

	public void setIncount(Integer incount) {
		this.incount = incount;
	}

	public Integer getAllcount() {
		return this.allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

}