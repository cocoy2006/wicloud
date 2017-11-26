package molab.main.java.entity;

/**
 * RealtimedataId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class RealtimedataId implements java.io.Serializable {

	// Fields

	private Integer monTime;
	private Integer traffic;
	private Monindex monindex;

	// Constructors

	/** default constructor */
	public RealtimedataId() {
	}

	/** full constructor */
	public RealtimedataId(Integer monTime, Integer traffic, Monindex monindex) {
		this.monTime = monTime;
		this.traffic = traffic;
		this.monindex = monindex;
	}

	// Property accessors

	public Integer getMonTime() {
		return this.monTime;
	}

	public void setMonTime(Integer monTime) {
		this.monTime = monTime;
	}

	public Integer getTraffic() {
		return this.traffic;
	}

	public void setTraffic(Integer traffic) {
		this.traffic = traffic;
	}

	public Monindex getMonindex() {
		return this.monindex;
	}

	public void setMonindex(Monindex monindex) {
		this.monindex = monindex;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RealtimedataId))
			return false;
		RealtimedataId castOther = (RealtimedataId) other;

		return ((this.getMonTime() == castOther.getMonTime()) || (this
				.getMonTime() != null
				&& castOther.getMonTime() != null && this.getMonTime().equals(
				castOther.getMonTime())))
				&& ((this.getTraffic() == castOther.getTraffic()) || (this
						.getTraffic() != null
						&& castOther.getTraffic() != null && this.getTraffic()
						.equals(castOther.getTraffic())))
				&& ((this.getMonindex() == castOther.getMonindex()) || (this
						.getMonindex() != null
						&& castOther.getMonindex() != null && this
						.getMonindex().equals(castOther.getMonindex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMonTime() == null ? 0 : this.getMonTime().hashCode());
		result = 37 * result
				+ (getTraffic() == null ? 0 : this.getTraffic().hashCode());
		result = 37 * result
				+ (getMonindex() == null ? 0 : this.getMonindex().hashCode());
		return result;
	}

}