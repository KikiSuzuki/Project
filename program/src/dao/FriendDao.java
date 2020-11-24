package dao;

import po.FriendInfo;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendDao {
    BaseDao bd=new BaseDao();

    public List<FriendInfo> findAllFriendIDbyID(int user1_id){
        List<FriendInfo> myfriends = new ArrayList<>();
        FriendInfo fi =  null;
        int i = 0;
        try {
            String sql = "Select user.* from diploma.user where user_id in (Select friends.user1_id from diploma.friends where user2_id = ? and friends.status = ? \n" +
                    "union\n" +
                    "Select friends.user2_id from diploma.friends where user1_id=? and friends.status = ?)";
            DBUtil db=new DBUtil();
            Connection conn =db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user1_id);
            pstmt.setInt(2,1);
            pstmt.setInt(3,user1_id);
            pstmt.setInt(4,1);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                fi = new FriendInfo();
                fi.setUser_id(rs.getInt("user_id"));
                fi.setUser_login(rs.getString("login"));
                fi.setUser_name(rs.getString("name"));
                fi.setUser_gender(rs.getInt("gender"));
                fi.setUser_dob(rs.getDate("dob"));
                fi.setUser_about(rs.getString("about"));
                fi.setPhotopath(rs.getString("photopath"));
                fi.setPeer(rs.getString("peer"));
                myfriends.add(fi);
            }
            db.close(rs, pstmt, conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myfriends;
    }

    public List<FriendInfo> findAllFriendReqIDbyID(int user1_id){
        List<FriendInfo> myfriendsreq = new ArrayList<>();
        FriendInfo fi =  null;
        int i = 0;
        try {
            String sql = "Select user.* from diploma.user where user_id in (Select friends.user1_id from diploma.friends where user2_id=? and friends.status=0)";
            DBUtil db=new DBUtil();
            Connection conn =db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user1_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                fi = new FriendInfo();
                fi.setUser_id(rs.getInt("user_id"));
                fi.setUser_login(rs.getString("login"));
                fi.setUser_name(rs.getString("name"));
                fi.setUser_gender(rs.getInt("gender"));
                fi.setUser_dob(rs.getDate("dob"));
                fi.setUser_about(rs.getString("about"));
                fi.setPhotopath(rs.getString("photopath"));
                fi.setPeer(rs.getString("peer"));
                myfriendsreq.add(fi);
            }
            db.close(rs, pstmt, conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myfriendsreq;
    }
    public void UpdateFriendStatus(int user1_id, int user2_id) throws SQLException{
        System.out.println("UpdateFriendStatus      FriendDao");
        String sql ="update diploma.friends set friends.status = '1' where (friends.user1_id = ? and friends.user2_id = ?) " +
                "or (friends.user1_id = ? and friends.user2_id = ?)";
        List<Object> params=new ArrayList<>();
        params.add(user1_id);
        params.add(user2_id);
        params.add(user2_id);
        params.add(user1_id);
        bd.executeUpdate(sql, params);
        System.out.println("Success  UpdateFriendStatus");
    }
    public void DeleteFriend(int user1_id, int user2_id) throws SQLException{
        System.out.println("DeletePost      FriendDao");
        String sql ="delete from diploma.friends where ( `user1_id` = "+user1_id+" and `user2_id` ="+user2_id+") " +
                "or (`user1_id` ="+user2_id+" and `user2_id` ="+user1_id+");";
        DBUtil db=new DBUtil();
        Connection conn =db.getConnection();
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate(sql);
        System.out.println("Success  Delete  DeleteFriend");
    }
    public void AddFriend(int user1_id, int user2_id) throws SQLException {
        System.out.println("AddFriend      FriendDao");
        List<Object> params=new ArrayList<>();
        String sql= "INSERT INTO `diploma`.`friends` (`user1_id`, `user2_id`, `status`) VALUES (?, ?, 0)";

        params.add(user1_id);
        params.add(user2_id);
        bd.executeUpdate(sql, params);
    }
}
