package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateUserInfo")
public class UpdateUserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UpdateUserInfo:"+req.getParameter("user_id"));
        UserService us = new UserService();

        Gson gson = new Gson();
        String json =gson.toJson(us.UpdateUserInfo(Integer.parseInt(req.getParameter("user_id")), req.getParameter("user_name"),Integer.parseInt(req.getParameter("user_gender")), req.getParameter("user_about")));
        JsonParser parser = new JsonParser();
        JsonObject jo = (JsonObject) parser.parse(json);
        jo.remove("user_password");
        jo.remove("user_question");
        jo.remove("user_answer");
        resp.getWriter().print(gson.toJson(jo));

    }
}