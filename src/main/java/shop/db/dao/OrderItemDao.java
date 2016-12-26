package shop.db.dao;

import shop.db.AbstractDao;
import shop.db.Dao;
import shop.model.OrderItem;
import shop.model.Product;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao extends AbstractDao<OrderItem> {
    Dao<Product> productDao = new ProductDao();

    @Override
    protected String prepareInsertQuery(OrderItem orderItem) {
        return "insert into order_product(order_id, product_id, amount) " +
                "values(" + orderItem.getOrderId() + "," + orderItem.getProduct().getId() + "," + orderItem.getAmount() + ")";
    }

    @Override
    protected OrderItem getById(Statement statement, int id) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * from order_product WHERE id = " + id);
        if (resultSet.next())
            return getOrderItem(resultSet);
        return null;
    }

    @Override
    protected List<OrderItem> getAllEntity(Statement statement) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * from order_product");
        while (resultSet.next())
            orderItems.add(getOrderItem(resultSet));
        return orderItems;
    }

    @Override
    protected String getUpdateQuery(OrderItem update) {
        return "update order_product set " + "order_id = " + update.getOrderId() +
                ",product_id = '" + update.getProduct().getId() +
                "',amount = " + update.getAmount().doubleValue() +
                " where id = " + update.getId();
    }

    @Override
    protected String getDeleteQuery(int id) {
        return "DELETE FROM order_product\n" +
                "WHERE id = " + id;
    }

    private OrderItem getOrderItem(ResultSet resultSet) throws SQLException {
        Product product = productDao.findById(resultSet.getInt("product_id"));
        OrderItem orderItem = new OrderItem(product, new BigDecimal(resultSet.getInt("amount")));
        orderItem.setId(resultSet.getInt("id"));
        orderItem.setOrderId(resultSet.getLong("order_id"));
        return orderItem;
    }
}
