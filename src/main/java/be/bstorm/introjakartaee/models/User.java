package be.bstorm.introjakartaee.models;

import be.bstorm.introjakartaee.models.enums.UserRole;
import lombok.*;

/**
 * Entité représentant un utilisateur du système.
 * Contient les informations essentielles d'un utilisateur incluant son identifiant,
 * son email, son mot de passe hashé et son rôle dans le système.
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@EqualsAndHashCode @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    /**
     * Identifiant unique de l'utilisateur (clé primaire générée automatiquement).
     */
    @Getter
    private Integer id;

    /**
     * Adresse email de l'utilisateur. Utilisée pour l'authentification.
     */
    @Getter @Setter
    private String email;

    /**
     * Mot de passe hashé de l'utilisateur. Ne doit jamais être stocké en clair.
     */
    @Getter @Setter
    private String password;

    /**
     * Rôle de l'utilisateur dans le système (ADMIN ou USER).
     */
    @Getter @Setter
    private UserRole role;

    /**
     * Constructeur pour créer un nouvel utilisateur sans identifiant.
     *
     * @param email l'email de l'utilisateur
     * @param password le mot de passe hashé de l'utilisateur
     * @param role le rôle de l'utilisateur
     */
    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
