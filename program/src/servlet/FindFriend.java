package servlet;

import com.google.gson.Gson;
import po.FriendInfo;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FindFriend")
public class FindFriend extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        UserService us = new UserService();
        if(!req.getParameter("loginORname").equals("")) {
            System.out.println(req.getParameter("loginORname"));
            System.out.println(Integer.parseInt(req.getParameter("user_id")));
            List<FriendInfo> lu = us.FindNewFriends(req.getParameter("loginORname"), Integer.parseInt(req.getParameter("user_id")));
            System.out.println(lu);
            resp.getWriter().print(gson.toJson(lu));
        }
    }
}
