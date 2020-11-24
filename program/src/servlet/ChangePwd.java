package servlet;


import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangePwd")
public class ChangePwd  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ChangePwd:"+req.getParameter("user_pwd"));
        UserService us = new UserService();

        us.UpdateUserPassword(req.getParameter("user_login"), req.getParameter("user_pwd"));

    }
}