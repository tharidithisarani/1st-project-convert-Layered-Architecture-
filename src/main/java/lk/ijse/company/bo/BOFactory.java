package lk.ijse.company.bo;

import lk.ijse.company.bo.custom.ItemBO;
import lk.ijse.company.bo.custom.impl.CustomerBOImpl;
import lk.ijse.company.bo.custom.impl.ItemBOImpl;

import static com.lowagie.text.pdf.PdfName.PO;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        Customer, Item, Machine, Permenent, Supplier, technisiyan
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case Customer:
                return new CustomerBOImpl();
            case Item:
                return new ItemBOImpl();

            default:
                return null;
        }
    }
}
