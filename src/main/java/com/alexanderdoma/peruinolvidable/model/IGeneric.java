package com.alexanderdoma.peruinolvidable.model;

import java.sql.ResultSet;
import java.util.List;

public interface IGeneric <T, K> {
    void add(T o) throws DAOException;
    T update(T o) throws DAOException;
    void delete(int id) throws DAOException;
    List<T> getAll() throws DAOException;
    T getById(int id) throws DAOException;
    T getObject(ResultSet rs) throws DAOException;
}
