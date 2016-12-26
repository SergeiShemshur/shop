package shop.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.db.AbstractDao;
import shop.model.Product;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao extends AbstractDao<Product> {

    @Override
    protected String prepareInsertQuery(Product product) {
        return "insert into product(id, dao, price, category_id) values('" + product.getId() +","+ product.getModelName()
                + "'," + product.getPrice().doubleValue() + "," + product.getCategory() + ");";
    }

    @Override
    protected Product getById(Statement statement, int id) throws SQLException {
        String sql = "select * from product where id = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
          return getProduct(resultSet);
        }
        return null;
    }

    @Override
    protected List<Product> getAllEntity(Statement statement) throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("Select * from product");
        while (resultSet.next()) {
            products.add(getProduct(resultSet));
        }
        resultSet.close();
        return products;
    }

    @Override
    protected String getUpdateQuery(Product update) {
        return "update product set " + "category_id = " + update.getCategory() + ",model = '" + update.getModelName() +
                "',price = " + update.getPrice().doubleValue() + " where id = " + update.getId();
    }

    @Override
    protected String getDeleteQuery(int id) {
        String sql = "DELETE FROM product\n" +
                "WHERE id = " + id;
        System.out.println(sql);
        return sql;
    }

    public Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setCategory(resultSet.getInt("category_id"));
        product.setModelName(resultSet.getString("model"));
        product.setPrice(new BigDecimal(resultSet.getDouble("price")));
        product.setImageUrl(resultSet.getString("imageUrl"));
        return product;
    }
}
