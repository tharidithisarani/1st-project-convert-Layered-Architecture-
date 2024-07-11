package lk.ijse.company.bo.custom.impl;

import lk.ijse.company.bo.custom.PermenentBO;
import lk.ijse.company.dao.DAOFactory;
import lk.ijse.company.dao.custom.ItemDAO;
import lk.ijse.company.dao.custom.PermenentDAO;
import lk.ijse.company.dao.custom.impl.PermenentDAOImpl;
import lk.ijse.company.dto.ItemDTO;
import lk.ijse.company.dto.PermenentDTO;
import lk.ijse.company.entity.Item;
import lk.ijse.company.entity.Permenent;

import java.sql.SQLException;
import java.util.ArrayList;

public class PermenentBOImpl implements PermenentBO {
    PermenentDAO permenentDAO = (PermenentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PERMENENT);

    @Override
    public ArrayList<PermenentDTO> getAllPermenentCus() throws SQLException, ClassNotFoundException {
        ArrayList<Permenent> allEntityData = permenentDAO.getAll();
        ArrayList<PermenentDTO> allDTOData= new ArrayList<>();
        for (Permenent p : allEntityData) {
            allDTOData.add(new PermenentDTO(p.getCode(),p.getName(),p.getAddress(),p.getContact(), p.getDescription()));
        }
        return allDTOData;
    }

    @Override
    public boolean deletePermenentCus(String code) throws SQLException, ClassNotFoundException {
        return permenentDAO.delete(code);
    }

    @Override
    public boolean savePermenentCus(PermenentDTO dto) throws SQLException, ClassNotFoundException {
        return permenentDAO.save(new Permenent(dto.getCode(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDescription()));
    }

    @Override
    public boolean updatePermenentCus(PermenentDTO dto) throws SQLException, ClassNotFoundException {
        return permenentDAO.update(new Permenent(dto.getCode(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDescription()));
    }

    @Override
    public boolean existPermenentCus(String code) throws SQLException, ClassNotFoundException {
        return permenentDAO.exist(code);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return permenentDAO.generateNewID();
    }
}
