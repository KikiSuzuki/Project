package service;
import dao.RequestDao;
import po.Request;

import java.sql.SQLException;

public class RequestService {
    RequestDao rd = new RequestDao();
    public boolean SaveNewRequest(Request req){
        try{
            rd.SaveNewRequest(req);
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }

    public Request FindRequestByUserId(int id) throws SQLException {
        return rd.FindRequestByUserId(id);
    }
    public Request FindRequestByReqId(int id) throws SQLException {
        return rd.FindRequestByReqId(id);
    }

    public boolean UpdateRequestStatus(int req_id){
        try{
            rd.UpdateRequestStatus(req_id);
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }
    public boolean DeleteRequest(int user_id, long time){
        try{
            rd.DeleteRequest(user_id, time);
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }
}
