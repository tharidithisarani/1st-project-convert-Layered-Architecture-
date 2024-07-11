package lk.ijse.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Order {
    private String orderId;
//    private String customerId;
    private LocalDate orderDate;
}
