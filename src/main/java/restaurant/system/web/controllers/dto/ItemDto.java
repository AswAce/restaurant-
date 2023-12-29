package restaurant.system.web.controllers.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto implements Serializable {

    private String types;

    private String specialType;

    private String name;

    private double price;

    private String description;

}
