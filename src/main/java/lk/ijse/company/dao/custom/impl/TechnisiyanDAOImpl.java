package lk.ijse.company.dao.custom.impl;

import lk.ijse.company.dao.custom.TechnisiyanDAO;
import lk.ijse.company.entity.Technisiyan;

import java.sql.SQLException;
import java.util.ArrayList;

public class TechnisiyanDAOImpl implements TechnisiyanDAO {
    @Override
    public ArrayList<Technisiyan> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Technisiyan entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Technisiyan entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Technisiyan search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
