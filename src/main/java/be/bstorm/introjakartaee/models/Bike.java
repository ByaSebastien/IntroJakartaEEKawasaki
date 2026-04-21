package be.bstorm.introjakartaee.models;

import lombok.*;

/**
 * Entité représentant une motocyclette du catalogue.
 * Contient les caractéristiques techniques d'une moto incluant sa marque, modèle,
 * puissance et URL de son image.
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@EqualsAndHashCode @ToString
@NoArgsConstructor @AllArgsConstructor
public class Bike {

    /**
     * Identifiant unique de la motocyclette (clé primaire générée automatiquement).
     */
    @Getter
    private Integer id;

    /**
     * Marque/Fabricant de la motocyclette (ex: Kawasaki, Yamaha, etc.).
     */
    @Getter @Setter
    private String brand;

    /**
     * Modèle de la motocyclette (ex: Ninja H2, Z900RS, etc.).
     */
    @Getter @Setter
    private String model;

    /**
     * Puissance de la motocyclette en chevaux-vapeur (ch).
     */
    @Getter @Setter
    private int horsePower;

    /**
     * URL de l'image d'affichage de la motocyclette.
     */
    @Getter @Setter
    private String imageUrl;

    /**
     * Constructeur pour créer une nouvelle motocyclette sans identifiant.
     *
     * @param brand la marque de la moto
     * @param model le modèle de la moto
     * @param horsePower la puissance en chevaux-vapeur
     * @param imageUrl l'URL de l'image de la moto
     */
    public Bike(String brand, String model, int horsePower, String imageUrl) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.imageUrl = imageUrl;
    }
}
