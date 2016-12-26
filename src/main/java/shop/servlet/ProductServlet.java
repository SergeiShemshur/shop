package shop.servlet;

import shop.db.dao.ProductDao;
import shop.model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*@WebServlet(urlPatterns = "/products")*/
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        List<Product> all = dao.findAll();
        StringBuilder sb = new StringBuilder();
        for (Product product : all)
            sb.append(product.toString()).append("\n");
        resp.getWriter().print(sb.toString());
    }
}
