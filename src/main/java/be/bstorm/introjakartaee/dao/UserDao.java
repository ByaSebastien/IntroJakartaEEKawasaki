package be.bstorm.introjakartaee.dao;

import be.bstorm.introjakartaee.models.User;

/**
 * Interface DAO (Data Access Object) pour la gestion des opérations de base
 * de données relatives aux utilisateurs.
 *
 * Définie les méthodes nécessaires pour la persistance et la récupération
 * des données utilisateur depuis la base de données.
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
public interface UserDao {

    /**
     * Sauvegarde un nouvel utilisateur dans la base de données.
     *
     * @param user l'utilisateur à sauvegarder
     */
    void save(User user);

    /**
     * Récupère un utilisateur depuis la base de données en fonction de son email.
     *
     * @param email l'email de l'utilisateur à rechercher
     * @return l'utilisateur trouvé, ou null s'il n'existe pas
     */
    User findByEmail(String email);
}
