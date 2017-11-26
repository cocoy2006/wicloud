package molab.main.java.entity;

/**
 * Realtimedata entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Realtimedata implements java.io.Serializable {

	// Fields

	private RealtimedataId id;
	private Monindex monindex;

	// Constructors

	/** default constructor */
	public Realtimedata() {
	}

	/** minimal constructor */
	public Realtimedata(RealtimedataId id) {
		this.id = id;
	}

	/** full constructor */
	public Realtimedata(RealtimedataId id, Monindex monindex) {
		this.id = id;
		this.monindex = monindex;
	}

	// Property accessors

	public RealtimedataId getId() {
		return this.id;
	}

	public void setId(RealtimedataId id) {
		this.id = id;
	}

	public Monindex getMonindex() {
		return this.monindex;
	}

	public void setMonindex(Monindex monindex) {
		this.monindex = monindex;
	}

}