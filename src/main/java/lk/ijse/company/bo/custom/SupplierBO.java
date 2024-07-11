package lk.ijse.company.bo.custom;

import lk.ijse.company.bo.SuperBO;
import lk.ijse.company.dto.PermenentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public ArrayList<PermenentDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public boolean deleteSuppliers(String code) throws SQLException, ClassNotFoundException ;

    public boolean saveSuppliers(PermenentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateSuppliers(PermenentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existSuppliers(String code) throws SQLException, ClassNotFoundException ;

    public String generateNewCode() throws SQLException, ClassNotFoundException ;
}
