package lk.ijse.company.bo.custom.impl;

import lk.ijse.company.bo.custom.MachineBO;
import lk.ijse.company.dao.DAOFactory;
import lk.ijse.company.dao.custom.MachineDAO;
import lk.ijse.company.dao.custom.TechnisiyanDAO;
import lk.ijse.company.dto.PermenentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MachineBOImpl implements MachineBO {
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);

    @Override
    public ArrayList<PermenentDTO> getAllMachine() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteMachine(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveMachine(PermenentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateMachine(PermenentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existMachine(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return null;
    }
}
