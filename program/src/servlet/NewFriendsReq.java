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

@WebServlet("/NewFriendsReq")
public class NewFriendsReq extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("NewFriendsReq:"+req.getParameter("user_id"));
        Gson gson = new Gson();
        FriendService fs = new FriendService();
        System.out.println(req.getParameter("user_id"));
        List<FriendInfo> myfriendsreq = fs.findAllFriendReqIDbyID(Integer.parseInt(req.getParameter("user_id")));
        System.out.println(myfriendsreq);
        resp.getWriter().print(gson.toJson(myfriendsreq));
    }
}
