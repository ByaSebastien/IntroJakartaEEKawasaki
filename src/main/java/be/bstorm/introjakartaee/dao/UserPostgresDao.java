package be.bstorm.introjakartaee.dao;

import be.bstorm.introjakartaee.db.DatabaseConfig;
import be.bstorm.introjakartaee.models.Bike;
import be.bstorm.introjakartaee.models.User;
import be.bstorm.introjakartaee.models.enums.UserRole;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implémentation PostgreSQL de l'interface {@link UserDao}.
 *
 * Gère la persistence des utilisateurs dans une base de données PostgreSQL.
 * Utilise des requêtes SQL préparées pour éviter les injections SQL.
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@ApplicationScoped
public class UserPostgresDao implements UserDao {

    /**
     * Sauvegarde un nouvel utilisateur dans la base de données PostgreSQL.
     *
     * @param user l'utilisateur à sauvegarder (ne doit pas avoir d'ID)
     */
    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole().name());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du user!");
            e.printStackTrace();
        }
    }

    /**
     * Récupère un utilisateur de la base de données PostgreSQL en fonction de son email.
     *
     * @param email l'email de l'utilisateur à rechercher
     * @return l'utilisateur trouvé avec tous ses détails, ou null s'il n'existe pas
     */
    @Override
    public User findByEmail(String email) {
        String sql = "SELECT id, email, password, role FROM users WHERE email = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            UserRole.valueOf(rs.getString("role"))
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la moto!");
            e.printStackTrace();
        }
        return null;
    }
}
