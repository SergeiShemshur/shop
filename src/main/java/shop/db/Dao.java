package shop.db;

import java.util.List;

public interface Dao<T> {

    T findById(int id);

    List<T> findAll();

    void saveOrUpdate(int id, T update) throws Exception;

    void delete(int id);

    void save(T obj) throws Exception ;

}
