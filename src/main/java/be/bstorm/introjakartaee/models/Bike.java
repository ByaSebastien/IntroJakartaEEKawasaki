package be.bstorm.introjakartaee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Bike {

    private Integer id;
    private String brand;
    private String model;
    private int horsePower;
    private String imageUrl;
}
