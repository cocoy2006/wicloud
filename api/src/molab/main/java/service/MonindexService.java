package molab.main.java.service;

import java.util.List;

import molab.main.java.dao.MonindexDAO;
import molab.main.java.entity.Monindex;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonindexService {
	
	@Autowired
	private MonindexDAO dao;
	
	public int assign(String monid, String username) {
		try {
			Monindex mon = dao.findById(monid);
			if(mon != null) {
				mon.setUserName(username);
				dao.update(mon);
				return Status.Common.SUCCESS.getInt();
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	public int assignMon(String username, String ids) {
		try {
			String[] idArray = ids.split(",");
			for(String id : idArray) {
				Monindex mon = dao.findById(id);
				if(mon != null) {
					mon.setUserName(username);
					dao.update(mon);
				}
			}
			return Status.Common.SUCCESS.getInt();
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	public List<Monindex> findByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	public String findName(String monid) {
		Monindex mon = dao.findById(monid);
		if(mon != null) {
			return mon.getMonname();
		}
		return null;
	}
	
}
