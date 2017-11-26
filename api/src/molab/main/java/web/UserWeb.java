package molab.main.java.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import molab.main.java.component.MSIComponent;
import molab.main.java.entity.Item;
import molab.main.java.entity.Monindex;
import molab.main.java.entity.SubItem;
import molab.main.java.entity.Userinfo;
import molab.main.java.service.MenuService;
import molab.main.java.service.MonindexService;
import molab.main.java.service.UserService;
import molab.main.java.util.Constants;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/user")
public class UserWeb {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MonindexService ms;
	
	@Autowired
	private UserService us;
	
	@RequestMapping(value = "")
	public String _() {
		return "user/signin";
	}
	
	@RequestMapping(value = "/")
	public String __() {
		return "user/signin";
	}

	@RequestMapping(value = "/add")
	public ModelAndView add(HttpSession session) {
		ModelAndView mav = new ModelAndView("user/add");
		
		List<Item> itemList = us.findAllItem();
		mav.addObject("itemList", itemList);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addAction", method = RequestMethod.POST)
	public String addAction(HttpSession session, 
			@RequestParam String monid) {
		String username = String.valueOf(session.getAttribute(Constants.SESSION_USERNAME));
		int result = us.add(username, monid);
		return String.valueOf(result);
	}
	
	@RequestMapping(value = "/agent/add")
	public ModelAndView addAgent(HttpSession session) {
		ModelAndView mav = new ModelAndView("user/addAgent");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agent/addAction", method = RequestMethod.POST)
	public String addAgentAction(@RequestParam String userName, 
			@RequestParam String passwd, 
			@RequestParam String shopadd, 
			@RequestParam String shopName, 
			@RequestParam String contact) {
		int result = us.addAgent(userName, passwd, shopadd, shopName, contact);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/agent")
	public ModelAndView agent(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("user/agent");
		
		List<Userinfo> agentList = us.findByUserType(Constants.USER_TYPE_AGENT);
		mav.addObject("agentList", agentList);
		
		List<Monindex> unassignedList = ms.findByUsername(Constants.ADMIN_USERNAME);
		mav.addObject("unassignedList", unassignedList);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/item", produces = "application/json;charset=UTF-8")
	public String allItem() {
		List<Item> itemList = us.findAllItem();
		return new Gson().toJson(itemList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/subItem", produces = "application/json;charset=UTF-8")
	public String allSubItem() {
		List<SubItem> subItemList = us.findAllSubItem();
		return new Gson().toJson(subItemList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/assignAgentAction", method = RequestMethod.POST)
	public String assignAgentAction(@RequestParam String monid, @RequestParam String username) {
		int result = ms.assign(monid, username);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/assignMonAction", method = RequestMethod.POST)
	public String assignMonAction(@RequestParam String username, @RequestParam String ids) {
		int result = ms.assignMon(username, ids);
		return new Gson().toJson(result);
	}
	
	/**
	 * @deprecated */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpSession session, @RequestParam String monid) {
		ModelAndView mav = new ModelAndView("user/edit");
		
		MSIComponent msi = us.findByMonid(monid);
		mav.addObject("msi", msi);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editAction", method = RequestMethod.POST)
	public String editAction(@RequestParam String monid, 
			@RequestParam String monName, 
			@RequestParam String monAdd, 
			@RequestParam(value="subItem") Integer subItemId, 
			@RequestParam Integer wallDis, 
			@RequestParam String code) {
		int result = us.edit(monid, monName, monAdd, subItemId, wallDis, code);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/agent/edit")
	public ModelAndView editAgent(HttpSession session, @RequestParam String userName) {
		ModelAndView mav = new ModelAndView("user/editAgent");
		mav.addObject("user", us.findByUserName(userName));
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agent/editAction", method = RequestMethod.POST)
	public String editAgentAction(@RequestParam String userName,
			@RequestParam String shopadd, 
			@RequestParam String shopName, 
			@RequestParam String contact) {
		int result = us.editAgent(userName, shopadd, shopName, contact);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("user/home");
		
		String searchString = request.getParameter("searchString");
		if(searchString != null) { // request is first priority
			session.setAttribute(Constants.SESSION_SEARCH_STRING, searchString);
		} else {
			Object searchObj = session.getAttribute("searchString");
			if(searchObj != null) { // search string is in session
				searchString = String.valueOf(searchObj);
			} else {
				searchString = ""; // no search at all
			}
		}
		
		List<MSIComponent> allList = us.findAll(searchString);
		List<MSIComponent> msicList = new ArrayList<MSIComponent>();
		List<MSIComponent> aMsicList = new ArrayList<MSIComponent>();
		us.findNormalAndAbnormal(allList, msicList, aMsicList);
		
		mav.addObject("msicList", msicList);
		mav.addObject("aMsicList", aMsicList);
		
		List<Userinfo> agentList = us.findByUserType(Constants.USER_TYPE_AGENT);
		mav.addObject("agentList", agentList);
		
		return mav;
	}
	
	@RequestMapping(value = "/links")
	public ModelAndView links(HttpSession session) {
		ModelAndView mav = new ModelAndView("user/links");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/look")
	public String look(HttpSession session, @RequestParam String monid) {
		session.setAttribute(Constants.SESSION_MONID, monid);
		session.setAttribute(Constants.SESSION_MONNAME, ms.findName(monid));
		return new Gson().toJson(Status.Common.SUCCESS.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/lookAll")
	public String lookAll(HttpSession session, @RequestParam String monid) {
		session.setAttribute(Constants.SESSION_MONID, monid);
		session.setAttribute(Constants.SESSION_MONNAME, ms.findName(monid));
		return new Gson().toJson(Status.Common.SUCCESS.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/recoverAction", method = RequestMethod.POST)
	public String recoverAction(@RequestParam String monid) {
		int result = us.recover(monid);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/remove")
	public String remove(@RequestParam String monid) {
		int result = us.remove(monid);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/agent/remove")
	public String removeAgent(@RequestParam String userName) {
		int result = us.removeAgent(userName);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/signin")
	public String signin() {
		return "user/signin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/signinAction", method = RequestMethod.POST)
	public String signinAction(HttpSession session, 
			@RequestParam String username, @RequestParam String password) {
		int result = us.signin(username, password, Constants.USER_TYPE_USER);
		if(result == Status.Common.SUCCESS.getInt()) {
			session.setAttribute(Constants.SESSION_USERNAME, username);
		}
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/signout")
	public String signout(HttpSession session) {
		session.removeAttribute(Constants.SESSION_USERNAME);
		return "user/signin";
	}

}
