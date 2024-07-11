package lk.ijse.company.bo.custom;

import lk.ijse.company.bo.SuperBO;
import lk.ijse.company.dto.PermenentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PermenentBO extends SuperBO {
    public ArrayList<PermenentDTO> getAllPermenentCus() throws SQLException, ClassNotFoundException;

    public boolean deletePermenentCus(String code) throws SQLException, ClassNotFoundException ;

    public boolean savePermenentCus(PermenentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updatePermenentCus(PermenentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existPermenentCus(String code) throws SQLException, ClassNotFoundException ;

    public String generateNewCode() throws SQLException, ClassNotFoundException ;
}
