package com.alexanderdoma.peruinolvidable.model.mysql;

import com.alexanderdoma.peruinolvidable.config.Connector;
import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.IOrderline;
import com.alexanderdoma.peruinolvidable.model.entity.Order;
import com.alexanderdoma.peruinolvidable.model.entity.Orderline;
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

public class OrderlineDAO implements IOrderline{
    
    private final String INSERT = SQLSentencesManager.getProperty("ORDERLINE.INSERT");
    private final String GET_ALL_BY_ORDER = SQLSentencesManager.getProperty("ORDERLINE.ORDER.GETALL");
    
    @Override
    public void add(Orderline o) throws DAOException {
        PreparedStatement objPreparedStatement = null;
        ResultSet objResultSet = null;
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objPreparedStatement = objConnection.prepareStatement(INSERT);
            int i=1;
            objPreparedStatement.setInt(i++, o.getQuantity());
            objPreparedStatement.setDouble(i++, o.getTotal());
            objPreparedStatement.setInt(i++, o.getOrder().getId());
            objPreparedStatement.setInt(i++, o.getProduct().getId());
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
    public Orderline update(Orderline o) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Orderline> getAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Orderline getById(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Orderline getObject(ResultSet rs) throws DAOException {
        try {
            Product objProduct = new ProductDAO().getById(rs.getInt("product_id"));
            Order objOrder = new OrderDAO().getById(rs.getInt("order_id"));
            return new Orderline(
                    rs.getInt("orderline_id"),
                    rs.getInt("orderline_quantity"),
                    rs.getDouble("orderline_total"),
                    rs.getTimestamp("orderline_created_at"),
                    objOrder,
                    objProduct
            );
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<Orderline> getOrderlineByOrder(int order_id) throws DAOException {
       CallableStatement objCallableStatement = null;
        ResultSet objResultSet = null;
        List<Orderline> objOrderlineList = new ArrayList<>();
        try(Connection objConnection = Connector.getInstance().getConnection()) {
            objCallableStatement = objConnection.prepareCall(GET_ALL_BY_ORDER);
            objCallableStatement.setInt(1, order_id);
            objCallableStatement.execute();
            objResultSet = objCallableStatement.getResultSet();
            while(objResultSet.next()) {
                objOrderlineList.add(getObject(objResultSet));
            }
            objConnection.close();
            return objOrderlineList;
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
}
