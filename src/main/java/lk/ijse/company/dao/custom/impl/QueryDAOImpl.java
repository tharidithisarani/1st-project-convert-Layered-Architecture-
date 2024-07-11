package lk.ijse.company.dao.custom.impl;

import lk.ijse.company.dao.custom.QueryDAO;
import lk.ijse.company.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> searchOrder(String oid) throws SQLException, ClassNotFoundException {
        return null;
    }
}
