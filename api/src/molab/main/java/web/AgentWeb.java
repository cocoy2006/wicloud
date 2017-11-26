package molab.main.java.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import molab.main.java.component.MSIComponent;
import molab.main.java.service.AgentService;
import molab.main.java.service.MenuService;
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
@RequestMapping(value = "/agent")
public class AgentWeb {
	
	@Autowired
	private AgentService as;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService us;
	
	@RequestMapping(value = "")
	public String _() {
		return "agent/signin";
	}
	
	@RequestMapping(value = "/")
	public String __() {
		return "agent/signin";
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpSession session, @RequestParam String monid) {
		ModelAndView mav = new ModelAndView("agent/edit");

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
	
	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("agent/home");
		
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
		
		String username = String.valueOf(session.getAttribute(Constants.SESSION_USERNAME));
		List<MSIComponent> allList = us.findAll(username, searchString);
		List<MSIComponent> msicList = new ArrayList<MSIComponent>();
		List<MSIComponent> aMsicList = new ArrayList<MSIComponent>();
		us.findNormalAndAbnormal(allList, msicList, aMsicList);
		
		mav.addObject("msicList", msicList);
		mav.addObject("aMsicList", aMsicList);
		return mav;
	}
	
	@RequestMapping(value = "/pwd")
	public ModelAndView pwd() {
		ModelAndView mav = new ModelAndView("agent/pwd");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/pwdAction", method = RequestMethod.POST)
	public String pwdAction(HttpSession session,
			@RequestParam String passwdOri,
			@RequestParam String passwd) {
		String username = String.valueOf(session.getAttribute(Constants.SESSION_USERNAME));
		int result = us.pwd(username, passwdOri, passwd);
		if(result == Status.Common.SUCCESS.getInt()) {
			session.removeAttribute(Constants.SESSION_USERNAME);
		}
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/signin")
	public String signin() {
		return "agent/signin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/signinAction", method = RequestMethod.POST)
	public String signinAction(HttpSession session, 
			@RequestParam String username, 
			@RequestParam String password) {
		int result = us.signin(username, password, Constants.USER_TYPE_AGENT);
		if(result == Status.Common.SUCCESS.getInt()) {
			session.setAttribute(Constants.SESSION_USERNAME, username);
		}
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/signout")
	public String signout(HttpSession session) {
		session.removeAttribute(Constants.SESSION_USERNAME);
		return "agent/signin";
	}
	
}
