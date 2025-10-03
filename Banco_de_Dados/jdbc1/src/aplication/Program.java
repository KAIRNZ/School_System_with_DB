package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("insert into produto (nome, quantidade, valor) values (?, ?, ?)");

			st.setString(1, "café");
			st.setInt(2, 6);
			st.setDouble(3, 16);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
