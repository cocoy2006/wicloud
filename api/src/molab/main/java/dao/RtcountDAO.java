package molab.main.java.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import molab.main.java.entity.Rtcount;

/**
 * A data access object (DAO) providing persistence and search support for
 * Rtcount entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.Rtcount
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
@Repository
public class RtcountDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RtcountDAO.class);
	// property constants
	public static final String INCOUNT = "incount";
	public static final String ALLCOUNT = "allcount";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void save(Rtcount transientInstance) {
		log.debug("saving Rtcount instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rtcount persistentInstance) {
		log.debug("deleting Rtcount instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rtcount findById(molab.main.java.entity.RtcountId id) {
		log.debug("getting Rtcount instance with id: " + id);
		try {
			Rtcount instance = (Rtcount) hibernateTemplate.get(
					"molab.main.java.entity.Rtcount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Rtcount instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rtcount as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIncount(Object incount) {
		return findByProperty(INCOUNT, incount);
	}

	public List findByAllcount(Object allcount) {
		return findByProperty(ALLCOUNT, allcount);
	}

	public List findAll() {
		log.debug("finding all Rtcount instances");
		try {
			return hibernateTemplate.loadAll(Rtcount.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Rtcount> findLastOne(String monid, String start, String finish) {
		log.debug("finding incount, allcount from Rtcount with monid: " +  monid);
		try {
			String queryString = "from Rtcount where id.monid=? and id.time between ? and ? order by time desc limit 1";
			return hibernateTemplate.find(queryString, monid, start, finish);
		} catch (RuntimeException re) {
			log.error("findIncountAndAllcount failed", re);
			throw re;
		}
	}
	
	public List findAllForLarge(String[] monidList, String start, String finish) {
		log.debug("finding incount, allcount from Rtcount with monid: " +  monidList);
		try {
			String queryString = "select id.time, sum(incount) from Rtcount where id.monid in (:monidListParam) and id.time between :startParam and :finishParam group by id.time";
			String[] params = {"monidListParam", "startParam", "finishParam"};
			Object[] values = {monidList, start, finish};
			return hibernateTemplate.findByNamedParam(queryString, params, values);
		} catch (RuntimeException re) {
			log.error("findAllForLarge failed", re);
			throw re;
		}
	}

}