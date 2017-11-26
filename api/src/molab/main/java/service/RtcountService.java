package molab.main.java.service;

import java.text.ParseException;
import java.util.List;

import molab.main.java.dao.RtcountDAO;
import molab.main.java.entity.Rtcount;
import molab.main.java.util.Constants;
import molab.main.java.util.DateUtil;
import molab.main.java.util.Wicloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RtcountService {

	@Autowired
	private RtcountDAO dao;

	public String realtimeData(String start, String finish) throws ParseException {
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		List list = dao.findAllForLarge(monidList.split(","), start, finish);
		if (list != null && list.size() > 0) {
			StringBuffer data = new StringBuffer();
			for(Object obj : list) {
				Object[] objs = (Object[]) obj;
				data.append("[")
					.append(DateUtil.parse(objs[0].toString()))
					.append(",")
					.append(Long.parseLong(objs[1].toString()))
					.append("],");
			}
			if (data.length() > 1) {
				data.delete(data.length() - 1, data.length());
			} else {
				data.append("[")
					.append(DateUtil.parse(finish))
					.append(",0]");
			}
			return "{\"data\":[" + data.toString() + "]}";
		}
		return null;
	}
	
}
