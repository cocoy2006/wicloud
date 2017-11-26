package molab.main.java.entity;

import java.util.Date;

/**
 * Totalinfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Totalinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date time;
	private Integer total;
	private Integer totalShort;
	private Integer totalCi;
	private Integer comein;
	private Integer heatMax;
	private Integer heatAvg;
	private Integer newer;
	private Integer old;
	private Integer dwelltime;
	private Integer dwelltimeall;
	private String monid;

	// Constructors

	/** default constructor */
	public Totalinfo() {
	}

	/** minimal constructor */
	public Totalinfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Totalinfo(Integer id, Date time, Integer total, Integer totalShort,
			Integer totalCi, Integer comein, Integer heatMax, Integer heatAvg,
			Integer newer, Integer old, Integer dwelltime, String monid) {
		this.id = id;
		this.time = time;
		this.total = total;
		this.totalShort = totalShort;
		this.totalCi = totalCi;
		this.comein = comein;
		this.heatMax = heatMax;
		this.heatAvg = heatAvg;
		this.newer = newer;
		this.old = old;
		this.dwelltime = dwelltime;
		this.monid = monid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalShort() {
		return this.totalShort;
	}

	public void setTotalShort(Integer totalShort) {
		this.totalShort = totalShort;
	}

	public Integer getTotalCi() {
		return this.totalCi;
	}

	public void setTotalCi(Integer totalCi) {
		this.totalCi = totalCi;
	}

	public Integer getComein() {
		return this.comein;
	}

	public void setComein(Integer comein) {
		this.comein = comein;
	}

	public Integer getHeatMax() {
		return this.heatMax;
	}

	public void setHeatMax(Integer heatMax) {
		this.heatMax = heatMax;
	}

	public Integer getHeatAvg() {
		return this.heatAvg;
	}

	public void setHeatAvg(Integer heatAvg) {
		this.heatAvg = heatAvg;
	}

	public Integer getNewer() {
		return this.newer;
	}

	public void setNewer(Integer newer) {
		this.newer = newer;
	}

	public Integer getOld() {
		return this.old;
	}

	public void setOld(Integer old) {
		this.old = old;
	}

	public Integer getDwelltime() {
		return this.dwelltime;
	}

	public void setDwelltime(Integer dwelltime) {
		this.dwelltime = dwelltime;
	}

	/**
	 * @return the dwelltimeall
	 */
	public Integer getDwelltimeall() {
		return dwelltimeall;
	}

	/**
	 * @param dwelltimeall
	 *            the dwelltimeall to set
	 */
	public void setDwelltimeall(Integer dwelltimeall) {
		this.dwelltimeall = dwelltimeall;
	}

	public String getMonid() {
		return this.monid;
	}

	public void setMonid(String monid) {
		this.monid = monid;
	}

}