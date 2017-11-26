package molab.main.java.entity;

/**
 * OrigdatatempId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class OrigdatatempId implements java.io.Serializable {

	// Fields

	private String mac;
	private Integer rssi;
	private Integer monTime;
	private Monindex monindex;

	// Constructors

	/** default constructor */
	public OrigdatatempId() {
	}

	/** full constructor */
	public OrigdatatempId(String mac, Integer rssi, Integer monTime,
			Monindex monindex) {
		this.mac = mac;
		this.rssi = rssi;
		this.monTime = monTime;
		this.monindex = monindex;
	}

	// Property accessors

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getRssi() {
		return this.rssi;
	}

	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}

	public Integer getMonTime() {
		return this.monTime;
	}

	public void setMonTime(Integer monTime) {
		this.monTime = monTime;
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
		if (!(other instanceof OrigdatatempId))
			return false;
		OrigdatatempId castOther = (OrigdatatempId) other;

		return ((this.getMac() == castOther.getMac()) || (this.getMac() != null
				&& castOther.getMac() != null && this.getMac().equals(
				castOther.getMac())))
				&& ((this.getRssi() == castOther.getRssi()) || (this.getRssi() != null
						&& castOther.getRssi() != null && this.getRssi()
						.equals(castOther.getRssi())))
				&& ((this.getMonTime() == castOther.getMonTime()) || (this
						.getMonTime() != null
						&& castOther.getMonTime() != null && this.getMonTime()
						.equals(castOther.getMonTime())))
				&& ((this.getMonindex() == castOther.getMonindex()) || (this
						.getMonindex() != null
						&& castOther.getMonindex() != null && this
						.getMonindex().equals(castOther.getMonindex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMac() == null ? 0 : this.getMac().hashCode());
		result = 37 * result
				+ (getRssi() == null ? 0 : this.getRssi().hashCode());
		result = 37 * result
				+ (getMonTime() == null ? 0 : this.getMonTime().hashCode());
		result = 37 * result
				+ (getMonindex() == null ? 0 : this.getMonindex().hashCode());
		return result;
	}

}