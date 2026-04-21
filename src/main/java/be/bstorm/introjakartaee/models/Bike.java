package be.bstorm.introjakartaee.models;

import lombok.*;

@EqualsAndHashCode @ToString
@NoArgsConstructor @AllArgsConstructor
public class Bike {

    @Getter
    private Integer id;

    @Getter @Setter
    private String brand;

    @Getter @Setter
    private String model;

    @Getter @Setter
    private int horsePower;

    @Getter @Setter
    private String imageUrl;

    public Bike(String brand, String model, int horsePower, String imageUrl) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.imageUrl = imageUrl;
    }
}
