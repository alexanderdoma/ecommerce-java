package com.alexanderdoma.peruinolvidable.model;

import com.alexanderdoma.peruinolvidable.model.entity.Admin;

public interface IAdmin extends IGeneric<Admin, Integer>{
    Admin login(String username, String password) throws DAOException;
}
