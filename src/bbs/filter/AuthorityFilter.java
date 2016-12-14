package bbs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.beans.User;

@WebFilter("/*")
public class AuthorityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		StringBuffer requestUrl = ((HttpServletRequest) request).getRequestURL();
		String requestUrlStr = requestUrl.toString();


//		System.out.println(requestUrlStr);

		//キャスト
		HttpSession session = ((HttpServletRequest)request).getSession();

		User user = (User)session.getAttribute("loginUser");

		if (requestUrlStr.endsWith("/userControl")
				|| requestUrlStr.endsWith("/userSetting")
					|| requestUrlStr.endsWith("/signup")) {

			if (!(((user.getBranch_id()) == 1) && ((user.getPosition_id()) == 4))) {

				List<String> messages = new ArrayList<String>();
				messages.add("権限を持っていません。");
				session.setAttribute("errorMessages", messages);

				((HttpServletResponse)response).sendRedirect("./");

				return;
			}
		}

		chain.doFilter(request, response);

	}

	public void AuthorityCheck(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {



		//キャスト
		HttpSession session = ((HttpServletRequest)request).getSession();

		User user = (User)session.getAttribute("loginUser");
		if (!((user.getBranch_id() == 1) && (user.getPosition_id() == (4 | 3)))) {

			List<String> messages = new ArrayList<String>();
			messages.add("権限を持っていません。");
			session.setAttribute("errorMessages", messages);

			((HttpServletResponse)response).sendRedirect("./");

			return;
		}
	}


	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}