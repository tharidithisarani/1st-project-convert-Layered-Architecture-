package lk.ijse.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Permenent implements Serializable {
    private String code;
    private String name;
    private String address;
    private String contact;
    private String description;
}
