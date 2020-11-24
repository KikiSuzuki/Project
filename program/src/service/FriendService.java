package service;

import dao.FriendDao;
import po.FriendInfo;

import java.sql.SQLException;
import java.util.List;

public class FriendService {
    FriendDao fd = new FriendDao();
    public List<FriendInfo> FindMyFriendsInfoByID(int user_id){
        return fd.findAllFriendIDbyID(user_id);
    }
    public List<FriendInfo> findAllFriendReqIDbyID(int user_id){
        return fd.findAllFriendReqIDbyID(user_id);
    }

    public boolean DeleteFriend(int user1_id, int user2_id){
        try {
            fd.DeleteFriend(user1_id, user2_id);
            return true;
        }catch (SQLException e){
            return false;
        }
    }
    public boolean AddFriend(int user1_id, int user2_id){
        try {
            fd.AddFriend(user1_id, user2_id);
            return true;
        }catch (SQLException e){
            return false;
        }
    }
    public boolean UpdateFriendStatus(int user1_id, int user2_id){
        try {
            fd.UpdateFriendStatus(user1_id, user2_id);
            return true;
        }catch (SQLException e){
            return false;
        }
    }
}
