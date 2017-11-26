package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.SubItem;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * SubItem entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.SubItem
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SubItemDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SubItemDAO.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	// property constants
	public static final String ITEM_ID = "itemId";
	public static final String NAME = "name";

	public void save(SubItem transientInstance) {
		log.debug("saving SubItem instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SubItem persistentInstance) {
		log.debug("deleting SubItem instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SubItem findById(java.lang.Integer id) {
		log.debug("getting SubItem instance with id: " + id);
		try {
			SubItem instance = (SubItem) hibernateTemplate.get(
					"molab.main.java.entity.SubItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SubItem instance) {
		log.debug("finding SubItem instance by example");
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

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SubItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SubItem as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SubItem> findByItemId(Object itemId) {
		return findByProperty(ITEM_ID, itemId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<SubItem> findAll() {
		log.debug("finding all SubItem instances");
		try {
			return hibernateTemplate.loadAll(SubItem.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SubItem merge(SubItem detachedInstance) {
		log.debug("merging SubItem instance");
		try {
			SubItem result = (SubItem) hibernateTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SubItem instance) {
		log.debug("attaching dirty SubItem instance");
		try {
			hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SubItem instance) {
		log.debug("attaching clean SubItem instance");
		try {
			hibernateTemplate.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}