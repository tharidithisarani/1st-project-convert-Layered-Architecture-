package lk.ijse.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ConstructerOrder {
    private String orderId;
    private LocalDate orderDate;
    private String customerId;
    private String customerName;
    private String cntact;
    private String address;
    private BigDecimal rePay;
}
