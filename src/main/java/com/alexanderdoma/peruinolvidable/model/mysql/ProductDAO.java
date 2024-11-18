package com.alexanderdoma.peruinolvidable.model.mysql;

import com.alexanderdoma.peruinolvidable.config.Connector;
import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.IProduct;
import com.alexanderdoma.peruinolvidable.model.entity.Category;
import com.alexanderdoma.peruinolvidable.model.entity.Product;
import com.alexanderdoma.peruinolvidable.utilies.MessagesManager;
import com.alexanderdoma.peruinolvidable.utilies.SQLSentencesManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProduct {

    private final String GET_ALL = SQLSentencesManager.getProperty("PRODUCT.GETALL");
    private final String GET_ACTIVE_PRODUCTS = SQLSentencesManager.getProperty("PRODUCT.ACTIVE.GETALL");
    private final String INSERT = SQLSentencesManager.getProperty("PRODUCT.INSERT");
    private final String UPDATE = SQLSentencesManager.getProperty("PRODUCT.UPDATE");
    private final String DELETE = SQLSentencesManager.getProperty("PRODUCT.DELETE");
    private final String GET_BY_ID = SQLSentencesManager.getProperty("PRODUCT.GETBYID");
    private final String GET_BY_CATEGORY = SQLSentencesManager.getProperty("PRODUCT.GETBYCATEGORY");
    
    @Override
    public void add(Product o) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareStatement(INSERT);
            int i=1;
            objPreparedStatement.setString(i++, o.getName());
            objPreparedStatement.setString(i++, o.getDescription());
            objPreparedStatement.setDouble(i++, o.getPrice());
            objPreparedStatement.setString(i++, o.getBrand());
            objPreparedStatement.setInt(i++, o.getStock());
            objPreparedStatement.setString(i++, o.getImage());
            objPreparedStatement.setInt(i++, o.getCategory().getId());
            objPreparedStatement.executeUpdate();
            objConnection.close();
        } catch (SQLException e) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.INSERT") + e.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objPreparedStatement != null) objPreparedStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.INSERT") + ex.getMessage());
            }
        }
    }

    @Override
    public Product update(Product o) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareStatement(UPDATE);
            int i=1;
            objPreparedStatement.setString(i++, o.getName());
            objPreparedStatement.setString(i++, o.getDescription());
            objPreparedStatement.setDouble(i++, o.getPrice());
            objPreparedStatement.setString(i++, o.getBrand());
            objPreparedStatement.setInt(i++, o.getStock());
            objPreparedStatement.setString(i++, o.getState());
            objPreparedStatement.setString(i++, o.getImage());
            objPreparedStatement.setInt(i++, o.getCategory().getId());            
            objPreparedStatement.setInt(i++, o.getId());
            objPreparedStatement.executeUpdate();
            objConnection.close();
            return o;
        } catch (SQLException e) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.UPDATE") + e.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objPreparedStatement != null) objPreparedStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.UPDATE") + ex.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareCall(DELETE);
            objPreparedStatement.setInt(1, id);
            objPreparedStatement.executeUpdate();
            objConnection.close();
        } catch (SQLException e) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.DELETE") + e.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objPreparedStatement != null) objPreparedStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.DELETE") + ex.getMessage());
            }
        }
    }

    @Override
    public List<Product> getAll() throws DAOException {
        CallableStatement objCallableStatement = null;
        ResultSet objResultSet = null;
        List<Product> objProductsList = new ArrayList<>();
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objCallableStatement = objConnection.prepareCall(GET_ALL);
            objCallableStatement.execute();
            objResultSet = objCallableStatement.getResultSet();
            while(objResultSet.next()) {
                objProductsList.add(getObject(objResultSet));
            }
            objConnection.close();
            return objProductsList;
        } catch (SQLException ex) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objCallableStatement != null) objCallableStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
            }
        }
    }

    @Override
    public Product getById(int id) throws DAOException {
        CallableStatement objCallableStatement = null;
        ResultSet objResultSet = null;
        Product objProduct = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objCallableStatement = objConnection.prepareCall(GET_BY_ID);
            objCallableStatement.setInt(1, id);
            objCallableStatement.execute();
            objResultSet = objCallableStatement.getResultSet();
            while(objResultSet.next()){
                objProduct = getObject(objResultSet);           
            }
            objConnection.close();
            return objProduct;
        } catch (SQLException e) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETBYID") + e.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objCallableStatement != null) objCallableStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETBYID") + ex.getMessage());
            }
        }
    }


    @Override
    public List<Product> getActiveProducts() throws DAOException {
        CallableStatement objCallableStatement = null;
        ResultSet objResultSet = null;
        List<Product> objProductsList = new ArrayList<>();
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objCallableStatement = objConnection.prepareCall(GET_ACTIVE_PRODUCTS);
            objCallableStatement.execute();
            objResultSet = objCallableStatement.getResultSet();
            while(objResultSet.next()) {
                objProductsList.add(getObject(objResultSet));
            }
            objConnection.close();
            return objProductsList;
        } catch (SQLException ex) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objCallableStatement != null) objCallableStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
            }
        }
    }
    
    @Override
    public List<Product> getActiveProductsByCategory(int category_id) throws DAOException {
        CallableStatement objCallableStatement = null;
        ResultSet objResultSet = null;
        List<Product> objProductsList = new ArrayList<>();
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objCallableStatement = objConnection.prepareCall(GET_BY_CATEGORY);
            objCallableStatement.setInt(1, category_id);
            objCallableStatement.execute();
            objResultSet = objCallableStatement.getResultSet();
            while(objResultSet.next()) {
                objProductsList.add(getObject(objResultSet));
            }
            objConnection.close();
            return objProductsList;
        } catch (SQLException ex) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objCallableStatement != null) objCallableStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
            }
        }
    }
    
    @Override
    public Product getObject(ResultSet rs) throws DAOException {
        try {
            Category objCategory = null; 
            if(rs.getObject("category_id") != null) { objCategory = new CategoryDAO().getObject(rs); }
            return new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("product_description"),
                    rs.getDouble("product_price"),
                    rs.getString("product_brand"),
                    rs.getInt("product_stock"),
                    rs.getString("product_state"),
                    rs.getString("product_image"),
                    rs.getTimestamp("product_created_at"),
                    objCategory
            );
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}
