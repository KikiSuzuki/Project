package servlet;

import com.google.gson.Gson;
import po.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindUser")
public class FindUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FindUser:"+req.getParameter("user_login"));
        UserService us = new UserService();

        Gson gson = new Gson();
        User user =us.findUserByLogin(req.getParameter("user_login"));
        if(user!=null){
            resp.getWriter().println(gson.toJson(user));
        }
    }
}
