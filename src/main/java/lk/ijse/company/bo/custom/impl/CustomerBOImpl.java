package lk.ijse.company.bo.custom.impl;

import lk.ijse.company.bo.custom.CustomerBO;
import lk.ijse.company.dao.DAOFactory;
import lk.ijse.company.dao.custom.ItemDAO;
import lk.ijse.company.dao.custom.OrderDAO;
import lk.ijse.company.dao.custom.OrderDetailDAO;
import lk.ijse.company.database.DbConnection;
import lk.ijse.company.dto.ItemDTO;
import lk.ijse.company.dto.OrderDTO;
import lk.ijse.company.dto.OrderDetailDTO;
import lk.ijse.company.entity.Item;
import lk.ijse.company.entity.Order;
import lk.ijse.company.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);


    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item i = itemDAO.search(code);
        return new ItemDTO(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand());
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> entityTypeData = itemDAO.getAll();
        ArrayList<ItemDTO> dtoTypeData= new ArrayList<>();
        for (Item i : entityTypeData) {
            dtoTypeData.add(new ItemDTO(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand()));
        }
        return dtoTypeData;
    }

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean b1 = orderDAO.exist(dto.getOrderId());
            /*PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
            stm.setString(1, orderId);*/

            //if order id already exist
            if (b1) {
                return false;
            }

            //save order to Order-table
//            stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date) VALUES (?,?)");
//            stm.setString(1, orderId);
//            stm.setDate(2, Date.valueOf(orderDate));

            boolean b2 = orderDAO.save(new Order(dto.getOrderId(), dto.getOrderDate()));
            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//            stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, qty, unitPrice) VALUES (?,?,?,?)");

            for (OrderDetailDTO detail : dto.getOrderDetails()) {
                OrderDetail orderDetail = new OrderDetail(detail.getOid(), detail.getItemCode(),detail.getQty(), detail.getUnitPrice());
                boolean b3 = orderDetailsDAO.save(orderDetail);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                /*PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
                pstm.setString(1, item.getDescription());
                pstm.setBigDecimal(2, item.getUnitPrice());
                pstm.setInt(3, item.getQtyOnHand());
                pstm.setString(4, item.getCode());*/

                //update item
                boolean b = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
        try {
            Item i = itemDAO.search(code);
            return new ItemDTO(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
