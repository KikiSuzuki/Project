package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
	public Connection getConnection() {
		Connection connection = null;
		try {
//			InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
//			Properties properties = new Properties();
//			properties.load(inputStream);
//			String jdbcName = properties.getProperty("jdbcName");
//			String dbUrl =  properties.getProperty("dbUrl");
//			String dbName = properties.getProperty("dbName");
//			String dbPwd = properties.getProperty("dbPwd");
//			Class.forName(jdbcName);
//			connection = DriverManager.getConnection(dbUrl,dbName, dbPwd);
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soul?serverTimezone=UTC&useSSL=false", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws SQLException {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet getDBResultSet(Connection connection, String query) throws SQLException {
			ResultSet rs = null;
			if (null != connection) {
				PreparedStatement st = connection.prepareStatement(query);
				rs = st.executeQuery();
			}
			return rs;
	}

//	public static void runQuery(Connection connection, String query) throws SQLException {
//	if (null != connection) {
//		PreparedStatement st = connection.prepareStatement(query);
//		st.executeUpdate();
//		}else{
//			System.out.println("Query execution failed!");
//		}
//	}
}
