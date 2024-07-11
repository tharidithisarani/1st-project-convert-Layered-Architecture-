package lk.ijse.company.dao.custom.impl;

import lk.ijse.company.dao.SQLUtil;
import lk.ijse.company.dao.custom.TechnisiyanDAO;
import lk.ijse.company.database.DbConnection;
import lk.ijse.company.entity.Technisiyan;
import lk.ijse.company.model.tm.TechDetailTm;

import java.sql.*;
import java.util.ArrayList;

public class TechnisiyanDAOImpl implements TechnisiyanDAO {
    @Override
    public ArrayList<Technisiyan> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Technisiyan> allTechnisiyans = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM techDetail");
        while (rst.next()){
            allTechnisiyans.add(new Technisiyan(rst.getString("code"),
                    rst.getString("NIC"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("contact"),
                    rst.getString("bankname"),
                    rst.getString("accountNum"),
                    rst.getString("toolCode"),
                    rst.getString("description")));
        }
        return allTechnisiyans;
    }

    @Override
    public boolean save(Technisiyan entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO techDetail (code, NIC, name, address, contact, bankName, accountNum, toolCode, description) VALUES (?,?,?,?,?,?,?,?,?)",
                entity.getCode(), entity.getNic(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getBankName(), entity.getAccountNum(), entity.getToolCode(), entity.getDescription());
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
        ResultSet rst =SQLUtil.execute("SELECT code FROM techDetail ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newTechDetailId = Integer.parseInt(id.replace("TD-000-", "")) + 1;
            return String.format("TD-000-%03d", newTechDetailId);
        } else {
            return "TD-000-001";
        }
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM techDetail WHERE NIC=?", nic);
    }

    @Override
    public Technisiyan search(String id) throws SQLException, ClassNotFoundException {

        return null;
    }
}
