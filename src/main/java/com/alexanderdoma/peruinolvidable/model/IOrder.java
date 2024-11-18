package com.alexanderdoma.peruinolvidable.model;

import com.alexanderdoma.peruinolvidable.model.entity.Order;
import com.alexanderdoma.peruinolvidable.model.entity.Orderline;
import java.util.List;

public interface IOrder extends IGeneric<Order, Integer> {
    void add(Order objOrder, List<Orderline> orderlineList) throws DAOException;
    List<Order> getOrdersByUser(int user_id) throws DAOException;
    void updateOrderState(String status, int order_id) throws DAOException;
}
