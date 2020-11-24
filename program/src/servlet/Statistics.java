package servlet;

import com.google.gson.Gson;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Statistics")
public class Statistics  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Statistics:"+req.getParameter("user_id"));
        UserService us = new UserService();

        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson(us.Statistics(Integer.parseInt(req.getParameter("user_id")))));

    }
}
