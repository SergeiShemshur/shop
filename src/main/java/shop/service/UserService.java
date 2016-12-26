package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.db.dao.UserDao;
import shop.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getSavedUser(String name, String phone) {
        User user = getUserByPhone(phone);
        if (user == null) {
            user = new User(name, phone);
            saveUser(user);
            user = getUserByPhone(user.getPhone());
        }
        return user;
    }

    public User getUserByPhone(String phone) {
        List<User> all = userDao.findAll();
        for (User user : all) {
            if (user.getPhone().equals(phone))
                return user;
        }
        return null;
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

}
