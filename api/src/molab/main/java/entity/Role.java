package molab.main.java.entity;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Role implements java.io.Serializable {

	private Integer id;
	private String name;
	private String menus;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the menus
	 */
	public String getMenus() {
		return menus;
	}

	/**
	 * @param menus
	 *            the menus to set
	 */
	public void setMenus(String menus) {
		this.menus = menus;
	}

}