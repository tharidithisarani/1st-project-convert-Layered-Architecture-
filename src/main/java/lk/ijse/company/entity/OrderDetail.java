package lk.ijse.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetail {
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;
}
