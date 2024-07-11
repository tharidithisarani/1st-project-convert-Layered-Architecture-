package lk.ijse.company.bo.custom.impl;

import lk.ijse.company.bo.custom.TechnisiyanBO;
import lk.ijse.company.dao.DAOFactory;
import lk.ijse.company.dao.custom.TechnisiyanDAO;
import lk.ijse.company.dto.PermenentDTO;
import lk.ijse.company.dto.TechnisiyanDTO;
import lk.ijse.company.entity.Permenent;
import lk.ijse.company.entity.Technisiyan;

import java.sql.SQLException;
import java.util.ArrayList;

public class TechnisiyanBOImpl implements TechnisiyanBO {
    TechnisiyanDAO technisiyanDAO = (TechnisiyanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TECHNISIYAN);

    @Override
    public ArrayList<TechnisiyanDTO> getAllTechnisiyan() throws SQLException, ClassNotFoundException {
        ArrayList<Technisiyan> allEntityData = technisiyanDAO.getAll();
        ArrayList<TechnisiyanDTO> allDTOData= new ArrayList<>();
        for (Technisiyan t : allEntityData) {
            allDTOData.add(new TechnisiyanDTO(t.getCode(), t.getNic(), t.getBankName(), t.getAddress(), t.getContact(), t.getBankName(), t.getAccountNum(), t.getToolCode(), t.getDescription()));
        }
        return allDTOData;
    }

    @Override
    public boolean deleteTechnisiyan(String code) throws SQLException, ClassNotFoundException {
        return technisiyanDAO.delete(code);
    }

    @Override
    public boolean saveTechnisiyan(TechnisiyanDTO dto) throws SQLException, ClassNotFoundException {
        return technisiyanDAO.save(new Technisiyan(dto.getCode(), dto.getNic(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getBankName(), dto.getAccountNum(), dto.getToolCode(), dto.getDescription()));
    }

    @Override
    public boolean updateTechnisiyan(TechnisiyanDTO dto) throws SQLException, ClassNotFoundException {
        return technisiyanDAO.update(new Technisiyan(dto.getCode(), dto.getNic(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getBankName(), dto.getAccountNum(), dto.getToolCode(), dto.getDescription()));
    }

    @Override
    public boolean existTechnisiyan(String code) throws SQLException, ClassNotFoundException {
        return technisiyanDAO.exist(code);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return technisiyanDAO.generateNewID();
    }
}
