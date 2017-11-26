package molab.main.java.entity;

/**
 * RealtimedataIn entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class RealtimedataIn implements java.io.Serializable {

	// Fields

	private RealtimedataInId id;
	private Monindex monindex;

	// Constructors

	/** default constructor */
	public RealtimedataIn() {
	}

	/** minimal constructor */
	public RealtimedataIn(RealtimedataInId id) {
		this.id = id;
	}

	/** full constructor */
	public RealtimedataIn(RealtimedataInId id, Monindex monindex) {
		this.id = id;
		this.monindex = monindex;
	}

	// Property accessors

	public RealtimedataInId getId() {
		return this.id;
	}

	public void setId(RealtimedataInId id) {
		this.id = id;
	}

	public Monindex getMonindex() {
		return this.monindex;
	}

	public void setMonindex(Monindex monindex) {
		this.monindex = monindex;
	}

}