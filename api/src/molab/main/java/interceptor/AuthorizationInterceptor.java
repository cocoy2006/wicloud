package molab.main.java.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import molab.main.java.util.Constants;
import molab.main.java.util.Wicloud;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Repository
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String[] filters = new String[] { "index", "day", "week", "month", "all" };
		String uri = request.getServletPath();
		boolean beFilter = false;
		for (String s : filters) {
			if (uri.indexOf(s) != -1) {
				beFilter = true;
				break;
			}
		}
		if (beFilter) {
			Object obj = request.getSession().getAttribute(Constants.SESSION_MONID);
			if (null == obj) {
				request.getSession().setAttribute(Constants.SESSION_MONID, Wicloud.getProperty(Constants.CFG_DEFAULT_MONID));
				request.getSession().setAttribute(Constants.SESSION_MONNAME, Wicloud.getProperty(Constants.CFG_DEFAULT_MONNAME));
			}
		}
		return super.preHandle(request, response, handler);
	}
}
