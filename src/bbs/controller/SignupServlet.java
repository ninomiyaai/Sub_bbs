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

import org.apache.commons.lang.StringUtils;

import bbs.beans.Branch;
import bbs.beans.Position;
import bbs.beans.User;
import bbs.service.BranchService;
import bbs.service.PositionService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches",  branches);
		List<Position> positions = new PositionService().getPosition();
		request.setAttribute("positions",  positions);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

//		User signupUser = getSignupUser(request, messages);
//		session.setAttribute("signupUser", signupUser);



		if (isValid(request, messages) == true) {
			User signupUser = getSignupUser(request, messages);
			session.setAttribute("signupUser", signupUser);

			new UserService().register(signupUser);

			session.removeAttribute("signupUser");
			response.sendRedirect("./");
		} else {
			User signupUser = getSignupUser(request, messages);
			session.setAttribute("signupUser", signupUser);
			session.setAttribute("errorMessages", messages);

			response.sendRedirect("signup");
		}
	}

	private User getSignupUser(HttpServletRequest request, List<String> messages)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User signupUser = (User) session.getAttribute("signupUser");
		if (signupUser == null) {
			 signupUser = new User();
		}

		signupUser.setLogin_id(request.getParameter("login_id"));
		signupUser.setPassword(request.getParameter("password"));
		signupUser.setName(request.getParameter("name"));
		try {
			signupUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		} catch (NumberFormatException e) {
			signupUser.setBranch_id(0);
		}
		try {
			signupUser.setPosition_id(Integer.parseInt(request.getParameter("position_id")));
		} catch (NumberFormatException e) {
			signupUser.setPosition_id(0);
		}
		return signupUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String branch_id = request.getParameter("branch_id");
		String position_id = request.getParameter("position_id");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店を選択してください");
		}
		if (StringUtils.isEmpty(position_id) == true) {
			messages.add("役職を選択してください");
		}

		try {
			Integer.parseInt(request.getParameter("branch_id"));
		} catch (NumberFormatException e){
		}
		try {
			Integer.parseInt(request.getParameter("position_id"));
		} catch (NumberFormatException e){
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


}