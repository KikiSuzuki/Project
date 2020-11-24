package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import po.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		UserService us = new UserService();
		System.out.println(request.getParameter("login")+" "+request.getParameter("pwd"));
		User user = us.findUserByLoginAndPwd(request.getParameter("login"), request.getParameter("pwd"));
		if (user != null) {
			us.SaveUserPhoto(request.getParameter("photopath"), user.getUser_id());
			String json =gson.toJson(user);
			JsonParser parser = new JsonParser();
			JsonObject jo = (JsonObject) parser.parse(json);
			jo.remove("user_password");
			jo.remove("user_question");
			jo.remove("user_answer");
			response.getWriter().print(gson.toJson(jo));
		} else {
			response.getWriter().print("User not found");
		}
	}
}