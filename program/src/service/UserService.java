package service;

import com.google.gson.JsonObject;
import dao.UserDao;
import po.FriendInfo;
import po.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
	UserDao ud = new UserDao();

	 public User UpdateUserInfo(int user_id, String user_name, int gender, String user_about){
		ud.UpdateUserInfo(user_id, user_name, gender,user_about);
		User user = ud.findUserByID(user_id);
		return user;
	 }
	 public void DeleteUser(int id) throws SQLException {
	 	ud.DeleteUser(id);
	 }

	 public List<FriendInfo> FindNewFriends(String s, int id){
	 	return ud.findUserByLoginORNamePart(s, id);
	 }

	public User findUserByLoginAndPwd(String login, String pwd){
		System.out.println("findUserByLoginAndPwd    UserService");
		return ud.findUserByLoginAndUpassword(login, pwd) ;
	}

	public String findLoginByID(int id){
		return ud.findLoginByID(id);
	}

	public void UpdateUserPassword(String login, String pwd){
		ud.UpdateUserPassword(login,pwd);
	}

	public User findUserByLogin(String login) {
		User user=ud.findUserByulogin(login);
		if(user!=null) {
            System.out.println("USERSERVICE      findUserByLogin  " + user.getUser_login());
        }
		return user;
	}

	public boolean checktoexist(String ulogin) {
		boolean k = false;
		User user = ud.findUserByulogin(ulogin);

		System.out.println(user);
		if(user==null) {
			k= false;
		}
		if(user!=null) {
			k= true;
		}
		return k;
	}

	public boolean checktosaving(User user) {
		try {
				ud.SaveNewUser(user);
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void SaveUserPeer(String peer, int id) throws SQLException {
	 	ud.SaveUserPeer(peer,id);
	}
	public void DeleteUserPeer(String peer) {
	 	try{
			ud.DeleteUserPeer(peer);
		}catch (SQLException ex){

		}
	}
	public String findNameByID(int id){return ud.findNameAndPeerByID(id);}
	public void SaveUserPhoto(String photopath,int id){
	 	ud.UpdateUserPhotoPath(photopath, id);
	}

	public User findUserById(int id){
	 	return ud.findUserByID(id);
	}
	public String FindPeerByRequest(int req_id){
	 	return ud.FindPeerByRequest(req_id);
	}
	public JsonObject Statistics(int user_id){
	 	JsonObject jo = new JsonObject();
	 	jo.addProperty("req_stat", ud.CountUserReq(user_id));
		jo.addProperty("res_stat", ud.CountUserRes(user_id));
	 	return jo;
	}
}