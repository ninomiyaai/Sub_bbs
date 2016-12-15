package bbs.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

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

		String category = request.getParameter("category");
		String oldDate = request.getParameter("oldDate");
		String newDate = request.getParameter("newDate");

		System.out.println(newDate);

		if (StringUtils.isEmpty(oldDate)) {
//			oldDate = "2000-01-01";
			oldDate = new MessageService().getOldDate().getCreated_at().toString();
		}
		if (StringUtils.isEmpty(newDate)) {
//			newDate = "2016-12-15";
			newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}

		// 一覧を取得する処理
		List<UserMessage> messages = new MessageService().getMessage(category, oldDate, newDate);
		List<UserComment> comments = new CommentService().getComment();
		List<Message> categories = new MessageService().getCategories();

		// JSPに渡す処理
		request.setAttribute("messages",  messages);
		request.setAttribute("comments",  comments);
		request.setAttribute("categories", categories);
		request.setAttribute("category", category);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		request.getRequestDispatcher("home.jsp").forward(request,response);
	}

}

