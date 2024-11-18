package com.alexanderdoma.peruinolvidable.model.mysql;

import com.alexanderdoma.peruinolvidable.config.Connector;
import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.ICategory;
import com.alexanderdoma.peruinolvidable.model.entity.Category;
import com.alexanderdoma.peruinolvidable.utilies.MessagesManager;
import com.alexanderdoma.peruinolvidable.utilies.SQLSentencesManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategory{
    private final String GETALL = SQLSentencesManager.getProperty("CATEGORY.GETALL");
    private final String INSERT = SQLSentencesManager.getProperty("CATEGORY.INSERT");
    private final String UPDATE = SQLSentencesManager.getProperty("CATEGORY.UPDATE");
    private final String DELETE = SQLSentencesManager.getProperty("CATEGORY.DELETE");
    private final String GETBYID = SQLSentencesManager.getProperty("CATEGORY.GETBYID");
    
    @Override
    public void add(Category o) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareStatement(INSERT);
            int i=1;
            objPreparedStatement.setString(i++,o.getName());
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
    public Category update(Category o) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareStatement(UPDATE);
            int i=1;
            objPreparedStatement.setString(i++, o.getName());
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
    public List<Category> getAll() throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        List<Category> objCategoriesList = new ArrayList<>();
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareStatement(GETALL);
            objResultSet = objPreparedStatement.executeQuery();
            while(objResultSet.next()) {
                objCategoriesList.add(getObject(objResultSet));
            }
            objConnection.close();
            return objCategoriesList;
        } catch (SQLException ex) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objPreparedStatement != null) objPreparedStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETALL") + ex.getMessage());
            }
        }
    }

    @Override
    public Category getById(int id) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        Category objCategory = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareCall(GETBYID);
            objPreparedStatement.setInt(1, id);
            objResultSet = objPreparedStatement.executeQuery();
            while(objResultSet.next()){
                objCategory = getObject(objResultSet);    
            }
            objConnection.close();
            return objCategory;
        } catch (SQLException e) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETBYID") + e.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objPreparedStatement != null) objPreparedStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.GETBYID") + ex.getMessage());
            }
        }
    }

    @Override
    public Category getObject(ResultSet rs) throws DAOException {
        try{
            return new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getTimestamp("category_created_at")
            );
        }catch(SQLException ex){
            throw new DAOException(ex.getMessage());
        }
    }
}
