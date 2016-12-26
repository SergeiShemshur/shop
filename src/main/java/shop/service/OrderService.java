package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.db.dao.OrderDao;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public void setOrderStatusDone(int id){
        orderDao.setOrderStatusDone(id);
    }
}
