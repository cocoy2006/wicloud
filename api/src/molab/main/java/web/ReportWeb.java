package molab.main.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import molab.main.java.component.Brand;
import molab.main.java.component.out.Day;
import molab.main.java.component.out.Traffic;
import molab.main.java.service.ApiService;
import molab.main.java.service.BranddisService;
import molab.main.java.service.MonindexService;
import molab.main.java.service.TotalinfoService;
import molab.main.java.service.WhisperService;
import molab.main.java.util.Constants;
import molab.main.java.util.DateUtil;
import molab.main.java.util.Status;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportWeb {

	@Autowired
	private ApiService service;
	
	@Autowired
	private BranddisService bs;
	
	@Autowired
	private MonindexService ms;

	@Autowired
	private TotalinfoService ts;
	
	@Autowired
	private WhisperService ws;
	
//	@Autowired
//	private RealtimedataInService ris;
	
//	@Autowired
//	private RtcountService rs;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpSession session) {
		ModelAndView mav = new ModelAndView("report/index");
		return mav;
	}

	@RequestMapping(value = "/all")
	public ModelAndView all(HttpSession session) 
			throws ClientProtocolException, IOException {
		ModelAndView mav = new ModelAndView("report/all");
		String monid = String.valueOf(session.getAttribute(Constants.SESSION_MONID));
		mav.addObject("monid", monid);
		// 入店顾客数
		int total = service.dayTotal(monid).getTotal();
		mav.addObject("total", total);
		// 入店率
		double rate = service.dayRate(monid).getRate();
		mav.addObject("rate", rate);
		// 平均停留时间（分钟）
		double stay = service.dayStay(monid).getStay();
		mav.addObject("stay", stay);
		// 前一天的每小时的人流量曲线图
		List<Traffic> trafficList = service.dayH(monid);
		mav.addObject("trafficList", trafficList);
		// 近7日入店顾客数
		List<Day> weekTotalList = service.weekTotal(monid);
		mav.addObject("weekTotalList", weekTotalList);
		// 近7日入店率
		List<Day> rateList = service.weekRate(monid);
		mav.addObject("rateList", rateList);
		// 近7日平均停留时间（分钟）
		List<Day> stayList = service.weekStay(monid);
		mav.addObject("stayList", stayList);
		// 近30日入店顾客数
		List<Day> monthTotalList = service.monthTotal(monid);
		mav.addObject("monthTotalList", monthTotalList);
		// 近30日新老顾客占比
		Day percent = service.monthPercent(monid);
		mav.addObject("percent", percent);
		// 近30日周内客流信息（平均）
		List<Traffic> wList = ts.loadAll(monid, DateUtil.day(-29), DateUtil.day(0));
		mav.addObject("wList", wList);
		// 近30日日内客流信息（平均）
		List<Traffic> hList = service.monthH(monid);
		mav.addObject("hList", hList);
		// 近30日顾客消费能力指数
		List<Brand> brandList = bs.avg_(monid, DateUtil.day(-29), DateUtil.day(0));
		mav.addObject("brandList", brandList);
//		if(brandList.get(0).getValue() == 0) {
//			mav.addObject("noBrand", true);
//		} else {
			mav.addObject("rank", bs.rank(brandList));
//		}
		return mav;
	}
	
	@RequestMapping(value = "/day")
	public ModelAndView day(HttpSession session) 
			throws ClientProtocolException, IOException {
		ModelAndView mav = new ModelAndView("report/day");
		String monid = String.valueOf(session.getAttribute(Constants.SESSION_MONID));
		// 入店顾客数
		int total = service.dayTotal(monid).getTotal();
		mav.addObject("total", total);
		// 入店率
		double rate = service.dayRate(monid).getRate();
		mav.addObject("rate", rate);
		// 平均停留时间
		double stay = service.dayStay(monid).getStay();
		mav.addObject("stay", stay);
		// 前一天的每小时的人流量曲线图
		List<Traffic> trafficList = service.dayH(monid);
		mav.addObject("trafficList", trafficList);
		// for page shift
		mav.addObject("monid", monid);
		mav.addObject("monname", ms.findName(monid));
		return mav;
	}
	
	@RequestMapping(value = "/week")
	public ModelAndView week(HttpSession session) 
			throws ClientProtocolException, IOException {
		ModelAndView mav = new ModelAndView("report/week");
		String monid = String.valueOf(session.getAttribute(Constants.SESSION_MONID));
		// 近7日入店顾客数
		List<Day> totalList = service.weekTotal(monid);
		mav.addObject("totalList", totalList);
		// 近7日入店率
		List<Day> rateList = service.weekRate(monid);
		mav.addObject("rateList", rateList);
		// 近7日平均停留时间
		List<Day> stayList = service.weekStay(monid);
		mav.addObject("stayList", stayList);
		// for page shift
		mav.addObject("monid", monid);
		mav.addObject("monname", ms.findName(monid));
		return mav;
	}
	
	@RequestMapping(value = "/month")
	public ModelAndView month(HttpSession session) 
			throws ClientProtocolException, IOException {
		ModelAndView mav = new ModelAndView("report/month");
		String monid = String.valueOf(session.getAttribute(Constants.SESSION_MONID));
		// 近30日入店顾客数
		List<Day> totalList = service.monthTotal(monid);
		mav.addObject("totalList", totalList);
		// 近30日新老顾客占比
		Day percent = service.monthPercent(monid);
		mav.addObject("percent", percent);
		// 近30日周内客流信息
		List<Traffic> wList = ts.loadAll(monid, DateUtil.day(-29), DateUtil.day(0));
		mav.addObject("wList", wList);
		// 近30日日内客流信息
		List<Traffic> hList = service.monthH(monid);
		mav.addObject("hList", hList);
		// 近30日顾客消费能力指数
		List<Brand> brandList = bs.avg_(monid, DateUtil.day(-29), DateUtil.day(0));
		mav.addObject("brandList", brandList);
//		if(brandList.get(0).getValue() == 0) {
//			mav.addObject("noBrand", true);
//		} else {
			mav.addObject("rank", bs.rank(brandList));
//		}
		// for page shift
		mav.addObject("monid", monid);
		mav.addObject("monname", ms.findName(monid));
		return mav;
	}
	
	@RequestMapping(value = "/my")
	public ModelAndView my(HttpSession session) {
		ModelAndView mav = new ModelAndView("report/my");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/myAction", method = RequestMethod.POST)
	public String myAction(HttpSession session,
			@RequestParam String code) {
		String monid = ws.whisper(code);
		if(monid != null) {
			session.setAttribute(Constants.SESSION_MONID, monid);
			session.setAttribute(Constants.SESSION_MONNAME, ms.findName(monid));
			return String.valueOf(Status.Common.SUCCESS.getInt());
		}
		return String.valueOf(Status.Common.ERROR.getInt());
	}
	
//	@RequestMapping(value = "/large")
//	public ModelAndView large(HttpSession session) {
//		ModelAndView mav = new ModelAndView("large");
//		// 30日顾客消费能力指数
//		List<Brand> brandList = bs.abilityForLarge(DateUtil.day(-29), DateUtil.day(0));
//		mav.addObject("brandList", brandList);
//		// 30日周内客流信息
//		List<Traffic> wList = ts.loadAllForLarge(DateUtil.day(-29), DateUtil.day(0));
//		mav.addObject("wList", wList);
//		// 30日日内客流信息
//		List<Traffic> hList = service.trafficForLarge(DateUtil.day(-30), DateUtil.day(-1));
//		mav.addObject("hList", hList);
//		// 近7日平均停留时间
//		List<Day> stayList = service.weekStayForLarge();
//		mav.addObject("stayList", stayList);
//		return mav;
//	}

//	@ResponseBody
//	@RequestMapping(value = "/large/realtimePastData")
//	public String realtimePastData() throws ParseException {
//		return rs.realtimeData(DateUtil.dateOnStart(0), DateUtil.dateOnNow(0));
//	}
	
//	@ResponseBody
//	@RequestMapping(value = "/large/realtimeData")
//	public String realtimeData(HttpServletRequest request) throws ParseException {
//		int interval = Integer.parseInt(request.getParameter("interval"));
//		String finish = DateUtil.dateOnNow(0);
//		String start = DateUtil.parse(DateUtil.parse(finish) - interval);
//		return rs.realtimeData(start, finish);
//	}
	
//	@ResponseBody
//	@RequestMapping(value = "/large/heatmap")
//	public String heatmap() throws ClientProtocolException, IOException {
//		List<Day> dayList = service.heatmapForLarge();
//		return new Gson().toJson(dayList);
//	}
	
//	@RequestMapping(value = "/shake")
//	public ModelAndView shake(HttpSession session) {
//		ModelAndView mav = new ModelAndView("shake");
//		return mav;
//	}
	
}
