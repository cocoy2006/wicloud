package molab.main.java.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import molab.main.java.component.Brand;
import molab.main.java.dao.BranddisDAO;
import molab.main.java.util.Constants;
import molab.main.java.util.Wicloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranddisService {

	@Autowired
	private BranddisDAO dao;

	public List<Brand> avg_(String place, String start, String finish) {
		List list = dao.findAvgBranddis(place, start, finish);
		if (list != null && list.size() > 0) {
			Object[] objs = (Object[]) list.get(0);
			List<Map.Entry<String, Integer>> mappingList = null;
			Map<String, Integer> map = new TreeMap<String, Integer>();
			map.put("苹果", (int) Wicloud.parseDoubleValue(objs[1])); // Apple
			map.put("三星", (int) Wicloud.parseDoubleValue(objs[2])); // Samsung
			map.put("诺基亚", (int) Wicloud.parseDoubleValue(objs[3])); // Nokia
//			map.put("索尼", (int) Wicloud.parseDoubleValue(objs[4])); // Sony
			map.put("中兴", (int) Wicloud.parseDoubleValue(objs[5])); // ZTE
			map.put("华为", (int) Wicloud.parseDoubleValue(objs[6])); // HUAWEI
//			map.put("华硕", (int) Wicloud.parseDoubleValue(objs[7])); // ASUS
//			map.put("英特尔", (int) Wicloud.parseDoubleValue(objs[8])); // Intel
//			map.put("鸿海", (int) Wicloud.parseDoubleValue(objs[9])); // honhai
			map.put("HTC", (int) Wicloud.parseDoubleValue(objs[10]));
			map.put("小米", (int) Wicloud.parseDoubleValue(objs[11])); // Xiaomi
			map.put("OPPO", (int) Wicloud.parseDoubleValue(objs[12]));
//			map.put("LG", (int) Wicloud.parseDoubleValue(objs[13]));
			map.put("联想", (int) Wicloud.parseDoubleValue(objs[14])); // Lenovo
			map.put("魅族", (int) Wicloud.parseDoubleValue(objs[15])); // Meizu
//			map.put("酷派", (int) Wicloud.parseDoubleValue(objs[16])); // Coolpad无对应的OUI
//			map.put("步步高", (int) Wicloud.parseDoubleValue(objs[17])); // bbk
//			map.put("TP_LINK", (int) Wicloud.parseDoubleValue(objs[18]));
			map.put("金立", (int) Wicloud.parseDoubleValue(objs[19])); // gionee
//			map.put("murata", (int) Wicloud.parseDoubleValue(objs[20]));
//			map.put("inpro", (int) Wicloud.parseDoubleValue(objs[21]));
//			map.put("AzureWave", (int) Wicloud.parseDoubleValue(objs[22]));
//			map.put("liteon", (int) Wicloud.parseDoubleValue(objs[23]));
//			map.put("arris", (int) Wicloud.parseDoubleValue(objs[24]));
//			map.put("天语", (int) Wicloud.parseDoubleValue(objs[25])); // K_Touch
//			map.put("AcSiP", (int) Wicloud.parseDoubleValue(objs[26]));
//			map.put("AsiaPacific", (int) Wicloud.parseDoubleValue(objs[27]));
//			map.put("ChiMei", (int) Wicloud.parseDoubleValue(objs[28]));
//			map.put("富士康", (int) Wicloud.parseDoubleValue(objs[29])); // Foxconn
//			map.put("Garmin", (int) Wicloud.parseDoubleValue(objs[30]));
//			map.put("Gemtek", (int) Wicloud.parseDoubleValue(objs[31]));
//			map.put("MediaTek", (int) Wicloud.parseDoubleValue(objs[32]));
//			map.put("高通", (int) Wicloud.parseDoubleValue(objs[33])); // Qualcomm
//			map.put("海信", (int) Wicloud.parseDoubleValue(objs[34])); // Hisense
//			map.put("Roving", (int) Wicloud.parseDoubleValue(objs[35]));
//			map.put("Simcom", (int) Wicloud.parseDoubleValue(objs[36]));
//			map.put("夏普", (int) Wicloud.parseDoubleValue(objs[37])); // SHARP
//			map.put("Wisol", (int) Wicloud.parseDoubleValue(objs[38]));
//			map.put("Wistron", (int) Wicloud.parseDoubleValue(objs[39]));
//			map.put("夏新", (int) Wicloud.parseDoubleValue(objs[40])); // Amoi
//			map.put("波导", (int) Wicloud.parseDoubleValue(objs[41])); // BIRD
//			map.put("飞利浦", (int) Wicloud.parseDoubleValue(objs[42])); // Philips
			map.put("TCL", (int) Wicloud.parseDoubleValue(objs[43]));
			map.put("vivo", (int) Wicloud.parseDoubleValue(objs[44]));
			map.put("乐视", (int) Wicloud.parseDoubleValue(objs[45])); // letv
			// 通过ArrayList构造函数把map.entrySet()转换成list
			mappingList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
			// 通过比较器实现比较排序
			Collections.sort(mappingList,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Map.Entry<String, Integer> mapping1,
								Map.Entry<String, Integer> mapping2) {
							return mapping2.getValue().compareTo(
									mapping1.getValue());
						}
					});
			int ii = 10;
			double top10 = 0;
			List<Brand> brandList = new ArrayList<Brand>();
			for (Map.Entry<String, Integer> mapping : mappingList) {
				if(mapping.getValue() <= 0) {
					continue;
				}
				Brand brand = new Brand();
				brand.setBrand(mapping.getKey());
				brand.setValue(mapping.getValue());
				brandList.add(brand);
				top10 = top10 + mapping.getValue();
				if (--ii <= 0) break;
			}
			int others = (int) (Wicloud.parseDoubleValue(objs[0]) - top10);
			Brand brand = new Brand();
			brand.setBrand("其他"); // Other
			brand.setValue(others);
			brandList.add(brand);
			return brandList;
		}
		return null;
	}
	
	public String rank(List<Brand> brandList) {
		Map<String, Integer> priceMap = new HashMap<String, Integer>(); 
		priceMap.put("苹果", 6499);
		priceMap.put("三星", 4888);
		priceMap.put("诺基亚", 1666);
		priceMap.put("中兴", 979);
		priceMap.put("华为", 2499);
		priceMap.put("HTC", 999);
		priceMap.put("小米", 1199);
		priceMap.put("OPPO", 2799);
		priceMap.put("联想", 579);
		priceMap.put("魅族", 899);
		priceMap.put("金立", 2399);
		priceMap.put("TCL", 899);
		priceMap.put("vivo", 2798);
		priceMap.put("乐视", 999);
		priceMap.put("其他", 0);
		int count = 0;
		for(Brand brand : brandList) {
			count += brand.getValue();
		}
		String rank = "D";
		if(count != 0) {
			int price = 0;
			for(Brand brand : brandList) {
				price += priceMap.get(brand.getBrand()) * brand.getValue() / count;
			}
			if(price >= 0 && price < 1000) {
				rank = "D";
			} else if(price >= 1000 && price < 1500) {
				rank = "C";
			} else if(price >= 1500 && price < 2200) {
				rank = "B";
			} else if(price >= 2200 && price < 3000) {
				rank = "A";
			} else {
				rank = "A+";
			}
		}
		return rank;
	}
	
	public List<Brand> abilityForLarge(String start, String finish) {
		String monidList = Wicloud.getProperty(Constants.CFG_DEFAULT_MONID_LIST);
		List list = dao.findAvgBranddisForLarge(monidList, start, finish);
		if (list != null && list.size() > 0) {
			Object[] objs = (Object[]) list.get(0);
			List<Map.Entry<String, Integer>> mappingList = null;
			Map<String, Integer> map = new TreeMap<String, Integer>();
			map.put("苹果", (int) Wicloud.parseDoubleValue(objs[1])); // Apple
			map.put("三星", (int) Wicloud.parseDoubleValue(objs[2])); // Samsung
			map.put("诺基亚", (int) Wicloud.parseDoubleValue(objs[3])); // Nokia
//			map.put("索尼", (int) Wicloud.parseDoubleValue(objs[4])); // Sony
			map.put("中兴", (int) Wicloud.parseDoubleValue(objs[5])); // ZTE
			map.put("华为", (int) Wicloud.parseDoubleValue(objs[6])); // HUAWEI
//			map.put("华硕", (int) Wicloud.parseDoubleValue(objs[7])); // ASUS
//			map.put("英特尔", (int) Wicloud.parseDoubleValue(objs[8])); // Intel
//			map.put("鸿海", (int) Wicloud.parseDoubleValue(objs[9])); // honhai
			map.put("HTC", (int) Wicloud.parseDoubleValue(objs[10]));
			map.put("小米", (int) Wicloud.parseDoubleValue(objs[11])); // Xiaomi
			map.put("OPPO", (int) Wicloud.parseDoubleValue(objs[12]));
//			map.put("LG", (int) Wicloud.parseDoubleValue(objs[13]));
			map.put("联想", (int) Wicloud.parseDoubleValue(objs[14])); // Lenovo
			map.put("魅族", (int) Wicloud.parseDoubleValue(objs[15])); // Meizu
//			map.put("酷派", (int) Wicloud.parseDoubleValue(objs[16])); // Coolpad无对应的OUI
//			map.put("步步高", (int) Wicloud.parseDoubleValue(objs[17])); // bbk
//			map.put("TP_LINK", (int) Wicloud.parseDoubleValue(objs[18]));
			map.put("金立", (int) Wicloud.parseDoubleValue(objs[19])); // gionee
//			map.put("murata", (int) Wicloud.parseDoubleValue(objs[20]));
//			map.put("inpro", (int) Wicloud.parseDoubleValue(objs[21]));
//			map.put("AzureWave", (int) Wicloud.parseDoubleValue(objs[22]));
//			map.put("liteon", (int) Wicloud.parseDoubleValue(objs[23]));
//			map.put("arris", (int) Wicloud.parseDoubleValue(objs[24]));
//			map.put("天语", (int) Wicloud.parseDoubleValue(objs[25])); // K_Touch
//			map.put("AcSiP", (int) Wicloud.parseDoubleValue(objs[26]));
//			map.put("AsiaPacific", (int) Wicloud.parseDoubleValue(objs[27]));
//			map.put("ChiMei", (int) Wicloud.parseDoubleValue(objs[28]));
//			map.put("富士康", (int) Wicloud.parseDoubleValue(objs[29])); // Foxconn
//			map.put("Garmin", (int) Wicloud.parseDoubleValue(objs[30]));
//			map.put("Gemtek", (int) Wicloud.parseDoubleValue(objs[31]));
//			map.put("MediaTek", (int) Wicloud.parseDoubleValue(objs[32]));
//			map.put("高通", (int) Wicloud.parseDoubleValue(objs[33])); // Qualcomm
//			map.put("海信", (int) Wicloud.parseDoubleValue(objs[34])); // Hisense
//			map.put("Roving", (int) Wicloud.parseDoubleValue(objs[35]));
//			map.put("Simcom", (int) Wicloud.parseDoubleValue(objs[36]));
//			map.put("夏普", (int) Wicloud.parseDoubleValue(objs[37])); // SHARP
//			map.put("Wisol", (int) Wicloud.parseDoubleValue(objs[38]));
//			map.put("Wistron", (int) Wicloud.parseDoubleValue(objs[39]));
//			map.put("夏新", (int) Wicloud.parseDoubleValue(objs[40])); // Amoi
//			map.put("波导", (int) Wicloud.parseDoubleValue(objs[41])); // BIRD
//			map.put("飞利浦", (int) Wicloud.parseDoubleValue(objs[42])); // Philips
			map.put("TCL", (int) Wicloud.parseDoubleValue(objs[43]));
			map.put("vivo", (int) Wicloud.parseDoubleValue(objs[44]));
			map.put("乐视", (int) Wicloud.parseDoubleValue(objs[45])); // letv
			// 通过ArrayList构造函数把map.entrySet()转换成list
			mappingList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
			// 通过比较器实现比较排序
			Collections.sort(mappingList,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Map.Entry<String, Integer> mapping1,
								Map.Entry<String, Integer> mapping2) {
							return mapping2.getValue().compareTo(
									mapping1.getValue());
						}
					});
			int ii = 10;
			double top10 = 0;
			List<Brand> brandList = new ArrayList<Brand>();
			for (Map.Entry<String, Integer> mapping : mappingList) {
				Brand brand = new Brand();
				brand.setBrand(mapping.getKey());
				brand.setValue(mapping.getValue());
				brandList.add(brand);
				top10 = top10 + mapping.getValue();
				if (--ii <= 0) break;
			}
			int others = (int) (Wicloud.parseDoubleValue(objs[0]) - top10);
			Brand brand = new Brand();
			brand.setBrand("其他"); // Other
			brand.setValue(others);
			brandList.add(brand);
			return brandList;
		}
		return null;
	}
	
}
