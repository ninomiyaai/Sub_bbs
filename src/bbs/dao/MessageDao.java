package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.Message;
import bbs.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append(" title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", created_at");
			sql.append(", updated_at");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getTitle());
			ps.setString(2, message.getText());
			ps.setString(3, message.getCategory());
			ps.setInt(4, message.getUser_id());

			System.out.println(ps.toString());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Message> getCategories(Connection connection) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM messages GROUP BY category";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Message> ret = toCategoryList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Message> toCategoryList(ResultSet rs)
			throws SQLException {

		List<Message> ret = new ArrayList<Message>();
		try {
			while (rs.next()) {
				String category = rs.getString("category");

				Message message = new Message();
				message.setCategory(category);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public Message getOldDate(Connection connection) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM messages order by created_at asc limit 1";

			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Message> ret = toMessageList(rs);

			return ret.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public Message getNewDate(Connection connection) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM messages order by created_at desc limit 1";

			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Message> ret = toMessageList(rs);

			return ret.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Message> toMessageList(ResultSet rs)
			throws SQLException {

		List<Message> ret = new ArrayList<Message>();
		try {
			while (rs.next()) {
				Timestamp created_at = rs.getTimestamp("created_at");

				Message message = new Message();
				message.setCreated_at(created_at);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}




}
