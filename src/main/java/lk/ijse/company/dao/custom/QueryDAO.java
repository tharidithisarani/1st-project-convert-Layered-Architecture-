package lk.ijse.company.dao.custom;

import lk.ijse.company.dao.SuperDAO;
import lk.ijse.company.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> searchOrder(String oid) throws SQLException, ClassNotFoundException;
}
