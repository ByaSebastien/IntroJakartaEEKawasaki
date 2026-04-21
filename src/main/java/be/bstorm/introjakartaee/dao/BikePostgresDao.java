package be.bstorm.introjakartaee.dao;

import be.bstorm.introjakartaee.db.DatabaseConfig;
import be.bstorm.introjakartaee.models.Bike;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation PostgreSQL de l'interface {@link BikeDao}.
 *
 * Gère la persistence des motocyclettes dans une base de données PostgreSQL.
 * Utilise des requêtes SQL préparées pour éviter les injections SQL et des
 * transactions pour assurer l'intégrité des données.
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@ApplicationScoped
public class BikePostgresDao implements BikeDao {

    /**
     * Récupère la liste de toutes les motocyclettes de la base de données PostgreSQL,
     * triées par identifiant croissant.
     *
     * @return une liste contenant toutes les motos, ou une liste vide s'il n'y en a pas
     */
    @Override
    public List<Bike> findAll() {
        List<Bike> bikes = new ArrayList<>();
        String sql = "SELECT id, brand, model, horse_power, image_url FROM bikes ORDER BY id";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bike bike = new Bike(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("horse_power"),
                        rs.getString("image_url")
                );
                bikes.add(bike);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des motos!");
            e.printStackTrace();
        }
        return bikes;
    }

    /**
     * Récupère une motocyclette en fonction de son identifiant unique.
     *
     * @param id l'identifiant de la moto à rechercher
     * @return la moto trouvée avec tous ses détails, ou null si elle n'existe pas
     */
    @Override
    public Bike findById(int id) {
        String sql = "SELECT id, brand, model, horse_power, image_url FROM bikes WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Bike(
                            rs.getInt("id"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getInt("horse_power"),
                            rs.getString("image_url")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la moto!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sauvegarde une nouvelle motocyclette dans la base de données PostgreSQL.
     *
     * @param bike la moto à sauvegarder (ne doit pas avoir d'ID)
     */
    @Override
    public void save(Bike bike) {
        String sql = "INSERT INTO bikes (brand, model, horse_power, image_url) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bike.getBrand());
            pstmt.setString(2, bike.getModel());
            pstmt.setInt(3, bike.getHorsePower());
            pstmt.setString(4, bike.getImageUrl());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la moto!");
            e.printStackTrace();
        }
    }

    /**
     * Met à jour une motocyclette existante dans la base de données PostgreSQL.
     * La moto doit avoir un identifiant valide pour être mise à jour.
     *
     * @param bike la moto à mettre à jour avec toutes ses nouvelles données
     */
    @Override
    public void update(Bike bike) {
        String sql = "UPDATE bikes SET brand = ?, model = ?, horse_power = ?, image_url = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bike.getBrand());
            pstmt.setString(2, bike.getModel());
            pstmt.setInt(3, bike.getHorsePower());
            pstmt.setString(4, bike.getImageUrl());
            pstmt.setInt(5, bike.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la moto!");
            e.printStackTrace();
        }
    }

    /**
     * Supprime une motocyclette de la base de données PostgreSQL en fonction de son identifiant.
     *
     * @param id l'identifiant de la moto à supprimer
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM bikes WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la moto!");
            e.printStackTrace();
        }
    }
}

