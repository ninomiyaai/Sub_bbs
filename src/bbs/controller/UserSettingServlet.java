package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@WebServlet(urlPatterns = { "/userSetting" })
@MultipartConfig(maxFileSize = 100000)
public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches",  branches);
		List<Position> positions = new PositionService().getPosition();
		request.setAttribute("positions",  positions);

		HttpSession session = request.getSession();
		int id = (Integer.parseInt(request.getParameter("id")));

	//	if (session.getAttribute("editUser") == null) {
			User editUser = new UserService().getUser(id);
			session.setAttribute("editUser", editUser);
	//	}
	// ↑ のこしとく

		request.getRequestDispatcher("userSetting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches",  branches);
		List<Position> positions = new PositionService().getPosition();
		request.setAttribute("positions",  positions);


		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

//		System.out.println("あああ");

//		User editUser = getEditUser(request);
//		session.setAttribute("editUser", editUser);

		if (isValid(request, messages) == true) {
			User editUser = getEditUser(request);
			session.setAttribute("editUser", editUser);

			new UserService().update(editUser);

//			try {
//				new UserService().update(editUser);
//			} catch (NoRowsUpdatedRuntimeException e) {
//				session.removeAttribute("editUser");
//				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
//				session.setAttribute("errorMessages", messages);
//				response.sendRedirect("userSetting");
//			}

//			session.setAttribute("editUser", editUser);
			session.removeAttribute("editUser");
			response.sendRedirect("userControl");

		} else {
			User editUser = getEditUser(request);
			session.setAttribute("editUser", editUser);
			session.setAttribute("errorMessages", messages);
//			response.sendRedirect("userSetting");
			request.getRequestDispatcher("userSetting.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");
		String password = request.getParameter("password");

		editUser.setLogin_id(request.getParameter("login_id"));

		editUser.setPassword(request.getParameter("password"));

		editUser.setName(request.getParameter("name"));
		try {
			editUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		} catch (NumberFormatException e) {
			editUser.setBranch_id(0);
		}
		try {
			editUser.setPosition_id(Integer.parseInt(request.getParameter("position_id")));
		} catch (NumberFormatException e) {
			editUser.setPosition_id(0);
		}
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String name = request.getParameter("name");
		String branch_id = request.getParameter("branch_id");
		String position_id = request.getParameter("position_id");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (password.equals(confirm_password) == false) {
			messages.add("パスワードが一致しません");
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