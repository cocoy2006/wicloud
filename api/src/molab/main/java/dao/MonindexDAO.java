package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.Monindex;
import molab.main.java.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Monindex entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.Monindex
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
@Repository
public class MonindexDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MonindexDAO.class);
	// property constants
	public static final String MON_MODLE = "monModle";
	public static final String MON_ADD = "monAdd";
	public static final String WALL_TYPE = "wallType";
	public static final String WALL_EXP = "wallExp";
	public static final String WALL_DIS = "wallDis";
	public static final String MONNAME = "monname";
	public static final String TOTAL = "total";
	public static final String GROUPID = "groupid";

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void delete(Monindex persistentInstance) {
		log.debug("deleting Monindex instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List<Monindex> findAll() {
		log.debug("finding all Monindex instances");
		try {
			return hibernateTemplate.loadAll(Monindex.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Monindex> findAll(String searchString) {
		try {
//			String sql = "from Monindex where userName not in (" 
//					+ "'" + Constants.ADMIN_USERNAME + "',"
//					+ "'" + Constants.DISCARD_USERNAME + "'"
//					+ ") and (monid %s or monAdd %s or monname %s or wallDis %s)";
			String sql = "from Monindex where userName!='" 
					+ Constants.DISCARD_USERNAME
					+ "' and (monid %s or monAdd %s or monname %s or wallDis %s)";
			String like = "like '%" + searchString + "%'";
			String queryString = String.format(sql, 
					like, like, like, like);
			return hibernateTemplate.find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Monindex> findAll(String username, String searchString) {
		try {
			String sql = "from Monindex where userName='" 
					+ username
					+ "' and (monid %s or monAdd %s or monname %s or wallDis %s)";
			String like = "like '%" + searchString + "%'";
			String queryString = String.format(sql, 
					like, like, like, like);
			return hibernateTemplate.find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Monindex findById(java.lang.String id) {
		log.debug("getting Monindex instance with id: " + id);
		try {
			Monindex instance = (Monindex) hibernateTemplate.get(
					"molab.main.java.entity.Monindex", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Monindex> findByProperty(String propertyName, Object value) {
		log.debug("finding Monindex instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Monindex as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Monindex> findByUsername(Object value) {
		log.debug("finding all Monindex instances with username: " + value);
		try {
			String queryString = "from Monindex where userName=?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Monindex> findUnassigned() {
		log.debug("finding all Monindex instances without username huimei and discard ");
		try {
			String queryString = "from Monindex where userName not in (?,?)";
			return hibernateTemplate.find(queryString, Constants.ADMIN_USERNAME, Constants.DISCARD_USERNAME);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void save(Monindex transientInstance) {
		log.debug("saving Monindex instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(Monindex transientInstance) {
		log.debug("saving Monindex instance");
		try {
			hibernateTemplate.saveOrUpdate(transientInstance);
			log.debug("saveOrUpdate successful");
		} catch (RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			throw re;
		}
	}

}