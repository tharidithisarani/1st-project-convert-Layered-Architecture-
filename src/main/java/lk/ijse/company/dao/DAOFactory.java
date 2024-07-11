package lk.ijse.company.dao;

import lk.ijse.company.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        ITEM, ORDER, ORDER_DETAIL, MACHINE, PERMENENT, QUERY_DAO, SUPPLIER, TECHNISIYAN
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            case PERMENENT:
                return new PermenentDAOImpl();
            case MACHINE:
                return new MachineDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TECHNISIYAN:
                return new TechnisiyanDAOImpl();
            default:
                return null;
        }
    }

}
