package shop.db.dao;

import org.springframework.stereotype.Repository;
import shop.db.DataSource;
import shop.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class OrderDao {
    private DataSource dataSource = DataSource.getDataSource();

    public long saveAndGetId(Order order) {
        Connection connection = dataSource.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert INTO orders(user_id, status) VALUE ("+order.getUserId()+ ", 'new')", Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Can't insert into DB");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Can't close ");
            }
        }
        return Long.MAX_VALUE;
    }


    public void setOrderStatusDone(int id){
        Connection connection = dataSource.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE orders set status='done' where id="+id);
        } catch (SQLException e) {
            System.out.println("Can't insert into DB");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Can't close ");
            }
        }
    }
}
