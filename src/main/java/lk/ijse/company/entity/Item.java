package lk.ijse.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Item {
    private String code;
    private String description;
    private BigDecimal unitPrice;
    private int qtyOnHand;
}
