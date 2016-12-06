package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Comment;
import bbs.exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append(" text");
			sql.append(", created_at");
			sql.append(", updated_at");
			sql.append(", user_id");
			sql.append(", message_id");
			sql.append(") VALUES (");
			sql.append("?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUser_id());
			ps.setInt(3, comment.getMessage_id());

			System.out.println(ps.toString());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
