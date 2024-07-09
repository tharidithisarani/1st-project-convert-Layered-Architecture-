package lk.ijse.company.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ConstucterOrderTm {
    private String conId;
    private LocalDate conDate;
    private String customerId;
    private String customerName;
    private String contact;
    private String address;
    private String techId;
    private String techName;
    private Integer count;
    private BigDecimal rePay;
    private BigDecimal adPay;
    private BigDecimal fullPay;
}
