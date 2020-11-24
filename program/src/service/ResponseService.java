package service;

import dao.ResponseDao;

import java.sql.SQLException;


public class ResponseService {
    ResponseDao rd = new ResponseDao();
    public boolean SaveNewResponse(int user_id, int req_id) {
        try {
            rd.SaveNewResponse(user_id, req_id);
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
}
