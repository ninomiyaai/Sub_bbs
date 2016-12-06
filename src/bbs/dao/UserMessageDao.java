package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.UserMessage;
import bbs.exception.SQLRuntimeException;public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_message ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				String title = rs.getString("title");
				String text = rs.getString("text");
				String name = rs.getString("name");
				int user_id = rs.getInt("user_id");
				int id = rs.getInt("id");
				String category = rs.getString("category");
				Timestamp created_at = rs.getTimestamp("created_at");

				UserMessage message = new UserMessage();
				message.setTitle(title);
				message.setName(name);
				message.setId(id);
				message.setUser_id(user_id);
				message.setCategory(category);
				message.setText(text);
				message.setCreated_at(created_at);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}