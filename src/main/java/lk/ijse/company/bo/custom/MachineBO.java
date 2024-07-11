package lk.ijse.company.bo.custom;

import lk.ijse.company.bo.SuperBO;
import lk.ijse.company.dto.PermenentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineBO extends SuperBO {
    public ArrayList<PermenentDTO> getAllMachine() throws SQLException, ClassNotFoundException;

    public boolean deleteMachine(String code) throws SQLException, ClassNotFoundException ;

    public boolean saveMachine(PermenentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateMachine(PermenentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existMachine(String code) throws SQLException, ClassNotFoundException ;

    public String generateNewCode() throws SQLException, ClassNotFoundException ;
}
