package lk.ijse.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDetail implements Serializable {
    private String oid;
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;

    public OrderDetail(String code, int qty, BigDecimal unitPrice) {
    }
}
