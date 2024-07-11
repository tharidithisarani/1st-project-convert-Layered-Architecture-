package lk.ijse.company.bo.custom.impl;

import lk.ijse.company.bo.custom.TechnisiyanBO;
import lk.ijse.company.dao.DAOFactory;
import lk.ijse.company.dao.custom.TechnisiyanDAO;
import lk.ijse.company.dto.TechnisiyanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TechnisiyanBOImpl implements TechnisiyanBO {
    TechnisiyanDAO technisiyanDAO = (TechnisiyanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TECHNISIYAN);

    @Override
    public ArrayList<TechnisiyanDTO> getAllTechnisiyan() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteTechnisiyan(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveTechnisiyan(TechnisiyanDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateTechnisiyan(TechnisiyanDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existTechnisiyan(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return null;
    }
}
