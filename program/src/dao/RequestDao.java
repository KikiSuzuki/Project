package dao;

import po.Request;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    BaseDao bd=new BaseDao();

    public void SaveNewRequest(Request request) throws SQLException {
        System.out.println("SaveNewRequest      RequestDao");
        List<Object> params=new ArrayList<>();
        String sql= "INSERT INTO `diploma`.`request` (`user_id`, `lvl`," +
                "`txt`, `longitude`,`latitude`, `time`, `status`) VALUES (?, ?, ?, ?, ?, ?, 0 );";

        params.add(request.getUser_id());
        params.add(request.getLvl());
        params.add(request.getTxt());
        params.add(request.getLongitude());
        params.add(request.getLatitude());
        params.add(request.getTime());
        bd.executeUpdate(sql, params);
    }
    public Request FindRequestByUserId(int user_id)throws SQLException{
        System.out.println("FindRequestByUserId      RequesrDao");
        Request req = null;
        try {
            String sql = "";
            sql = "SELECT * FROM diploma.request where user_id = ? and status=0";
            DBUtil db=new DBUtil();
            Connection conn =db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                req = new Request();
                req.setRequest_id(rs.getInt("request_id"));
                req.setUser_id(rs.getInt("user_id"));
                req.setLvl(rs.getInt("lvl"));
                req.setTxt(rs.getString("txt"));
                req.setLongitude(rs.getString("longitude"));
                req.setLatitude(rs.getString("latitude"));
                req.setStatus(rs.getInt("status"));
                req.setTime(rs.getLong("time"));
            }
            db.close(rs, pstmt, conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();}
        return req;
    }

    public Request FindRequestByReqId(int req_id)throws SQLException{
        System.out.println("FindRequestByUserId      RequesrDao");
        Request req = null;
        try {
            String sql = "";
            sql = "SELECT * FROM diploma.request where req_id = ?";
            DBUtil db=new DBUtil();
            Connection conn =db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,req_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                req = new Request();
                req.setRequest_id(rs.getInt("request_id"));
                req.setUser_id(rs.getInt("user_id"));
                req.setLvl(rs.getInt("lvl"));
                req.setTxt(rs.getString("txt"));
                req.setLongitude(rs.getString("longitude"));
                req.setLatitude(rs.getString("latitude"));
                req.setStatus(rs.getInt("status"));
                req.setTime(rs.getLong("time"));
            }
            db.close(rs, pstmt, conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();}
        return req;
    }

    public void UpdateRequestStatus(int request_id) throws SQLException {
        System.out.println("UpdateRequestStatus      RequestDao");
        String sql = "update diploma.request set request.status = 1 where request.request_id = '" + request_id + "';";
        DBUtil db = new DBUtil();
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate(sql);
        System.out.println("Success    UpdateUserPassword");
    }

    public void DeleteRequest(int user_id, long time) throws SQLException {
        System.out.println("DeleteRequest      RequestDao");
        String sql= "DELETE FROM diploma.request WHERE (user_id = "+user_id+" and time = "+time+");";
        bd.executeUpdate(sql, null);
    }
}
