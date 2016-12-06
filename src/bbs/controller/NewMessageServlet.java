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

import bbs.beans.Message;
import bbs.beans.User;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/newMessage" })
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("newMessage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {

			Message message = getMessage(request, messages);
			session.setAttribute("message", message);

			new MessageService().register(message);

			session.removeAttribute("message");
			response.sendRedirect("./");
		} else {
			Message message = getMessage(request, messages);
			session.setAttribute("message", message);
			session.setAttribute("errorMessages", messages);

			response.sendRedirect("newMessage");
		}
	}

	private Message getMessage(HttpServletRequest request, List<String> messages)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		Message message = (Message) session.getAttribute("message");
		User loginUser = (User) session.getAttribute("loginUser");
		if ( message == null) {
			 message = new Message();
		}
		message.setTitle(request.getParameter("title"));
		message.setText(request.getParameter("text"));
		message.setCategory(request.getParameter("category"));
		message.setUser_id(loginUser.getId());

		return message;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isEmpty(title) == true) {
			messages.add("件名を入力してください");
		}
		if ( 50 < title.length()) {
			messages.add("件名は50文字以下で入力してください");
		}
		if (StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください");
		}
		if ( 1000 < text.length()) {
			messages.add("本文は1000文字以下で入力してください");
		}
		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを入力してください");
		}
		if ( 10 < category.length()) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}