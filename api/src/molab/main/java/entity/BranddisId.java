package molab.main.java.entity;

import java.util.Date;

/**
 * BranddisId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BranddisId implements java.io.Serializable {

	// Fields

	private String monid;
	private Date time;

	// Constructors

	/** default constructor */
	public BranddisId() {
	}

	/** full constructor */
	public BranddisId(String monid, Date time) {
		this.monid = monid;
		this.time = time;
	}

	// Property accessors

	public String getMonid() {
		return this.monid;
	}

	public void setMonid(String monid) {
		this.monid = monid;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BranddisId))
			return false;
		BranddisId castOther = (BranddisId) other;

		return ((this.getMonid() == castOther.getMonid()) || (this.getMonid() != null
				&& castOther.getMonid() != null && this.getMonid().equals(
				castOther.getMonid())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMonid() == null ? 0 : this.getMonid().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		return result;
	}

}