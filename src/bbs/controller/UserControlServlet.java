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


//		List<UserMessage> messages = new MessageService().getMessage();
//		List<UserComment> comments = new CommentService().getComment();
//		System.out.println(comments.size());

//		request.setAttribute("messages",  messages);
//		request.setAttribute("comments",  comments);

		request.setAttribute("users",  users);


		request.getRequestDispatcher("/userControl.jsp").forward(request,response);
	}

}