package molab.main.java.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import molab.main.java.entity.Menu;
import molab.main.java.service.MenuService;
import molab.main.java.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Repository
public class SigninInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String[] noFilters = new String[] { "index", "day", "week", "month", "all", "my", "service", "user/signin", "agent/signin" };
		String uri = request.getServletPath();
		boolean beFilter = true;
		for (String s : noFilters) {
			if (uri.indexOf(s) != -1) {
				beFilter = false;
				break;
			}
		}
		if (beFilter) {
			Object obj = request.getSession().getAttribute(Constants.SESSION_USERNAME);
			if (null == obj) {
				if(uri.startsWith(Constants.USER_TYPE_USER, 1)) {
					response.sendRedirect(request.getContextPath() + "/user/signin");
				} else {
					response.sendRedirect(request.getContextPath() + "/agent/signin");
				}
				return false;
			} else {
				String username = String.valueOf(obj);
				List<Menu> menuList = menuService.findByUsername(username);
				request.getSession().setAttribute("menuList", menuList);
			}
		}
		return super.preHandle(request, response, handler);
	}
}
