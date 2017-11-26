package molab.main.java.dao;

import java.sql.Date;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import molab.main.java.entity.Totalinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Totalinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.Totalinfo
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
@Repository
public class TotalinfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TotalinfoDAO.class);
	
	// property constants
	public static final String TOTAL = "total";
	public static final String TOTAL_SHORT = "totalShort";
	public static final String TOTAL_CI = "totalCi";
	public static final String COMEIN = "comein";
	public static final String HEAT_MAX = "heatMax";
	public static final String HEAT_AVG = "heatAvg";
	public static final String NEWER = "newer";
	public static final String OLD = "old";
	public static final String DWELLTIME = "dwelltime";
	public static final String DWELLTIMEALL = "dwelltimeall";
	public static final String MONID = "monid";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void save(Totalinfo transientInstance) {
		log.debug("saving Totalinfo instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Totalinfo persistentInstance) {
		log.debug("deleting Totalinfo instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Totalinfo findById(java.lang.Integer id) {
		log.debug("getting Totalinfo instance with id: " + id);
		try {
			Totalinfo instance = (Totalinfo) hibernateTemplate.get(
					"molab.main.java.entity.Totalinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Totalinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Totalinfo as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTotal(Object total) {
		return findByProperty(TOTAL, total);
	}

	public List findByTotalShort(Object totalShort) {
		return findByProperty(TOTAL_SHORT, totalShort);
	}

	public List findByTotalCi(Object totalCi) {
		return findByProperty(TOTAL_CI, totalCi);
	}

	public List findByComein(Object comein) {
		return findByProperty(COMEIN, comein);
	}

	public List findByHeatMax(Object heatMax) {
		return findByProperty(HEAT_MAX, heatMax);
	}

	public List findByHeatAvg(Object heatAvg) {
		return findByProperty(HEAT_AVG, heatAvg);
	}

	public List findByNewer(Object newer) {
		return findByProperty(NEWER, newer);
	}

	public List findByOld(Object old) {
		return findByProperty(OLD, old);
	}

	public List findByDwelltime(Object dwelltime) {
		return findByProperty(DWELLTIME, dwelltime);
	}

	public List findByMonid(Object monid) {
		return findByProperty(MONID, monid);
	}

	public List findAll() {
		log.debug("finding all Totalinfo instances");
		try {
			@SuppressWarnings("unused")
			String queryString = "from Totalinfo";
			return hibernateTemplate.loadAll(Totalinfo.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAll(String monid, String start, String finish) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Totalinfo.class);
			criteria.add(Restrictions.eq("monid", monid))
				.add(Restrictions.between("time", Date.valueOf(start), Date.valueOf(finish)));
			List<Totalinfo> list=hibernateTemplate.findByCriteria(criteria);
			for(int i=0;i<(list.size()-1);i++){
				if(list.get(i).getTime().after(list.get(i+1).getTime())||list.get(i).getTime().equals(list.get(i+1).getTime())){
					list.remove(i+1);
					i=i-1;
				}
			}
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Object[]> findAvgNewerAndAvgOldWithNewerAndOld(String monid, String start, String finish) {
		log.debug("finding avg(newer), avg(old) from Totalinfo with monid: " + 
				monid + ", start: " + start + ", finish: " + finish);
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Totalinfo.class);
			criteria.add(Restrictions.eq("monid", monid))
				.add(Restrictions.between("time", Date.valueOf(start), Date.valueOf(finish)))
				.add(Restrictions.gt("newer", 0)).add(Restrictions.gt("old", 0));
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.avg("newer")).add(Projections.avg("old"));
			criteria.setProjection(projection);
			return hibernateTemplate.findByCriteria(criteria);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
}