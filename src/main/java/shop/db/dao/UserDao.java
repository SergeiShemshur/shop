package shop.db.dao;


import org.springframework.stereotype.Repository;
import shop.db.AbstractDao;
import shop.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao extends AbstractDao<User> {

    @Override
    protected String prepareInsertQuery(User user) {
        String sql = "insert into user(name,phone) values('" + user.getName() +"','" + user.getPhone()+"')";
        System.out.println(sql);
        return sql;
    }

    @Override
    protected User getById(Statement statement, int id) throws SQLException {
        User user = null;
        ResultSet resultSet = statement.executeQuery("select * from user where id = " + id);
        if (resultSet.next()) {
            return getUser(resultSet);
        }
        return user;
    }

    @Override
    protected List<User> getAllEntity(Statement statement) throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * from user");
        while (resultSet.next())
            list.add(getUser(resultSet));
        return list;
    }

    @Override
    protected String getUpdateQuery(User update) {
        return  "update user set name = '" + update.getName() + "' where id = " + update.getId();
    }


    @Override
    protected String getDeleteQuery(int id) {
        return "delete user where id = "+id;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPhone(resultSet.getString("phone"));
        return user;
    }
}
