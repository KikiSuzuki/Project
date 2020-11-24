package servlet;

import com.google.gson.Gson;
import po.FriendInfo;
import service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MyFriends")
public class MyFriends extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        FriendService fs = new FriendService();
        System.out.println(req.getParameter("user_id"));
        List<FriendInfo> myfriends = fs.FindMyFriendsInfoByID(Integer.parseInt(req.getParameter("user_id")));
        System.out.println(myfriends);
        resp.getWriter().print(gson.toJson(myfriends));
    }
}
