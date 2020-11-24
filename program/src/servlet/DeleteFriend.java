package servlet;

import service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteFriend")
public class DeleteFriend extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteFriend:"+req.getParameter("user1_id"));
        System.out.println("DeleteFriend:"+req.getParameter("user2_id"));
        FriendService fs = new FriendService();
        if(fs.DeleteFriend(Integer.parseInt(req.getParameter("user1_id")), Integer.parseInt(req.getParameter("user2_id")))){
            resp.getWriter().print("Success");
        }else{
            resp.getWriter().print("Error");
        }

    }
}
