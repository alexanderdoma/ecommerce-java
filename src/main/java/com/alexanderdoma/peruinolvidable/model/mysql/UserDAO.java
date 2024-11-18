package com.alexanderdoma.peruinolvidable.model.mysql;

import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.config.Connector;
import com.alexanderdoma.peruinolvidable.model.IUser;
import com.alexanderdoma.peruinolvidable.model.entity.User;
import com.alexanderdoma.peruinolvidable.utilies.SQLSentencesManager;
import com.alexanderdoma.peruinolvidable.utilies.MessagesManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUser{

    private final String GETALL = SQLSentencesManager.getProperty("USER.GETALL");
    private final String INSERT = SQLSentencesManager.getProperty("USER.INSERT");
    private final String UPDATE = SQLSentencesManager.getProperty("USER.UPDATE");
    private final String DELETE = SQLSentencesManager.getProperty("USER.DELETE");
    private final String GETBYID = SQLSentencesManager.getProperty("USER.GETBYID");
    private final String VERIFY = SQLSentencesManager.getProperty("USER.VERIFY");

    @Override
    public List<User> getAll() throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        List<User> objUsersList = new ArrayList<>();
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareStatement(GETALL);
            objResultSet = objPreparedStatement.executeQuery();
            while(objResultSet.next()) {
                objUsersList.add(getObject(objResultSet));
            }
            return objUsersList;
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
    public void add(User o) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try (Connection objConnection = Connector.getInstance().getConnection()){
            objPreparedStatement = objConnection.prepareStatement(INSERT);
            int i=1;
            objPreparedStatement.setString(i++, o.getName());
            objPreparedStatement.setString(i++, o.getLastname());
            objPreparedStatement.setString(i++, o.getUsername());
            objPreparedStatement.setString(i++, o.getPassword());
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
    public User update(User o) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try(Connection objConnection = Connector.getInstance().getConnection()){
            objPreparedStatement = objConnection.prepareCall(UPDATE);
            int i=1;
            objPreparedStatement.setString(i++, o.getName());
            objPreparedStatement.setString(i++, o.getLastname());
            objPreparedStatement.setString(i++, o.getUsername());
            objPreparedStatement.setString(i++, o.getPassword());
            objPreparedStatement.setString(i++, o.getEmail());
            objPreparedStatement.setString(i++, o.getDni());
            objPreparedStatement.setString(i++, o.getPhone());
            objPreparedStatement.setDate(i++, o.getBirthdate());
            objPreparedStatement.setString(i++, o.getGender());
            objPreparedStatement.setString(i++, o.getAddress());
            objPreparedStatement.setInt(i++, o.getId());
            objPreparedStatement.executeUpdate();
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
    public User getById(int id) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        User objUser = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareCall(GETBYID);
            objPreparedStatement.setInt(1, id);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objUser = getObject(objResultSet);
            }
            return objUser;
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
    public User login(String username, String password) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        User objUser = null;
        try (Connection objConnection = Connector.getInstance().getConnection();){
            objPreparedStatement = objConnection.prepareStatement(VERIFY);
            int i = 1;
            objPreparedStatement.setString(i++, username);
            objPreparedStatement.setString(i++, password);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()){
                objUser = getObject(objResultSet);
            }
            return objUser;
        } catch (SQLException ex) {
            throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.VERIFY") + " " + ex.getMessage());
        } finally {
            try {
                if(objResultSet != null) objResultSet.close();
                if(objPreparedStatement != null) objPreparedStatement.close();
            } catch (SQLException ex) {
                throw new DAOException(MessagesManager.getProperty("DATABASE.ERROR.VERIFY") + " " + ex.getMessage());
            }
        }
    }
    
    @Override
    public User getObject(ResultSet rs) throws DAOException {
        try {
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("user_lastname"),
                    rs.getString("user_username"),
                    rs.getString("user_password"),
                    rs.getString("user_email"),
                    rs.getString("user_dni"),
                    rs.getString("user_phone"),
                    rs.getDate("user_birthdate"),
                    rs.getString("user_gender"),
                    rs.getString("user_address"),
                    rs.getTimestamp("user_created_at")
            );
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}
