package shop.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {
    private DataSource dataSource = DataSource.getDataSource();

    public void save(T entity) {
        Connection connection = dataSource.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(prepareInsertQuery(entity));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't insert into DB");
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

    protected abstract String prepareInsertQuery(T obj);

    public T findById(int id) {
        Connection connection = dataSource.getConnection();
        Statement statement = null;
        T obj = null;
        try {
            statement = connection.createStatement();
            obj = getById(statement, id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cant insert into DB");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    protected abstract T getById(Statement statement, int id) throws SQLException;


    public List<T> findAll() {
        Connection connection = dataSource.getConnection();
        Statement statement = null;
        List<T> entities = new ArrayList<>();
        try {
            statement = connection.createStatement();
            entities = getAllEntity(statement);
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
        return entities;
    }


    protected abstract List<T> getAllEntity(Statement statement) throws SQLException;


    public void saveOrUpdate(int id, T entity) throws Exception {
        Connection connection = dataSource.getConnection();
        Statement statement = null;
        if (id <= 0) {
            throw new Exception();
        }
        if (findById(id) == null) {
            save(entity);
        } else {
            try {
                statement = connection.createStatement();
                statement.execute(getUpdateQuery(entity));
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
        }
    }

    protected abstract String getUpdateQuery(T update);


    public void delete(int id) {
        Connection connection = dataSource.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute(getDeleteQuery(id));
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
    }

    protected abstract String getDeleteQuery(int id);
}
