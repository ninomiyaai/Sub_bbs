package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.Message;
import bbs.beans.UserComment;
import bbs.beans.UserMessage;
import bbs.service.CommentService;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<UserMessage> messages = new MessageService().getMessage();
		request.setAttribute("messages",  messages);
		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments",  comments);

		List<Message> categories = new MessageService().getCategories();
		request.setAttribute("categories", categories);

//		System.out.println(comments.size());


		request.getRequestDispatcher("home.jsp").forward(request,response);
	}

}

