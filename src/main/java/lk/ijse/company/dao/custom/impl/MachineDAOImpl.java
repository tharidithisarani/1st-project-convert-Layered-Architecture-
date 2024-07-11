package lk.ijse.company.dao.custom.impl;

import lk.ijse.company.dao.custom.MachineDAO;
import lk.ijse.company.entity.Machine;

import java.sql.SQLException;
import java.util.ArrayList;

public class MachineDAOImpl implements MachineDAO {
    @Override
    public ArrayList<Machine> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Machine entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Machine entity) throws SQLException, ClassNotFoundException {
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
    public Machine search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
