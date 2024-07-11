package lk.ijse.company.bo.custom.impl;

import lk.ijse.company.bo.custom.SupplierBO;
import lk.ijse.company.dao.DAOFactory;
import lk.ijse.company.dao.custom.SupplierDAO;
import lk.ijse.company.dao.custom.TechnisiyanDAO;
import lk.ijse.company.dto.PermenentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<PermenentDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteSuppliers(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveSuppliers(PermenentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSuppliers(PermenentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existSuppliers(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return null;
    }
}
