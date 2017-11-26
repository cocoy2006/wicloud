package molab.main.java.dao;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import molab.main.java.entity.Rssinfo;


/**
 * A data access object (DAO) providing persistence and search support for
 * Rssinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.Rssinfo
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
@Repository
public class RssinfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RssinfoDAO.class);

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	// property constants
	public static final String MONID = "monid";
	
	public void save(Rssinfo transientInstance) {
		log.debug("saving Rssinfo instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(Rssinfo transientInstance) {
		log.debug("saving Rssinfo instance");
		try {
			hibernateTemplate.saveOrUpdate(transientInstance);
			log.debug("saveOrUpdate successful");
			//return Status.SUCCESS;
		} catch (RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			throw re;
			//return Status.ERROR;
		}
	}

	public void delete(Rssinfo persistentInstance) {
		log.debug("deleting Rssinfo instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public Rssinfo findByMonid(Object monid) {
		List<Rssinfo> list = findByProperty(MONID, monid);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<Rssinfo> findByProperty(String propertyName, Object value) {
		log.debug("finding Rssinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rssinfo as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Rssinfo> findAll() {
		log.debug("finding all Rssinfo instances");
		try {
			@SuppressWarnings("unused")
			String queryString = "from Rssinfo";
			return hibernateTemplate.loadAll(Rssinfo.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}