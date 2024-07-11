package lk.ijse.company.dao.custom.impl;

import lk.ijse.company.dao.SQLUtil;
import lk.ijse.company.dao.custom.OrderDAO;
import lk.ijse.company.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }
}
