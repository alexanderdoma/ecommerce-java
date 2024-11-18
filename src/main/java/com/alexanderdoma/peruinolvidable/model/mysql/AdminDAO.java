package com.alexanderdoma.peruinolvidable.model.mysql;

import com.alexanderdoma.peruinolvidable.config.Connector;
import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.IAdmin;
import com.alexanderdoma.peruinolvidable.model.entity.Admin;
import com.alexanderdoma.peruinolvidable.utilies.MessagesManager;
import com.alexanderdoma.peruinolvidable.utilies.SQLSentencesManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO implements IAdmin{

    private final String VERIFY = SQLSentencesManager.getProperty("ADMIN.VERIFY");
    
    @Override
    public void add(Admin o) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Admin update(Admin o) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Admin> getAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Admin getById(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Admin getObject(ResultSet rs) throws DAOException {
        try {
            return new Admin(
                    rs.getInt("admin_id"),
                    rs.getString("admin_user"),
                    rs.getString("admin_password"),
                    rs.getTimestamp("admin_created_at")
            );
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public Admin login(String username, String password) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        Admin objAdmin = null;
        try (Connection objConnection = Connector.getInstance().getConnection();){
            objPreparedStatement = objConnection.prepareStatement(VERIFY);
            int i = 1;
            objPreparedStatement.setString(i++, username);
            objPreparedStatement.setString(i++, password);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()){
                objAdmin = getObject(objResultSet);
            }
            return objAdmin;
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
}
