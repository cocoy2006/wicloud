package molab.main.java.entity;
import java.util.Date;

/**
 * HourscountId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class HourscountallId implements java.io.Serializable{

		// Fields

		private String monid;
		private Date mondate;

		// Constructors

		/** default constructor */
		public HourscountallId() {
		}

		/** full constructor */
		public HourscountallId(String monid, Date date) {
			this.monid = monid;
			this.mondate = date;
		}

		// Property accessors

		public String getMonid() {
			return this.monid;
		}

		public void setMonid(String monid) {
			this.monid = monid;
		}

		public Date getMondate() {
			return this.mondate;
		}

		public void setMondate(Date time) {
			this.mondate = time;
		}

		public boolean equals(Object other) {
			if ((this == other))
				return true;
			if ((other == null))
				return false;
			if (!(other instanceof HourscountallId))
				return false;
			HourscountallId castOther = (HourscountallId) other;

			return ((this.getMonid() == castOther.getMonid()) || (this.getMonid() != null
					&& castOther.getMonid() != null && this.getMonid().equals(
					castOther.getMonid())))
					&& ((this.getMondate() == castOther.getMondate()) || (this.getMondate() != null
							&& castOther.getMondate() != null && this.getMondate()
							.equals(castOther.getMondate())));
		}

		public int hashCode() {
			int result = 17;

			result = 37 * result
					+ (getMonid() == null ? 0 : this.getMonid().hashCode());
			result = 37 * result
					+ (getMondate() == null ? 0 : this.getMondate().hashCode());
			return result;
		}

	}
