import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class DBConnector {
	
	private static Connection connection;
	private static String dbDriver = "org.gjt.mm.myqsl.Driver";
	private static String dbURL = "jdbc:mysql://localhost/test";
	private static String user = "admin";
	private static String password = "admin";
	
	private static boolean connect() {
		if (connection == null) {
			try {
				Class.forName(dbDriver);
				connection = DriverManager.getConnection(dbURL, user, password);
			}
			catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean close() {
		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Member> getMemberList(String table) throws SQLException {
		
		if (connection == null) {
			connect();
		}
		try (
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM '" + table + "' ORDER BY name");
		){
			List<Member> list = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				boolean alive = Boolean.parseBoolean(rs.getString("alive"));
				Member member = new Member(name, gender, alive);
				list.add(member);
			}
			return list;
		}
	}
	
	public static boolean addMember(String table, Member m) throws SQLException {
		
		if (connection == null) {
			connect();
		}
		if (m == null) {
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		String query = "INSERT INTO " + table + " (name, date_added, gender, date_of_birth, is_alive, email_address, phone_number, address)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, m.getName());
			ps.setDate(2, (Date) calendar.getTime());
			ps.setString(3, m.getGender());
			ps.setString(4, m.getDOB());
			ps.setBoolean(5, m.isAlive());
			ps.setString(6, m.getEmail());
			ps.setString(7, m.getPhone());
			ps.setString(8, m.getAddress());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			return false;
		}
		return true;
	}
}