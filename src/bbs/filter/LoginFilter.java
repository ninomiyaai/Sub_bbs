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
import javax.servlet.http.HttpSession;

import bbs.beans.User;

@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		StringBuffer requestUrl = ((HttpServletRequest) request).getRequestURL();
		String requestUrlStr = requestUrl.toString();


//		System.out.println(requestUrlStr);

		if (requestUrlStr.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = ((HttpServletRequest)request).getSession();
		//キャスト

		User user = (User)session.getAttribute("loginUser");

		if (user == null) {
//			System.out.println("ccc");
			List<String> messages = new ArrayList<String>();
			messages.add("不正なアクセスです");
			request.setAttribute("errorMessages", messages);

//			((HttpServletResponse)response).sendRedirect("./");
			request.getRequestDispatcher("login").forward(request,response);
			return;
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}