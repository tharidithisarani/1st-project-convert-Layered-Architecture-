package lk.ijse.company.bo.custom;

import lk.ijse.company.bo.SuperBO;
import lk.ijse.company.dto.ItemDTO;
import lk.ijse.company.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException ;

    public boolean existItem(String code) throws SQLException, ClassNotFoundException;

    public String generateOrderID() throws SQLException, ClassNotFoundException ;

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean purchaseOrder(OrderDTO dto)throws SQLException, ClassNotFoundException;

    public ItemDTO findItem(String code)throws SQLException, ClassNotFoundException;
}
