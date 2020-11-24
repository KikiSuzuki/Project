package servlet;

import service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ConfirmFriend")
public class ConfirmFriend extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ConfirmFriend:"+req.getParameter("user1_id"));
        System.out.println("ConfirmFriend:"+req.getParameter("user2_id"));
        FriendService fs = new FriendService();
        if(fs.UpdateFriendStatus(Integer.parseInt(req.getParameter("user1_id")), Integer.parseInt(req.getParameter("user2_id")))){
            resp.getWriter().print("Success");
        }else{
            resp.getWriter().print("Error");
        }
    }
}
