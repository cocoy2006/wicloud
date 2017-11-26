package molab.main.java.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import molab.main.java.component.out.Day;
import molab.main.java.component.out.Traffic;
import molab.main.java.dao.HourscountDAO;
import molab.main.java.dao.HourscountallDAO;
import molab.main.java.dao.RtcountDAO;
import molab.main.java.dao.TotalinfoDAO;
import molab.main.java.dao.VisitrecordDAO;
import molab.main.java.entity.Rtcount;
import molab.main.java.util.Constants;
import molab.main.java.util.DateUtil;
import molab.main.java.util.MathUtil;
import molab.main.java.util.Wicloud;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

	@Autowired
	private HourscountDAO hd;
	
	@Autowired
	private HourscountallDAO had;
	
	@Autowired
	private RtcountDAO rd;
	
	@Autowired
	private TotalinfoDAO td;

	@Autowired
	private VisitrecordDAO vd;

	/**
	 * 入店顾客数
	 */
	public Day dayTotal(String monid) throws ClientProtocolException, IOException {
		int total = 0;
		List<Rtcount> list = rd.findLastOne(monid, DateUtil.dateOnStart(0), DateUtil.dateOnEnd(0));
		if(list != null && list.size() > 0) {
			Rtcount rtcount = list.get(0);
			total = rtcount.getIncount();
		}
		Day day = new Day();
		day.setTotal(total);
		return day;
	}

	/**
	 * 入店率
	 */
	public Day dayRate(String monid) throws ClientProtocolException, IOException {
		int comein = 0, total = 0;
		List<Rtcount> list = rd.findLastOne(monid, DateUtil.dateOnStart(0), DateUtil.dateOnEnd(0));
		if(list != null && list.size() > 0) {
			Rtcount rtcount = list.get(0);
			comein = rtcount.getIncount();
			total = rtcount.getAllcount();
		}
		Day day = new Day();
		day.setRate(MathUtil.divide(comein, total));
		return day;
	}

	/**
	 * 平均停留时间
	 */
	public Day dayStay(String monid) throws ClientProtocolException, IOException {
		int stay = 0;
		List list = vd.findCountMacAndAvgDwelltime(monid, DateUtil.dayInMillis(0), DateUtil.dayInMillis(1) - 1, 20);
		if (list != null && list.size() > 0) {
			Object[] objs = (Object[]) list.get(0);
			stay = (int) Wicloud.parseDoubleValue(objs[1]);
		}
		Day day = new Day();
		day.setStay(MathUtil.divide(stay, 60, 1));
		return day;
	}
	
	/**
	 * 前一天的每小时的人流量曲线图
	 */
	public List<Traffic> dayH(String monid) {
		String yesterday = DateUtil.day(-1);
		return traffic(monid, yesterday, yesterday);
	}

	/**
	 * 近7日入店顾客数
	 */
	public List<Day> weekTotal(String monid) throws ClientProtocolException, IOException {
		List<Day> dayList = new ArrayList<Day>();
		for (int i = -7; i <= -1; i++) {
			int total = 0;
			List<Rtcount> list = rd.findLastOne(monid, DateUtil.dateOnStart(i), DateUtil.dateOnEnd(i));
			if(list != null && list.size() > 0) {
				Rtcount rtcount = list.get(0);
				total = rtcount.getIncount();
			}
			Day day = new Day();
			day.setTotal(total);
			dayList.add(day);
		}
		return dayList;
	}

	/**
	 * 近7日入店率
	 */
	public List<Day> weekRate(String monid) throws ClientProtocolException, IOException {
		List<Day> dayList = new ArrayList<Day>();
		for (int i = -7; i <= -1; i++) {
			int comein = 0; int total = 0;
			List<Rtcount> list = rd.findLastOne(monid, DateUtil.dateOnStart(i), DateUtil.dateOnEnd(i));
			if(list != null && list.size() > 0) {
				Rtcount rtcount = list.get(0);
				comein = rtcount.getIncount();
				total = rtcount.getAllcount();
			}
			Day day = new Day();
			day.setRate(MathUtil.divide(comein, total));
			dayList.add(day);
		}
		return dayList;
	}

	/**
	 * 近7日平均停留时间
	 */
	public List<Day> weekStay(String monid) throws ClientProtocolException, IOException {
		List<Day> dayList = new ArrayList<Day>();
		for (int i = -7; i <= -1; i++) {
			int stay = 0;
			List list = vd.findCountMacAndAvgDwelltime(monid, DateUtil.dayInMillis(i), DateUtil.dayInMillis(i + 1) - 1, 20);
			if (list != null && list.size() > 0) {
				Object[] objs = (Object[]) list.get(0);
				stay = (int) Wicloud.parseDoubleValue(objs[1]);
			}
			Day day = new Day();
			day.setStay(MathUtil.divide(stay, 60, 1));
			dayList.add(day);
		}
		return dayList;
	}
	
	/**
	 * 近7日平均停留时间
	 */
	public List<Day> weekStayForLarge() {
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		List<Day> dayList = new ArrayList<Day>();
		for (int i = -7; i <= -1; i++) {
			double stay = 0;
			List list = vd.findCountMacAndAvgDwelltimeForLarge(monidList.split(","), DateUtil.dayInMillis(i), DateUtil.dayInMillis(i + 1) - 1, 20);
			if (list != null && list.size() > 0) {
				Object[] objs = (Object[]) list.get(0);
				stay = Wicloud.parseDoubleValue(objs[1]);
			}
			Day day = new Day();
			day.setStay(MathUtil.rescale(stay, 1));
			dayList.add(day);
		}
		return dayList;
	}

	/**
	 * 近30日入店顾客数
	 */
	public List<Day> monthTotal(String monid) throws ClientProtocolException, IOException {
		List<Day> dayList = new ArrayList<Day>();
		for (int i = -30; i <= -1; i++) {
			int total = 0;
			List<Rtcount> list = rd.findLastOne(monid, DateUtil.dateOnStart(i), DateUtil.dateOnEnd(i));
			if(list != null && list.size() > 0) {
				Rtcount rtcount = list.get(0);
				total = rtcount.getIncount();
			}
			Day day = new Day();
			day.setTotal(total);
			dayList.add(day);
		}
		return dayList;
	}

	/**
	 * 近30日新老顾客占比
	 */
	public Day monthPercent(String monid) throws ClientProtocolException, IOException {
		int new_avg = 0, old_avg = 0;
		for (int i = -30; i <= -1; i++) {
			List list = td.findAvgNewerAndAvgOldWithNewerAndOld(monid, DateUtil.day(i), DateUtil.day(i));
			if (list != null && list.size() > 0) {
				Object[] objs = (Object[]) list.get(0);
				new_avg += (int) Wicloud.parseDoubleValue(objs[0]);
				old_avg += (int) Wicloud.parseDoubleValue(objs[1]);
			}
		}
		Day day = new Day();
		day.setPercent(MathUtil.divide(old_avg, new_avg + old_avg));
		return day;
	}

	/**
	 * 近30日日内客流信息
	 */
	public List<Traffic> monthH(String monid) {
		return traffic(monid, DateUtil.day(-30), DateUtil.day(-1));
	}
	
	public List<Traffic> traffic(String monid, String start, String finish) {
		Object[] inList = hd.findAll_(monid, start, finish);
		Object[] aroundList = had.findAll_(monid, start, finish);
		List<Traffic> trafficList = new ArrayList<Traffic>();
		for (int i = 0; i < 24; i++) {
			Traffic traffic = new Traffic();
			if (inList[i] != null) {
				traffic.setIn(new Long((long) inList[i]).intValue() / 30);
			}
			if (aroundList[i] != null) {
				traffic.setAround(new Long((long) aroundList[i]).intValue() / 30);
			}
			trafficList.add(traffic);
		}
		return trafficList;
	}
	
	public List<Traffic> trafficForLarge(String start, String finish) {
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		List<Object> inList = new ArrayList<Object>();
		List<Object> aroundList = new ArrayList<Object>();
		for(String monid : monidList.split(",")) {
			Collections.addAll(inList, hd.findAll_(monid, start, finish));
			Collections.addAll(aroundList, had.findAll_(monid, start, finish));
		}
		List<Traffic> trafficList = new ArrayList<Traffic>();
		for (int i = 0; i < 24; i++) {
			Traffic traffic = new Traffic();
			if (inList.get(i) != null) {
				traffic.setIn(new Long((long) inList.get(i)).intValue());
			}
			if (aroundList.get(i) != null) {
				traffic.setAround(new Long((long) aroundList.get(i)).intValue());
			}
			trafficList.add(traffic);
		}
		return trafficList;
	}
	
	public List<Day> heatmapForLarge() throws ClientProtocolException, IOException {
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		List<Day> dayList = new ArrayList<Day>();
		for(String monid : monidList.split(",")) {
			Day day = dayTotal(monid);
			dayList.add(day);
		}
		return dayList;
	}
	
}
