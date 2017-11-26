package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.MonSubItem;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonSubItem entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.MonSubItem
 * @author MyEclipse Persistence Tools
 */
@Repository
public class MonSubItemDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MonSubItemDAO.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	// property constants
	public static final String MONID = "monid";
	public static final String SUB_ITEM_ID = "subItemId";

	public void save(MonSubItem transientInstance) {
		log.debug("saving MonSubItem instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonSubItem persistentInstance) {
		log.debug("deleting MonSubItem instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonSubItem findById(java.lang.Integer id) {
		log.debug("getting MonSubItem instance with id: " + id);
		try {
			MonSubItem instance = (MonSubItem) hibernateTemplate.get(
					"molab.main.java.entity.MonSubItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MonSubItem> findByExample(MonSubItem instance) {
		log.debug("finding MonSubItem instance by example");
		try {
			List results = hibernateTemplate.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<MonSubItem> findByProperty(String propertyName, Object value) {
		log.debug("finding MonSubItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MonSubItem as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
//	public MonSubItem find(Object monid) {
//		List<MonSubItem> msiList = findByProperty(MONID, monid);
//		if(msiList != null && msiList.size() > 0) {
//			return msiList.get(0);
//		}
//		return null;
//	}

	public MonSubItem findByMonid(Object monid) {
		List<MonSubItem> list = findByProperty(MONID, monid);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<MonSubItem> findBySubItemId(Object subItemId) {
		return findByProperty(SUB_ITEM_ID, subItemId);
	}

	public List<MonSubItem> findAll() {
		log.debug("finding all MonSubItem instances");
		try {
			return hibernateTemplate.loadAll(MonSubItem.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonSubItem merge(MonSubItem detachedInstance) {
		log.debug("merging MonSubItem instance");
		try {
			MonSubItem result = (MonSubItem) hibernateTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonSubItem instance) {
		log.debug("attaching dirty MonSubItem instance");
		try {
			hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonSubItem instance) {
		log.debug("attaching clean MonSubItem instance");
		try {
			hibernateTemplate.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}