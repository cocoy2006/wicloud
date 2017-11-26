package molab.main.java.component;

import molab.main.java.entity.Item;
import molab.main.java.entity.Monindex;
import molab.main.java.entity.SubItem;
import molab.main.java.entity.Whisper;

public class MSIComponent {

	private Monindex mon;
	private Item item;
	private SubItem subItem;
	private Whisper whisper;

	/**
	 * @return the mon
	 */
	public Monindex getMon() {
		return mon;
	}

	/**
	 * @param mon
	 *            the mon to set
	 */
	public void setMon(Monindex mon) {
		this.mon = mon;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the subItem
	 */
	public SubItem getSubItem() {
		return subItem;
	}

	/**
	 * @param subItem
	 *            the subItem to set
	 */
	public void setSubItem(SubItem subItem) {
		this.subItem = subItem;
	}

	/**
	 * @return the whisper
	 */
	public Whisper getWhisper() {
		return whisper;
	}

	/**
	 * @param whisper
	 *            the whisper to set
	 */
	public void setWhisper(Whisper whisper) {
		this.whisper = whisper;
	}

}
