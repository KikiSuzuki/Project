package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponseDao {
    BaseDao bd=new BaseDao();

    public void SaveNewResponse(int user_id, int request_id) throws SQLException {
        System.out.println("SaveNewResponse      ResponseDao");
        List<Object> params=new ArrayList<>();
        String sql= "INSERT INTO `diploma`.`response` (`user_id`, `request_id`) VALUES (?, ?);";

        params.add(user_id);
        params.add(request_id);
        bd.executeUpdate(sql, params);
    }
}
