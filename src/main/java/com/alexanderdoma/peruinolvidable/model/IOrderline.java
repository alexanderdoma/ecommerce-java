package com.alexanderdoma.peruinolvidable.model;

import com.alexanderdoma.peruinolvidable.model.entity.Orderline;
import java.util.List;

public interface IOrderline extends IGeneric<Orderline, Integer>{
    List<Orderline> getOrderlineByOrder(int order_id) throws DAOException;
}
