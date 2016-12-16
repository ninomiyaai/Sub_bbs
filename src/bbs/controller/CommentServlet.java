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

import bbs.beans.Comment;
import bbs.beans.User;
import bbs.service.CommentService;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> comments = new ArrayList<String>();

		if (isValid(request, comments) == true) {

			Comment comment = getComment(request, comments);

			new CommentService().register(comment);

			session.removeAttribute("message");
			response.sendRedirect("./");
		} else {
			Comment comment = getComment(request, comments);
			session.setAttribute("comment", comment);
			session.setAttribute("errorMessages", comments);

			response.sendRedirect("./");
		}
	}

	private Comment getComment(HttpServletRequest request, List<String> comments)
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

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String text = request.getParameter("text");

		if (StringUtils.isBlank(text) == true) {
			comments.add("コメントを入力してください");
		} else {
			if (500 < text.length()) {
				comments.add("コメントは500文字以下で入力してください");
			}
		}
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}

