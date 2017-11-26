package molab.main.java.service;

import java.util.Date;
import java.util.List;

import molab.main.java.dao.RealtimedataInDAO;
import molab.main.java.dao.TotalinfoDAO;
import molab.main.java.entity.RealtimedataIn;
import molab.main.java.util.Constants;
import molab.main.java.util.Wicloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealtimedataInService {

	@Autowired
	private RealtimedataInDAO dao;

	@Autowired
	private TotalinfoDAO td;

	/**
	 * @deprecated */
	public String realtimedataIn() {
		int start = (int) (new Date().getTime() / 1000);
		int finish = ((int) (new Date().getTime() / 300000)) * 300;
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		StringBuffer realdata = new StringBuffer();
		for (String monid : monidList.split(",")) {
			List<RealtimedataIn> list = dao.findTraffic(monid, start, finish);
			if (list != null && list.size() > 0) {
				for (RealtimedataIn realtimedataIn : list) {
					realdata.append("[")
							.append(realtimedataIn.getId().getMonTime())
							.append("000,")
							.append(realtimedataIn.getId().getTraffic())
							.append("],");
				}
			}
		}
		if (realdata.length() > 1) {
			realdata.delete(realdata.length() - 1, realdata.length());
		} else {
			realdata.append("[")
					.append(finish)
					.append("000,0]");
		}
		return "{\"data\":[" + realdata.toString() + "]}";
	}

	/**
	 * @deprecated */
	public String realtimeData() {
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		int time = (int) (new Date().getTime() / 1000);
		// get realtime data from RealtimedataIn for each monid
		int real = 0;
		for (String monid : monidList.split(",")) {
			List<RealtimedataIn> list = dao.findTraffic(monid, time);
			if (list != null && list.size() > 0) {
				RealtimedataIn realtimedataIn = list.get(0);
				real += realtimedataIn.getId().getTraffic();
			}
		}
		return "{\"real\":" + real + "}";
	}

}
