package lk.ijse.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Supplier {
    private String code;
    private String name;
    private String contact;
    private String email;
    private String description;
}
