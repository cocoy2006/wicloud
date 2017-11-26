package molab.main.java.web;

import java.io.IOException;
import java.util.List;

import molab.main.java.component.Brand;
import molab.main.java.component.out.Day;
import molab.main.java.component.out.Traffic;
import molab.main.java.service.ApiService;
import molab.main.java.service.BranddisService;
import molab.main.java.service.TotalinfoService;
import molab.main.java.util.DateUtil;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class ApiWeb {

	@Autowired
	private ApiService service;
	
	@Autowired
	private BranddisService bs;

	@Autowired
	private TotalinfoService ts;

	@ResponseBody
	@RequestMapping(value = "/day/total/{monid}")
	public String dayTotal(@PathVariable String monid)
			throws ClientProtocolException, IOException {
		Day day = service.dayTotal(monid);
		return new Gson().toJson(day);
	}

	@ResponseBody
	@RequestMapping(value = "/day/rate/{monid}")
	public String dayRate(@PathVariable String monid) throws ClientProtocolException, IOException {
		Day day = service.dayRate(monid);
		return new Gson().toJson(day);
	}

	@ResponseBody
	@RequestMapping(value = "/day/stay/{monid}")
	public String dayStay(@PathVariable String monid) throws ClientProtocolException, IOException {
		Day day = service.dayStay(monid);
		return new Gson().toJson(day);
	}

	/**
	 * 前一天的每小时的人流量曲线图
	 */
	@ResponseBody
	@RequestMapping(value = "/day/h/{monid}")
	public String dayH(@PathVariable String monid) {
		List<Traffic> trafficList = service.dayH(monid);
		return new Gson().toJson(trafficList);
	}

	@ResponseBody
	@RequestMapping(value = "/week/total/{monid}")
	public String weekTotal(@PathVariable String monid)
			throws ClientProtocolException, IOException {
		List<Day> dayList = service.weekTotal(monid);
		return new Gson().toJson(dayList);
	}

	@ResponseBody
	@RequestMapping(value = "/week/rate/{monid}")
	public String weekRate(@PathVariable String monid)
			throws ClientProtocolException, IOException {
		List<Day> dayList = service.weekRate(monid);
		return new Gson().toJson(dayList);
	}

	@ResponseBody
	@RequestMapping(value = "/week/stay/{monid}")
	public String weekStay(@PathVariable String monid)
			throws ClientProtocolException, IOException {
		List<Day> dayList = service.weekStay(monid);
		return new Gson().toJson(dayList);
	}

	@ResponseBody
	@RequestMapping(value = "/month/total/{monid}")
	public String monthTotal(@PathVariable String monid)
			throws ClientProtocolException, IOException {
		List<Day> dayList = service.monthTotal(monid);
		return new Gson().toJson(dayList);
	}

	@ResponseBody
	@RequestMapping(value = "/month/percent/{monid}")
	public String monthPercent(@PathVariable String monid)
			throws ClientProtocolException, IOException {
		Day day = service.monthPercent(monid);
		return new Gson().toJson(day);
	}

	/**
	 * 近30日周内客流信息
	 */
	@ResponseBody
	@RequestMapping(value = "/month/w/{monid}")
	public String monthW(@PathVariable String monid) {
		List<Traffic> trafficList = ts.loadAll(monid, DateUtil.day(-30), DateUtil.day(-1));
		return new Gson().toJson(trafficList);
	}

	/**
	 * 近30日日内客流信息
	 */
	@ResponseBody
	@RequestMapping(value = "/month/h/{monid}")
	public String monthH(@PathVariable String monid) {
		List<Traffic> trafficList = service.monthH(monid);
		return new Gson().toJson(trafficList);
	}

	@ResponseBody
	@RequestMapping(value = "/month/ability/{monid}")
	public String monthAbility(@PathVariable String monid)
			throws ClientProtocolException, IOException {
		List<Brand> brandList = bs.avg_(monid, DateUtil.day(-30), DateUtil.day(-1));
		return new Gson().toJson(brandList);
	}

}
