package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.User;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/userControl" })
public class UserControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<User> users = new UserService().getUsers();
		request.setAttribute("users",  users);

		request.getRequestDispatcher("/userControl.jsp").forward(request,response);
	}



	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User deletedUser = getDeletedUser(request);

		new UserService().updateDeleted(deletedUser);

		//request.getRequestDispatcher("/userControl.jsp").forward(request,response);
		response.sendRedirect("userControl");

	}

	private User getDeletedUser(HttpServletRequest request)
			throws IOException, ServletException {

		int id = (Integer.parseInt(request.getParameter("user_id")));

		User user = new User();
		user.setId(id);
		user.setDeleted(Integer.parseInt(request.getParameter("deleted")));

		return user;
	}

}