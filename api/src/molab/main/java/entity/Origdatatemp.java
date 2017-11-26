package molab.main.java.entity;

/**
 * Origdatatemp entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Origdatatemp implements java.io.Serializable {

	// Fields

	private OrigdatatempId id;
	private Monindex monindex;

	// Constructors

	/** default constructor */
	public Origdatatemp() {
	}

	/** minimal constructor */
	public Origdatatemp(OrigdatatempId id) {
		this.id = id;
	}

	/** full constructor */
	public Origdatatemp(OrigdatatempId id, Monindex monindex) {
		this.id = id;
		this.monindex = monindex;
	}

	// Property accessors

	public OrigdatatempId getId() {
		return this.id;
	}

	public void setId(OrigdatatempId id) {
		this.id = id;
	}

	public Monindex getMonindex() {
		return this.monindex;
	}

	public void setMonindex(Monindex monindex) {
		this.monindex = monindex;
	}

}