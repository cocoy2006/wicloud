package molab.main.java.dao;

import java.sql.Date;

import molab.main.java.entity.Hourscount;

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

@Repository
public class HourscountDAO extends BaseHibernateDAO<Hourscount> {
	private static final Logger log = LoggerFactory
			.getLogger(HourscountDAO.class);
	// property constants

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public Object[] findAll_(String monid, String start, String finish) {
		log.debug("finding record from Hourscount with monid: " + monid
				+ ", start inTime: " + start + ", finish inTime: " + finish);
		try {
			Date startt = Date.valueOf(start);
			Date finisht = Date.valueOf(finish);
			String queryString = "SELECT sum(h1), sum(h2), sum(h3),sum(h4),sum(h5),sum(h6),sum(h7),sum(h8),"
					+ "sum(h9),sum(h10),sum(h11),sum(h12),sum(h13),sum(h14),sum(h15),sum(h16),"
					+ "sum(h17),sum(h18),sum(h19),sum(h20),sum(h21),sum(h22),sum(h23),sum(h24) "
					+ "FROM Hourscount where monid=? and mondate between ? and ?";
			return (Object[]) hibernateTemplate.find(queryString, monid, startt, finisht).get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}