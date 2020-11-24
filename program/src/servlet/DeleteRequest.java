package servlet;

import service.RequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteRequest")
public class DeleteRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long mills = Long.parseLong(req.getParameter("time"));
        RequestService rs = new RequestService();
        if(rs.DeleteRequest(Integer.parseInt(req.getParameter("user_id")), mills)){
            resp.getWriter().print("Success");
        }else{
            resp.getWriter().print("Fail");
        }
    }
}
