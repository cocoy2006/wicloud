package molab.main.java.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import molab.main.java.component.out.Day;
import molab.main.java.component.out.Traffic;
import molab.main.java.dao.TotalinfoDAO;
import molab.main.java.entity.Totalinfo;
import molab.main.java.util.Constants;
import molab.main.java.util.Wicloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalinfoService {

	@Autowired
	private TotalinfoDAO totalinfoDao;

	/**
	 * fill NULL data with 0
	 */
	@SuppressWarnings("unchecked")
	public List<Traffic> loadAll(String monid, String start, String finish) {
		List<Totalinfo> list = totalinfoDao.findAll(monid, start, finish);
		List<Totalinfo> arrayList = new ArrayList<Totalinfo>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, finishDate = null;
		try {
			startDate = format.parse(start);// 字符串转换为Date
			finishDate = format.parse(finish);// 字符串转换为Date
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar temp = Calendar.getInstance();
		Calendar temp2 = Calendar.getInstance();
		Calendar temp3 = Calendar.getInstance();
		long list1 = 0, list2 = 0, list3 = 0;
		temp2.setTime(startDate);
		temp3.setTime(finishDate);
		list2 = temp2.getTimeInMillis();
		list3 = temp3.getTimeInMillis();
		if (list != null && list.size() > 0) {
			if (list.size() == 30) {
				for (int i = 0; i < list.size(); i++) {

					Totalinfo totalinfo = list.get(i);
					arrayList.add(totalinfo);
				}
			} else {
				for (int i = 0; i < list.size(); i++) {

					Totalinfo totalinfo = list.get(i);
					temp.setTime(list.get(i).getTime());
					list1 = temp.getTimeInMillis();
					if (list1 == list2) {
						arrayList.add(totalinfo);
						list2 = 1000 * 86400 + list2;
						continue;
					} else {
						long k = (list1 - list2) / (86400 * 1000);

						for (int j = 0; j < k; j++) {
							Totalinfo newTotalinfo = new Totalinfo();
							newTotalinfo.setTime(new Date(list2 + j * 86400
									* 1000));
							newTotalinfo.setTotal(0);
							newTotalinfo.setComein(0);
							newTotalinfo.setNewer(0);
							newTotalinfo.setOld(0);
							newTotalinfo.setDwelltime(0);
							newTotalinfo.setDwelltimeall(0);
							arrayList.add(newTotalinfo);
						}
						list2 = 1000 * 86400 * (k + 1) + list2;
						Totalinfo totalinfo1 = list.get(i);
						arrayList.add(totalinfo1);
					}
				}
				if (list2 <= list3) {
					long m = (list3 - list2) / (86400 * 1000) + 1;
					for (int j = 0; j < m; j++) {
						Totalinfo newTotalinfo = new Totalinfo();
						newTotalinfo.setTime(new Date(list2 + j * 86400 * 1000));
						newTotalinfo.setTotal(0);
						newTotalinfo.setComein(0);
						newTotalinfo.setNewer(0);
						newTotalinfo.setOld(0);
						newTotalinfo.setDwelltime(0);
						newTotalinfo.setDwelltimeall(0);
						arrayList.add(newTotalinfo);
						// list2=1000*86400+list2;
					}

				}
			}

		} else {
			for (int i = 0; i < 31; i++) {
				Totalinfo newTotalinfo = new Totalinfo();
				newTotalinfo.setTime(startDate);
				newTotalinfo.setTotal(0);
				newTotalinfo.setComein(0);
				newTotalinfo.setNewer(0);
				newTotalinfo.setOld(0);
				newTotalinfo.setDwelltime(0);
				newTotalinfo.setDwelltimeall(0);
				arrayList.add(newTotalinfo);
				startDate = new Date(startDate.getTime() + 24 * 3600 * 1000);
			}
			if (arrayList.size() > 1) {
				arrayList.remove((arrayList.size() - 1));
			}

		}
		int[] weekOfTotal = new int[7];
		int[] weekOfComein = new int[7];
		for (int i = 0; i < arrayList.size(); i++) {
			Totalinfo totalinfo = arrayList.get(i);
			int total = totalinfo.getTotal();
			int comein = totalinfo.getComein();
			Calendar c = Calendar.getInstance();
			c.setTime(totalinfo.getTime());
			// 计算周内客流信息数据
			weekOfTotal[c.get(Calendar.DAY_OF_WEEK) - 1] += total;
			weekOfComein[c.get(Calendar.DAY_OF_WEEK) - 1] += comein;
		}
		List<Traffic> trafficList = new ArrayList<Traffic>();
		for (int i = 0; i < 7; i++) {
			Traffic traffic = new Traffic();
			traffic.setIn(weekOfComein[i] / 30);
			traffic.setAround(weekOfTotal[i] / 30);
			trafficList.add(traffic);
		}
		return trafficList;
	}
	
	/**
	 * fill NULL data with 0
	 */
	@SuppressWarnings("unchecked")
	public List<Traffic> loadAllForLarge(String start, String finish) {
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		List<Totalinfo> list = new ArrayList<Totalinfo>();
		for(String monid : monidList.split(",")) {
			list.addAll(totalinfoDao.findAll(monid, start, finish));
		}
		List<Totalinfo> arrayList = new ArrayList<Totalinfo>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, finishDate = null;
		try {
			startDate = format.parse(start);// 字符串转换为Date
			finishDate = format.parse(finish);// 字符串转换为Date
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar temp = Calendar.getInstance();
		Calendar temp2 = Calendar.getInstance();
		Calendar temp3 = Calendar.getInstance();
		long list1 = 0, list2 = 0, list3 = 0;
		temp2.setTime(startDate);
		temp3.setTime(finishDate);
		list2 = temp2.getTimeInMillis();
		list3 = temp3.getTimeInMillis();
		if (list.size() > 0) {
			if (list.size() == 30) {
				for (int i = 0; i < list.size(); i++) {

					Totalinfo totalinfo = list.get(i);
					arrayList.add(totalinfo);
				}
			} else {
				for (int i = 0; i < list.size(); i++) {

					Totalinfo totalinfo = list.get(i);
					temp.setTime(list.get(i).getTime());
					list1 = temp.getTimeInMillis();
					if (list1 == list2) {
						arrayList.add(totalinfo);
						list2 = 1000 * 86400 + list2;
						continue;
					} else {
						long k = (list1 - list2) / (86400 * 1000);

						for (int j = 0; j < k; j++) {
							Totalinfo newTotalinfo = new Totalinfo();
							newTotalinfo.setTime(new Date(list2 + j * 86400
									* 1000));
							newTotalinfo.setTotal(0);
							newTotalinfo.setComein(0);
							newTotalinfo.setNewer(0);
							newTotalinfo.setOld(0);
							newTotalinfo.setDwelltime(0);
							newTotalinfo.setDwelltimeall(0);
							arrayList.add(newTotalinfo);
						}
						list2 = 1000 * 86400 * (k + 1) + list2;
						Totalinfo totalinfo1 = list.get(i);
						arrayList.add(totalinfo1);
					}
				}
				if (list2 <= list3) {
					long m = (list3 - list2) / (86400 * 1000) + 1;
					for (int j = 0; j < m; j++) {
						Totalinfo newTotalinfo = new Totalinfo();
						newTotalinfo.setTime(new Date(list2 + j * 86400 * 1000));
						newTotalinfo.setTotal(0);
						newTotalinfo.setComein(0);
						newTotalinfo.setNewer(0);
						newTotalinfo.setOld(0);
						newTotalinfo.setDwelltime(0);
						newTotalinfo.setDwelltimeall(0);
						arrayList.add(newTotalinfo);
						// list2=1000*86400+list2;
					}

				}
			}

		} else {
			for (int i = 0; i < 31; i++) {
				Totalinfo newTotalinfo = new Totalinfo();
				newTotalinfo.setTime(startDate);
				newTotalinfo.setTotal(0);
				newTotalinfo.setComein(0);
				newTotalinfo.setNewer(0);
				newTotalinfo.setOld(0);
				newTotalinfo.setDwelltime(0);
				newTotalinfo.setDwelltimeall(0);
				arrayList.add(newTotalinfo);
				startDate = new Date(startDate.getTime() + 24 * 3600 * 1000);
			}
			if (arrayList.size() > 1) {
				arrayList.remove((arrayList.size() - 1));
			}

		}
		int[] weekOfTotal = new int[7];
		int[] weekOfComein = new int[7];
		for (int i = 0; i < arrayList.size(); i++) {
			Totalinfo totalinfo = arrayList.get(i);
			int total = totalinfo.getTotal();
			int comein = totalinfo.getComein();
			Calendar c = Calendar.getInstance();
			c.setTime(totalinfo.getTime());
			// 计算周内客流信息数据
			weekOfTotal[c.get(Calendar.DAY_OF_WEEK) - 1] += total;
			weekOfComein[c.get(Calendar.DAY_OF_WEEK) - 1] += comein;
		}
		List<Traffic> trafficList = new ArrayList<Traffic>();
		for (int i = 0; i < 7; i++) {
			Traffic traffic = new Traffic();
			traffic.setIn(weekOfComein[i]);
			traffic.setAround(weekOfTotal[i]);
			trafficList.add(traffic);
		}
		return trafficList;
	}
	
	public Day comeandAround(String place, String start, String finish) {
		int new_avg = 0, old_avg = 0;
		List list = totalinfoDao.findAvgNewerAndAvgOldWithNewerAndOld(place, start, finish);
		if (list != null && list.size() > 0) {
			Object[] objs = (Object[]) list.get(0);
			new_avg = (int) Wicloud.parseDoubleValue(objs[0]);
			old_avg = (int) Wicloud.parseDoubleValue(objs[1]);
		}
		Day day = new Day();
		day.setNew_avg(new_avg);
		day.setOld_avg(old_avg);
		return day;
	}

}
