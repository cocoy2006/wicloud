package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.RealtimedataIn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * RealtimedataIn entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see molab.main.java.entity.RealtimedataIn
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
@Repository
public class RealtimedataInDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RealtimedataInDAO.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public List<RealtimedataIn> findTraffic(String monid, int start, int finish) {
		log.debug("finding monTime and traffic from RealtimedataIn with monid: " + 
				monid + ", start: " + start + ", finish: " + finish);
		try {
			String queryString = "from RealtimedataIn where monindex.monid=? and id.monTime>=? and id.monTime<=?";
			return hibernateTemplate.find(queryString, monid, start, finish);
		} catch (RuntimeException re) {
			log.error("find monTime and traffic failed", re);
			throw re;
		}
	}

	public List<RealtimedataIn> findTraffic(String monid, int monTime) {
		log.debug("finding traffic from RealtimedataIn with monid: " + 
				monid + ", monTime: " + monTime);
		try {
			String queryString = "from RealtimedataIn where monindex.monid=? order by id.monTime desc limit 1";
			return hibernateTemplate.find(queryString, monid);
		} catch (RuntimeException re) {
			log.error("find traffic failed", re);
			throw re;
		}
	}
	
}