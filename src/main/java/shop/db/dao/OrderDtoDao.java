package shop.db.dao;


import org.springframework.stereotype.Repository;
import shop.db.DataSource;
import shop.model.OrderItem;
import shop.model.Product;
import shop.model.User;
import shop.service.OrderDto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

//TODO change name
@Repository
public class OrderDtoDao {
    private DataSource dataSource = DataSource.getDataSource();


    public Map<Integer, OrderDto> findAll() {
        Connection connection = dataSource.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT\n" +
                    "  orders.id,\n" +
                    "  product.model,\n" +
                    "  order_product.amount,\n" +
                    "  product.price,\n" +
                    "  orders.status,\n" +
                    "  product.category_id,\n" +
                    "  user.name,\n" +
                    "  user.phone,\n" +
                    "  orders.purchase_time\n" +
                    "FROM order_product\n" +
                    "  JOIN product ON product.id = order_product.product_id\n" +
                    "  JOIN orders ON orders.id = order_product.order_id\n" +
                    "  JOIN user ON user.id = orders.user_id\n" +
                    "ORDER BY id;");

            Map<Integer, OrderDto> entities = new LinkedHashMap<>();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                if (!entities.containsKey(orderId)) {
                    entities.put(orderId, createOrderDto(rs));
                } else {
                    entities.get(orderId).addOrderItem(getOrderItem(rs));
                }
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private OrderDto createOrderDto(ResultSet rs) throws SQLException {
        OrderDto orderDto = new OrderDto(getUser(rs), rs.getString("status"));
        orderDto.addOrderItem(getOrderItem(rs));
        orderDto.setPurchaseTime(getPuchaseTime(rs));
        return orderDto;
    }

    private User getUser(ResultSet rs) throws SQLException {
        return new User(rs.getString("name"), rs.getString("phone"));
    }


    private OrderItem getOrderItem(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setCategory(rs.getInt("category_id"));
        product.setModelName(rs.getString("model"));
        product.setPrice(new BigDecimal(rs.getDouble("price")));
        return new OrderItem(product, new BigDecimal(rs.getInt("amount")));
    }

    private String getPuchaseTime(ResultSet rs) throws SQLException {
        String purchaseTime = rs.getString("purchase_time");
       return purchaseTime.substring(0, purchaseTime.indexOf("."));
    }

}
