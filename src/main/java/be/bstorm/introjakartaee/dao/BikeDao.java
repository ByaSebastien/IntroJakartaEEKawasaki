package be.bstorm.introjakartaee.dao;

import be.bstorm.introjakartaee.models.Bike;
import java.util.List;

/**
 * Interface DAO (Data Access Object) pour la gestion des opérations de base
 * de données relatives aux motocyclettes.
 *
 * Définit les méthodes nécessaires pour la persistance, la récupération, la mise à jour
 * et la suppression des données de motocyclettes depuis la base de données.
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
public interface BikeDao {

    /**
     * Récupère la liste de toutes les motocyclettes de la base de données.
     *
     * @return une liste contenant toutes les motos, ou une liste vide s'il n'y en a pas
     */
    List<Bike> findAll();

    /**
     * Récupère une motocyclette en fonction de son identifiant.
     *
     * @param id l'identifiant de la moto à rechercher
     * @return la moto trouvée, ou null si elle n'existe pas
     */
    Bike findById(int id);

    /**
     * Sauvegarde une nouvelle motocyclette dans la base de données.
     *
     * @param bike la moto à sauvegarder
     */
    void save(Bike bike);

    /**
     * Met à jour une motocyclette existante dans la base de données.
     *
     * @param bike la moto à mettre à jour (doit avoir un ID valide)
     */
    void update(Bike bike);

    /**
     * Supprime une motocyclette de la base de données en fonction de son identifiant.
     *
     * @param id l'identifiant de la moto à supprimer
     */
    void delete(int id);
}

