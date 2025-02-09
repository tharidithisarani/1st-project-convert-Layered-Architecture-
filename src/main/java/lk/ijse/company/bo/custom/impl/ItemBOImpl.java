package lk.ijse.company.bo.custom.impl;

import lk.ijse.company.bo.custom.ItemBO;
import lk.ijse.company.dao.DAOFactory;
import lk.ijse.company.dao.custom.ItemDAO;
import lk.ijse.company.dto.ItemDTO;
import lk.ijse.company.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allEntityData = itemDAO.getAll();
        ArrayList<ItemDTO> allDTOData= new ArrayList<>();
        for (Item i : allEntityData) {
            allDTOData.add(new ItemDTO(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand()));
        }
        return allDTOData;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(),dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(),dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }
}
