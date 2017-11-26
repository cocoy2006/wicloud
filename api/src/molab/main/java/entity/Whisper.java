package molab.main.java.entity;

/**
 * MonSubItem entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Whisper implements java.io.Serializable {

	private Integer id;
	private String monid;
	private String code;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the monid
	 */
	public String getMonid() {
		return monid;
	}

	/**
	 * @param monid
	 *            the monid to set
	 */
	public void setMonid(String monid) {
		this.monid = monid;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}