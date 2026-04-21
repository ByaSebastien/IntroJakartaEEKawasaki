package be.bstorm.introjakartaee.db;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de configuration et de gestion de la base de données PostgreSQL.
 *
 * Gère la connexion à la base de données et l'initialisation des tables
 * et données de démarrage. Inclut des utilisateurs de test et des motos pré-configurées.
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
public class DatabaseConfig {

    /** URL de connexion à la base de données PostgreSQL */
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/kawasaki_db";

    /** Nom d'utilisateur pour la connexion PostgreSQL */
    private static final String DB_USER = "postgres";

    /** Mot de passe pour la connexion PostgreSQL */
    private static final String DB_PASSWORD = "postgres";

    /**
     * Bloc d'initialisation statique qui charge le driver PostgreSQL
     * et initialise la base de données au démarrage de l'application.
     */
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found!");
            e.printStackTrace();
        }
    }

    /**
     * Obtient une connexion à la base de données PostgreSQL.
     *
     * @return une connexion ouverte à la base de données
     * @throws SQLException si la connexion échoue
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

