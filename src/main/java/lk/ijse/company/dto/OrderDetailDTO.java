package lk.ijse.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetailDTO implements Serializable {
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;
}
