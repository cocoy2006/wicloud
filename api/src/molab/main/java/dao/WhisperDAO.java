package molab.main.java.dao;

import java.io.Serializable;
import java.util.List;

import molab.main.java.entity.Whisper;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Whisper entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.Whisper
 * @author MyEclipse Persistence Tools
 */
@Repository
public class WhisperDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(WhisperDAO.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	// property constants
	public static final String MONID = "monid";
	public static final String CODE = "code";

	public void save(Whisper transientInstance) {
		log.debug("saving Whisper instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Whisper persistentInstance) {
		log.debug("deleting Whisper instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Whisper findById(java.lang.Integer id) {
		log.debug("getting Whisper instance with id: " + id);
		try {
			Whisper instance = (Whisper) hibernateTemplate.get(
					"molab.main.java.entity.Whisper", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Whisper> findByExample(Whisper instance) {
		log.debug("finding Whisper instance by example");
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

	public List<Whisper> findByProperty(String propertyName, Object value) {
		log.debug("finding Whisper instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Whisper as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Whisper findByMonid(Object monid) {
		List<Whisper> list = findByProperty(MONID, monid);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public Whisper findByCode(Object code) {
		List<Whisper> list = findByProperty(CODE, code);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<Whisper> findAll() {
		log.debug("finding all Whisper instances");
		try {
			return hibernateTemplate.loadAll(Whisper.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Whisper merge(Whisper detachedInstance) {
		log.debug("merging Whisper instance");
		try {
			Whisper result = (Whisper) hibernateTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Whisper instance) {
		log.debug("attaching dirty Whisper instance");
		try {
			hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Whisper instance) {
		log.debug("attaching clean Whisper instance");
		try {
			hibernateTemplate.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}