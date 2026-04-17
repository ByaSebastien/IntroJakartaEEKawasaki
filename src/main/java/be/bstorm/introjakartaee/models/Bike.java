package be.bstorm.introjakartaee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Bike {

    public static int nextId = 11;

    private Integer id;
    private String brand;
    private String model;
    private int horsePower;
    private String imageUrl;

    public Bike(String brand, String model, int horsePower, String imageUrl) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.imageUrl = imageUrl;
        id = nextId++;
    }
}
