package molab.main.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.main.java.dao.VisitrecordDAO;

@Service
public class VisitrecordService {

	@Autowired
	private VisitrecordDAO visitrecordDao;	
	
	@SuppressWarnings("unchecked")
	public List findCount(String monid, int start, int finish) {
		return visitrecordDao.findCount(monid, start, finish);
	}
	
	@SuppressWarnings("unchecked")
	public List findFreAndMac(String monid, int start, int finish) {
		return visitrecordDao.findFreAndMac(monid, start, finish);
	}
	public int[] findDtimeAndMac(String monid, int start, int finish) {
		return visitrecordDao.findDtimeAndMac(monid, start, finish);
	}

	@SuppressWarnings("unchecked")
	public int findCountMac(String monid, int inTime) {
		List list = visitrecordDao.findCountMac(monid, inTime);
		if (list != null && list.size() > 0) {
			return (Integer) list.get(0);
		}
		return 0;
	}

}
