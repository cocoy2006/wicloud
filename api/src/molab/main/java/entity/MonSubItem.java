package molab.main.java.entity;

/**
 * MonSubItem entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class MonSubItem implements java.io.Serializable {

	private Integer id;
	private String monid;
	private Integer subItemId;

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
	 * @return the subItemId
	 */
	public Integer getSubItemId() {
		return subItemId;
	}

	/**
	 * @param subItemId
	 *            the subItemId to set
	 */
	public void setSubItemId(Integer subItemId) {
		this.subItemId = subItemId;
	}

}