package lk.ijse.company.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.company.bo.BOFactory;
import lk.ijse.company.bo.custom.CustomerBO;
import lk.ijse.company.database.DbConnection;
import lk.ijse.company.dto.ItemDTO;
import lk.ijse.company.dto.OrderDTO;
import lk.ijse.company.model.*;
import lk.ijse.company.model.tm.CartTm;
import lk.ijse.company.model.tm.OrderDetailTm;
//import lk.ijse.company.reposotory.OrderRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerFormController {
    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnConstructerOrder;

    @FXML
    private JFXButton btnOrdinaryBuyer;

    @FXML
    private JFXButton btnPermenentBuyer;

    @FXML
    private Button btnimage;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnRegPermenentCustomer;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private Label lblCusID;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblItemCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblTotal;

    @FXML
    private Pane panedesc;

    @FXML
    private Pane paneorder;

    @FXML
    private AnchorPane rootCusDetail;

    @FXML
    private AnchorPane rootCustomer;

    @FXML
    private AnchorPane rootLode;

    @FXML
    private AnchorPane rootOrdinaryBuyer;

    @FXML
    private TableView<OrderDetailTm> tblOrderDetails;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    private ObservableList<CartTm> cartList = FXCollections.observableArrayList();

    private String orderId;

    @FXML
    private Label lblId;

    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);

    public void initialize() {
        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<OrderDetailTm, Button> lastCol = (TableColumn<OrderDetailTm, Button>) tblOrderDetails.getColumns().get(5);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        orderId = generateNewOrderId();
        lblId.setText("Order ID: " + orderId);
        lblDate.setText(LocalDate.now().toString());
        btnPlaceOrder.setDisable(true);
        txtDescription.setFocusTraversable(false);
        txtDescription.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);
        txtQty.setOnAction(event -> btnAddCart.fire());
        txtQty.setEditable(false);
        btnAddCart.setDisable(true);

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
            txtQty.setEditable(newItemCode != null);
            btnAddCart.setDisable(newItemCode == null);

            if (newItemCode != null) {
                //find item
                try {
                    if (!existItem(newItemCode + "")) {}
                    /*Connection connection = DbConnection.getInstance().getConnection();
                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
                    pstm.setString(1, newItemCode + "");
                    ResultSet rst = pstm.executeQuery();
                    rst.next();
                    Item item = new Item(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
*/

                    //search item
                    ItemDTO item = customerBO.searchItem(newItemCode + "");

                    txtDescription.setText(item.getDescription());
                    txtUnitPrice.setText(item.getUnitPrice().setScale(2).toString());

                    Optional<OrderDetailTm> optOrderDetail = tblOrderDetails.getItems().stream().filter(detail -> detail.getCode().equals(newItemCode)).findFirst();
                    txtQtyOnHand.setText((optOrderDetail.isPresent() ? item.getQtyOnHand() - optOrderDetail.get().getQty() : item.getQtyOnHand()) + "");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                txtDescription.clear();
                txtQty.clear();
                txtQtyOnHand.clear();
                txtUnitPrice.clear();
            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {

            if (selectedOrderDetail != null) {
                cmbItemCode.setDisable(true);
                cmbItemCode.setValue(selectedOrderDetail.getCode());
                btnAddCart.setText("Update");
                txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText()) + selectedOrderDetail.getQty() + "");
                txtQty.setText(selectedOrderDetail.getQty() + "");
            } else {
                btnAddCart.setText("Add");
                cmbItemCode.setDisable(false);
                cmbItemCode.getSelectionModel().clearSelection();
                txtQty.clear();
            }

        });

        loadAllItemCodes();
    }

    private void loadAllItemCodes() {
        try {
            /*Get all items*/
            /*Connection connection = DbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");
            while (rst.next()) {
                cmbItemCode.getItems().add(rst.getString("code"));
            }*/
            ArrayList<ItemDTO> allItems = customerBO.getAllItems();
            for (ItemDTO i : allItems) {
                cmbItemCode.getItems().add(i.getCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNewOrderId() {
        try {
            /*Connection connection = DbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

            return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";*/
            return customerBO.generateOrderID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "OID-001";
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/
        return customerBO.existItem(code);
    }

    private void enableOrDisablePlaceOrderButton() {
        btnPlaceOrder.setDisable(!(cmbItemCode.getSelectionModel().getSelectedItem() != null || !tblOrderDetails.getItems().isEmpty()));
    }

    private void calculateTotal() {
        BigDecimal total = new BigDecimal(0);

        for (OrderDetailTm detail : tblOrderDetails.getItems()) {
            total = total.add(detail.getTotal());
        }
        lblTotal.setText("Total: " +total);
    }

    @FXML
    void btnAddCartOnAction(ActionEvent event) {
        if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 ||
                Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;
        }

        String itemCode = cmbItemCode.getSelectionModel().getSelectedItem();
        String description = txtDescription.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);
        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal total = unitPrice.multiply(new BigDecimal(qty)).setScale(2);

        boolean exists = tblOrderDetails.getItems().stream().anyMatch(detail -> detail.getCode().equals(itemCode));

        if (exists) {
            OrderDetailTm orderDetailTm = tblOrderDetails.getItems().stream().filter(detail -> detail.getCode().equals(itemCode)).findFirst().get();

            if (btnAddCart.getText().equalsIgnoreCase("Update")) {
                orderDetailTm.setQty(qty);
                orderDetailTm.setTotal(total);
                tblOrderDetails.getSelectionModel().clearSelection();
            } else {
                orderDetailTm.setQty(orderDetailTm.getQty() + qty);
                total = new BigDecimal(orderDetailTm.getQty()).multiply(unitPrice).setScale(2);
                orderDetailTm.setTotal(total);
            }
            tblOrderDetails.refresh();
        } else {
            tblOrderDetails.getItems().add(new OrderDetailTm(itemCode, description, qty, unitPrice, total));
        }
        cmbItemCode.getSelectionModel().clearSelection();
        cmbItemCode.requestFocus();
        calculateTotal();
        enableOrDisablePlaceOrderButton();
    }

    @FXML
    void btnConstructerOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/constructer_order.fxml"));
        Stage stage = (Stage) rootCusDetail.getScene().getWindow();
        rootCusDetail.getChildren().add(rootNode);
        stage.setTitle("Constructer Order Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnOrdinaryBuyerOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/customer.fxml"));
        Stage stage = (Stage) rootCustomer.getScene().getWindow();
        rootCustomer.getChildren().clear();
        rootCustomer.getChildren().add(rootNode);
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnRegPermenentCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/permanent_buyer.fxml"));
        this.rootLode.getChildren().clear();
        this.rootLode.getChildren().add(anchorPane);
    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        boolean b = saveOrder(orderId, LocalDate.now(),
                tblOrderDetails.getItems().stream().map(tm -> new OrderDetail(tm.getCode(), tm.getQty(), tm.getUnitPrice())).collect(Collectors.toList()));

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            /*if(true){
                 JasperDesign jasperDesign =
                        JRXmlLoader.load("src/main/resources/Report/CustomerReport.jrxml");
                JasperReport jasperReport =
                    JasperCompileManager.compileReport(jasperDesign);

                Map<String, Object> data = new HashMap<>();
                data.put("reservationId",lblId.getText());

                JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            data,
                            DbConnection.getInstance().getConnection());

                JasperViewer.viewReport(jasperPrint,false);
            }else {
            new Alert(Alert.AlertType.ERROR, "You have not Repot").show();
            }*/
        } else {
            new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
        }

        orderId = generateNewOrderId();
        lblId.setText("Order Id: " + orderId);
        cmbItemCode.getSelectionModel().clearSelection();
        tblOrderDetails.getItems().clear();
        txtQty.clear();
        calculateTotal();
    }

    public boolean saveOrder(String orderId, LocalDate orderDate, List<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException {
       /* Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
            stm.setString(1, orderId);

            //if order id already exist
            if (stm.executeQuery().next()) {

            }

//            connection.setAutoCommit(false);
            stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date) VALUES (?,?)");
            stm.setString(1, orderId);
            stm.setDate(2, Date.valueOf(orderDate));

            if (stm.executeUpdate() != 1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, qty, unitPrice) VALUES (?,?,?,?)");

            for (OrderDetail detail : orderDetails) {
                stm.setString(1, orderId);
                stm.setString(2, detail.getItemCode());
                stm.setInt(3, detail.getQty());
                stm.setBigDecimal(4, detail.getUnitPrice());

                if (stm.executeUpdate() != 1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
                Item item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
                pstm.setString(1, item.getDescription());
                pstm.setBigDecimal(2, item.getUnitPrice());
                pstm.setInt(3, item.getQtyOnHand());
                pstm.setString(4, item.getCode());

                if (!(pstm.executeUpdate() > 0)) {
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
        }
        return false;
        */
//        return customerBO.purchaseOrder(orderD);
        OrderDTO orderDTO = new OrderDTO(orderId, orderDate, orderDetails);
        return customerBO.purchaseOrder(orderDTO);
    }


    public Item findItem(String code) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
            pstm.setString(1, code);
            ResultSet rst = pstm.executeQuery();
            rst.next();
            return new Item(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        }
    }

    public void txtQty_OnAction(ActionEvent actionEvent) {
    }

    @FXML
    void btnImage_OnAction(ActionEvent event) {
        new Alert(Alert.AlertType.ERROR, "The form desn't available, Click the correct Button").show();
    }

}



