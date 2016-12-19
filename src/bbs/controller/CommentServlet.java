package bbs.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bbs.beans.Comment;
import bbs.beans.Message;
import bbs.beans.User;
import bbs.beans.UserComment;
import bbs.beans.UserMessage;
import bbs.service.CommentService;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

//		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			Comment comment = getComment(request, messages);

			new CommentService().register(comment);

//			session.removeAttribute("message");
			response.sendRedirect("./");
		} else {
			Comment comment = getComment(request, messages);
			request.setAttribute("comment", comment);
			request.setAttribute("errorMessages", messages);

			String category = request.getParameter("category");
			String oldDate = request.getParameter("oldDate");
			String newDate = request.getParameter("newDate");

			System.out.println(newDate);

			if (StringUtils.isEmpty(oldDate)) {
//				oldDate = "2000-01-01";
				oldDate = new MessageService().getOldDate().getCreated_at().toString();
			}
			if (StringUtils.isEmpty(newDate)) {
//				newDate = "2016-12-15";
				newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			}

			// 一覧を取得する処理
			List<UserMessage> userMessages = new MessageService().getMessage(category, oldDate, newDate);
			List<UserComment> comments = new CommentService().getComment();
			List<Message> categories = new MessageService().getCategories();

			// JSPに渡す処理
			request.setAttribute("messages",  userMessages);
			request.setAttribute("comments",  comments);
			request.setAttribute("categories", categories);
			request.setAttribute("category", category);
			request.setAttribute("oldDate", oldDate);
			request.setAttribute("newDate", newDate);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

	private Comment getComment(HttpServletRequest request, List<String> messages)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		Comment comment = (Comment) session.getAttribute("comment");
		User loginUser = (User) session.getAttribute("loginUser");

		if ( comment == null) {
			comment = new Comment();
		}
		comment.setText(request.getParameter("text"));
		comment.setUser_id(loginUser.getId());
		comment.setMessage_id(Integer.parseInt(request.getParameter("message_id")));

		return comment;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String text = request.getParameter("text");

		if (StringUtils.isBlank(text) == true) {
			messages.add("コメントを入力してください");
		} else {
			if (500 < text.length()) {
				messages.add("コメントは500文字以下で入力してください");
			}
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}

