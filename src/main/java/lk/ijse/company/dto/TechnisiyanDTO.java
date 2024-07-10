package lk.ijse.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TechnisiyanDTO implements Serializable {
    private String code;
    private String nic;
    private String name;
    private String address;
    private String contact;
    private String bankName;
    private String accountNum;
    private String toolCode;
    private String description;
}
