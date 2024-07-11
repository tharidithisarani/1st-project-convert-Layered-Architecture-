package lk.ijse.company.bo.custom;

import lk.ijse.company.bo.SuperBO;
import lk.ijse.company.dto.TechnisiyanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TechnisiyanBO extends SuperBO {

    ArrayList<TechnisiyanDTO> getAllTechnisiyan() throws SQLException, ClassNotFoundException;

    boolean deleteTechnisiyan(String code) throws SQLException, ClassNotFoundException;

    boolean saveTechnisiyan(TechnisiyanDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateTechnisiyan(TechnisiyanDTO dto) throws SQLException, ClassNotFoundException;

    boolean existTechnisiyan(String code) throws SQLException, ClassNotFoundException;

    public String generateNewCode() throws SQLException, ClassNotFoundException ;
}
