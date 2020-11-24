package dao;

import po.FriendInfo;
import po.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	BaseDao bd=new BaseDao();

	public void SaveNewUser(User user) throws SQLException {
		System.out.println("SaveNewUser      UserDao");
		List<Object> params=new ArrayList<>();
		String sql= "INSERT INTO `diploma`.`user` (`login`, `name`," +
				"`gender`, `dob`, `question`, `answer`, `about`, `pwd`, `photopath`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

		params.add(user.getUser_login());
		params.add(user.getUser_name());
		params.add(user.getUser_gender());
		params.add(user.getUser_dob());
		params.add(user.getUser_question());
		params.add(user.getUser_answer());
		params.add(user.getUser_about());
		params.add(user.getUser_password());
		params.add(user.getUser_photopath());
		bd.executeUpdate(sql, params);
	}

	public void SaveUserPeer(String peer, int user_id) throws SQLException {
		System.out.println("SaveUserPeer      UserDao");
		String sql ="update diploma.user set user.peer = '"+peer+"'where user.user_id = '"+user_id+"';";
		DBUtil db=new DBUtil();
		Connection conn =db.getConnection();
		Statement stmt = conn.createStatement();
		int rows = stmt.executeUpdate(sql);
		System.out.println("Success    SaveUserPeer");
	}
	public void DeleteUserPeer(String peer) throws SQLException {
		System.out.println("DeleteUserPeer      UserDao");
		String sql ="update diploma.user set user.peer = '' where user.peer = '"+peer+"';";
		DBUtil db=new DBUtil();
		Connection conn =db.getConnection();
		Statement stmt = conn.createStatement();
		int rows = stmt.executeUpdate(sql);
		System.out.println("Success    DeleteUserPeer");
	}
	public void DeleteUser(int user_id) throws SQLException {
		System.out.println("DeleteUser      UserDao");
		String sql ="DELETE FROM diploma.user WHERE user_id="+user_id;
		DBUtil db=new DBUtil();
		Connection conn =db.getConnection();
		Statement stmt = conn.createStatement();
		int rows = stmt.executeUpdate(sql);
		System.out.println("Success    DeleteUser");
	}
	public User findUserByLoginAndUpassword(String user_login, String user_password){
		User user=null;
		System.out.println("findUserByLoginandUpassword      UserDao");
		try {
			String sql = "";
			sql = "SELECT * FROM diploma.user where login = ? and pwd = ?";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_login);
			pstmt.setString(2,user_password);
			ResultSet rs = pstmt.executeQuery();
		   if(rs.next()) {
			   user=new User();
			   user.setUser_id(rs.getInt("user_id"));
			   user.setUser_login(rs.getString("login"));
			   user.setUser_name(rs.getString("name"));
			   user.setUser_gender(rs.getInt("gender"));
			   user.setUser_dob(rs.getDate("dob"));
			   user.setUser_question(rs.getString("question"));
			   user.setUser_answer(rs.getString("answer"));
			   user.setUser_about(rs.getString("about"));
			   user.setUser_password(rs.getString("pwd"));
			   user.setUser_photopath(rs.getString("photopath"));
		   	}
		   db.close(rs, pstmt, conn);
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}	
		return user;
	}
	
	public User findUserByulogin(String user_login){
		System.out.println("findUserByulogin      UserDao");
		User user=null;
		try {
			String sql = "";
			sql = "SELECT * FROM diploma.user where user.login like ?";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_login);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_login(rs.getString("login"));
				user.setUser_name(rs.getString("name"));
				user.setUser_gender(rs.getInt("gender"));
				user.setUser_dob(rs.getDate("dob"));
				user.setUser_question(rs.getString("question"));
				user.setUser_answer(rs.getString("answer"));
				user.setUser_about(rs.getString("about"));
				user.setUser_password(rs.getString("pwd"));
				user.setUser_peer(rs.getString("peer"));
				user.setUser_photopath(rs.getString("photopath"));
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return user;
	}
	public List<FriendInfo> findUserByLoginORNamePart(String s, int id){
		System.out.println("findUserByLoginORNamePart      UserDao");
		FriendInfo user=null;
		List<FriendInfo> lu = new ArrayList<>();
		try {
			String sql = "";
			sql = "SELECT * FROM diploma.user where (name like ? or login like ?) and user_id not in(select user1_id from diploma.friends where user2_id = ? union select user2_id from diploma.friends where user1_id = ?)";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,'%'+s+'%');
			pstmt.setString(2,'%'+s+'%');
			pstmt.setInt(3,id);
			pstmt.setInt(4,id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				user=new FriendInfo();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_login(rs.getString("login"));
				user.setUser_name(rs.getString("name"));
				user.setUser_gender(rs.getInt("gender"));
				user.setUser_dob(rs.getDate("dob"));
				user.setUser_about(rs.getString("about"));
				user.setPhotopath(rs.getString("photopath"));
				lu.add(user);
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return lu;
	}

	public String findNameAndPeerByID(int id){
		System.out.println("findNameByID      UserDao");
		String name=null;
		try {
			String sql = "";
			sql = "SELECT name, peer FROM diploma.user where user_id = ?";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				name=rs.getString("name");
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return name;
	}

	public String findLoginByID(int id){
		System.out.println("findLoginByID      UserDao");
		String login= null;
		try {
			String sql = "";
			sql = "SELECT login FROM diploma.user where user_id = ?";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				login=rs.getString("login");
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return login;
	}

	public User findUserByID(int user_id){
		System.out.println("findUserByID      UserDao");
		User user=null;
		try {
			String sql = "";
			sql = "SELECT * FROM diploma.user where user_id = ?";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,user_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_login(rs.getString("login"));
				user.setUser_name(rs.getString("name"));
				user.setUser_gender(rs.getInt("gender"));
				user.setUser_dob(rs.getDate("dob"));
				user.setUser_question(rs.getString("question"));
				user.setUser_answer(rs.getString("answer"));
				user.setUser_about(rs.getString("about"));
				user.setUser_password(rs.getString("pwd"));
				user.setUser_peer(rs.getString("peer"));
				user.setUser_photopath(rs.getString("photopath"));
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return user;
	}

	public void UpdateUserInfo(int user_id, String user_name, int gender, String user_about){
		System.out.println("UpdateUserInfo      UserDao");
		try {
			String sql ="update diploma.user set user.name = '"+user_name+"', user.gender = "+gender+", user.about='"+user_about+"' where user.user_id = "+user_id;
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			Statement stmt = conn.createStatement();
			int rows = stmt.executeUpdate(sql);
			System.out.println("Success    UpdateUserInfo");
		}catch (Exception ex) {
			System.out.println("ERROR    UpdateUserInfo   " + ex.getMessage());
		}
	}

	public void UpdateUserPassword(String login, String user_password){
		System.out.println("UpdateUserInfo      UserDao");
		try {
			String sql ="update diploma.user set user.pwd = '"+user_password+"'where user.login = '"+login+"';";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			Statement stmt = conn.createStatement();
			int rows = stmt.executeUpdate(sql);
			System.out.println("Success    UpdateUserPassword");
		}catch (Exception ex) {
			System.out.println("ERROR    UpdateUserPassword   " + ex.getMessage());
		}
	}
	public void UpdateUserPhotoPath(String photopath, int user_id){
		System.out.println("UpdateUserInfo      UserDao");
		try {
			String sql ="update diploma.user set user.photopath = '"+photopath+"'where user.user_id = '"+user_id+"';";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			Statement stmt = conn.createStatement();
			int rows = stmt.executeUpdate(sql);
			System.out.println("Success    UpdateUserPassword");
		}catch (Exception ex) {
			System.out.println("ERROR    UpdateUserPassword   " + ex.getMessage());
		}
	}

	public String FindPeerByRequest(int req_id){
		System.out.println("FindPeerByRequest      UserDao");
		String peer= null;
		try {
			String sql = "";
			sql = "SELECT user.peer from diploma.user, diploma.request where user.user_id=request.user_id and request_id=?";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,req_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				peer=rs.getString("peer");
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return peer;
	}
	public int CountUserReq(int user_id){
		System.out.println("CountUserReq      UserDao");
		int countReq=0;
		try {
			String sql = "";
			sql = "SELECT COUNT(request.request_id) FROM diploma.request WHERE user_id = ?;";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,user_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				countReq=rs.getInt("COUNT(request.request_id)");
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return countReq;
	}
	public int CountUserRes(int user_id){
		System.out.println("CountUserRes      UserDao");
		int countRes=0;
		try {
			String sql = "";
			sql = "SELECT COUNT(response.response_id) FROM diploma.response WHERE user_id = ?;";
			DBUtil db=new DBUtil();
			Connection conn =db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,user_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				countRes=rs.getInt("COUNT(response.response_id)");
			}
			db.close(rs, pstmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return countRes;
	}
}