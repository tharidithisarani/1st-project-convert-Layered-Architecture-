package lk.ijse.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomEntity {
    //Order
    private String orderId;
//    private String customerId;
    private LocalDate orderDate;

    //OrderDetail
    private String itemCode;
    private int qty;

    //Item
    private String code;
    private String description;
    private BigDecimal unitPrice;
    private int qtyOnHand;

}
