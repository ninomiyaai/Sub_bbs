package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.beans.User;
import bbs.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
//		response.sendRedirect("login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

//		System.out.println(request.getParameter("password"));

		LoginService loginService = new LoginService();
		User user = loginService.login(login_id, password);

//		System.out.println(user);

		HttpSession session = request.getSession();
		if (user != null) {

			session.setAttribute("loginUser", user);
			response.sendRedirect("./");
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);

			session.setAttribute("login_id", login_id);
			response.sendRedirect("login");

		}
	}

//	private User getDeletedUser(HttpServletRequest request)
//			throws IOException, ServletException {
//
//		String login_id = request.getParameter("login_id");
//
//		User user = new User();
//		user.setLogin_id(login_id);
//		user.setDeleted(Integer.parseInt(request.getParameter("deleted")));
//
//		return user;
//	}

}
