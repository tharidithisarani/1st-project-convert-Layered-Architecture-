package lk.ijse.company.dao.custom.impl;

import lk.ijse.company.dao.SQLUtil;
import lk.ijse.company.dao.custom.PermenentDAO;
import lk.ijse.company.database.DbConnection;
import lk.ijse.company.entity.Permenent;
import lk.ijse.company.model.tm.PermenentTm;

import java.sql.*;
import java.util.ArrayList;

public class PermenentDAOImpl implements PermenentDAO {
    @Override
    public ArrayList<Permenent> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Permenent> allPermenentCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM permenentCus");
        while (rst.next()) {
            allPermenentCustomers.add(new Permenent(rst.getString("code"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("contact"),
                    rst.getString("description")));
        }
        return allPermenentCustomers;
    }

    @Override
    public boolean save(Permenent entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO permenentCus (code, name, address, contact, description) VALUES (?,?,?,?,?)",
                entity.getCode(), entity.getName(), entity.getAddress(), entity.getCode(), entity.getDescription());
    }

    @Override
    public boolean update(Permenent entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE permenentCus SET name=?, address=?, contact=?, description=? WHERE code=?",
                entity.getCode(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getDescription());
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM permenentCus WHERE code=?", code);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM permenentCus ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newId = Integer.parseInt(id.replace("CUS00-", "")) + 1;
            return String.format("CUS00-%03d", newId);
        } else {
            return "CUS00-001";
        }
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM permenentCus WHERE code=?", code);
    }

    @Override
    public Permenent search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
