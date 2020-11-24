package servlet;

import com.google.gson.Gson;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteUser:"+req.getParameter("user_id"));
        UserService us = new UserService();
        Gson gson = new Gson();

        try {
            us.DeleteUser(Integer.parseInt(req.getParameter("user_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
