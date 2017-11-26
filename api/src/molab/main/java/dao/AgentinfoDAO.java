package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.Agentinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Agentinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.Agentinfo
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
@Repository
public class AgentinfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AgentinfoDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String ID = "id";
	public static final String PASSWD = "passwd";
	public static final String USER_TYPE = "userType";
	public static final String SHOPADD = "shopadd";
	public static final String SHOP_NAME = "shopName";
	public static final String CONTRACT = "contract";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void save(Agentinfo transientInstance) {
		log.debug("saving Agentinfo instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(Agentinfo transientInstance) {
		log.debug("saving Agentinfo instance");
		try {
			hibernateTemplate.saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Agentinfo persistentInstance) {
		log.debug("deleting Agentinfo instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Agentinfo findById(java.lang.String id) {
		log.debug("getting Agentinfo instance with id: " + id);
		try {
			Agentinfo instance = (Agentinfo) hibernateTemplate.get(
					"molab.main.java.entity.Agentinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Agentinfo> findByExample(Agentinfo instance) {
		log.debug("finding Agentinfo instance by example");
		try {
			List<Agentinfo> results = (List<Agentinfo>) hibernateTemplate
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Agentinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Agentinfo as model where model."
					+ propertyName + "= ?";
			return hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findById(Object id) {
		return findByProperty(ID, id);
	}

	public List findByPasswd(Object passwd) {
		return findByProperty(PASSWD, passwd);
	}

	public List findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List findByShopadd(Object shopadd) {
		return findByProperty(SHOPADD, shopadd);
	}

	public List findByShopName(Object shopName) {
		return findByProperty(SHOP_NAME, shopName);
	}

	public List findByContract(Object contract) {
		return findByProperty(CONTRACT, contract);
	}

	public List findAll() {
		log.debug("finding all Agentinfo instances");
		try {
			return hibernateTemplate.loadAll(Agentinfo.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
}