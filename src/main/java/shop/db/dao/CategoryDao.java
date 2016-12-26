package shop.db.dao;

import org.springframework.stereotype.Repository;
import shop.db.AbstractDao;
import shop.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDao extends AbstractDao<Category> {

    @Override
    protected String prepareInsertQuery(Category category) {
        return "insert into category(id,title) values(" + category.getId() + ",'" + category.getTitle() + "')";
    }

    @Override
    protected Category getById(Statement statement, int id) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * from category where id = " + id);
        if(resultSet.next()){
            return getCategory(resultSet);
        }
            resultSet.close();
        return null;
    }

    @Override
    protected List<Category> getAllEntity(Statement statement) throws SQLException {
        List<Category> categories = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * from category");
        while (resultSet.next()){
            categories.add(getCategory(resultSet));
        }
        return categories;
    }

    @Override
    protected String getUpdateQuery(Category update) {
        return  "update category set title = '" + update.getTitle() + "' where id = " + update.getId();
    }

    @Override
    protected String getDeleteQuery(int id) {
        return "delete category where id = " + id;
    }

    private Category getCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setTitle(resultSet.getString("title"));
        category.setImageUrl(resultSet.getString("imageUrl"));
        return category;
    }

    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();
        System.out.println(dao.findById(2));

    }

}
