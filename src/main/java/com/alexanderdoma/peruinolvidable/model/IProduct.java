package com.alexanderdoma.peruinolvidable.model;

import com.alexanderdoma.peruinolvidable.model.entity.Product;
import java.util.List;

public interface IProduct extends IGeneric<Product, Integer> {
    List<Product> getActiveProducts() throws DAOException;
    List<Product> getActiveProductsByCategory(int category_id) throws DAOException;
}