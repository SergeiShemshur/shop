package shop.servlet;

import shop.db.dao.UserDao;
import shop.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLWriter;
import java.io.IOException;
import java.util.List;

/*@WebServlet(urlPatterns = "/user")*/
public class UserServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = new UserDao().findById(Integer.valueOf(id));
        resp.getWriter().print("hello  " + user.toString());
    }

}
