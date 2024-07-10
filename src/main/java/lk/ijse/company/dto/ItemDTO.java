package lk.ijse.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemDTO implements Serializable {
    private String code;
    private String description;
    private BigDecimal unitPrice;
    private int qtyOnHand;
}
