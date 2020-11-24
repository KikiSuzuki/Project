package servlet;

import po.User;
import service.UserService;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService us = new UserService();
        System.out.println("Registration..GET");
        if(us.checktoexist(req.getParameter("login"))){
            resp.getWriter().print("This login is already exists");
        }else{
            User user = new User();
            user.setUser_login(req.getParameter("login"));
            user.setUser_name(req.getParameter("name"));
            user.setUser_gender(Integer.parseInt(req.getParameter("gender")));
            DateUtil du = new DateUtil();
            user.setUser_dob(du.FromStringToDate(req.getParameter("dob")));
            user.setUser_question(req.getParameter("question"));
            user.setUser_answer(req.getParameter("answer"));
            user.setUser_password(req.getParameter("pwd"));
            user.setUser_about(req.getParameter("about"));
            user.setUser_photopath(req.getParameter("photo"));
            if(us.checktosaving(user)){
                resp.getWriter().print("Success");
            }else{
                resp.getWriter().print("Error");
            }
        }
    }
}
