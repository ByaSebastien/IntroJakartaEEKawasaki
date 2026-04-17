package be.bstorm.introjakartaee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    // Configuration PostgreSQL
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/kawasaki_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // DROP la table si elle existe
            System.out.println("🗑️ Suppression de la table bikes...");
            try {
                stmt.execute("DROP TABLE IF EXISTS bikes CASCADE");
                System.out.println("✅ Table supprimée avec succès");
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors de la suppression: " + e.getMessage());
            }

            // CREATE la table
            System.out.println("🔨 Création de la table bikes...");
            String createTableSQL = "CREATE TABLE bikes (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    brand VARCHAR(100) NOT NULL,\n" +
                    "    model VARCHAR(100) NOT NULL,\n" +
                    "    horse_power INT NOT NULL,\n" +
                    "    image_url TEXT NOT NULL\n" +
                    ")";
            stmt.execute(createTableSQL);
            System.out.println("✅ Table créée avec succès");

            // SEED DATA - Insertion des données initiales
            System.out.println("📊 Insertion des données initiales...");
            String[] insertStatements = {
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (1, 'Kawasaki', 'Ninja H2', 300, 'https://www.kawasaki.be/content/dam/products/pim/studio/nin/Resource_299076_24MY_Ninja_H2R_GY3_STU.jpg/_jcr_content/renditions/cq5dam.thumbnail.600.600.png')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (2, 'Kawasaki', 'Ninja ZX-10R', 210, 'https://www.kawasaki.be/content/dam/products/pim/studio/Resource_324964_26ZX1003A_40TGN1DRF3CG_A.jpg')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (3, 'Kawasaki', 'Ninja 400', 45, 'https://storage.kawasaki.eu/public/kawasaki.eu/en-EU/model/Ninja-400-GN2-Performance-front_001.png')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (4, 'Kawasaki', 'Ninja 650', 68, 'https://www.kawasaki.be/content/dam/products/pim/studio/s/Resource_320273_26EX650P_S_44TGN1DRF3CG_A.jpg')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (5, 'Kawasaki', 'Z900RS', 109, 'https://www.kawasaki.be/content/dam/products/pim/studio/a/Resource_324750_26ZR902A_A40TRD1DLS3CG_A.jpg/_jcr_content/renditions/cq5dam.web.1280.1280.png')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (6, 'Kawasaki', 'Z H2', 236, 'https://www.kawasaki.be/content/dam/products/pim/studio/r/Resource_320876_26ZR1000L_R_40TGY1DRF3CG_A.jpg')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (7, 'Kawasaki', 'Ninja 1000 SX', 143, 'https://www.kawasaki.be/content/dam/products/pim/resource/Resource_321239_26ZX1100J_40TBU1DRF3CG_A.jpg/_jcr_content/renditions/cq5dam.thumbnail.600.600.png')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (8, 'Kawasaki', 'Vulcan S', 61, 'https://www.kawasaki.be/content/dam/products/pim/studio/m/Resource_320137_26EN650D_M_44TGY1DRF1CG_A.jpg')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (9, 'Kawasaki', 'Z650', 68, 'https://www.kawasaki.be/content/dam/products/pim/studio/Resource_324316_26ER650W_44TGY1DRF3CG_A.jpg')",
                    "INSERT INTO bikes (id, brand, model, horse_power, image_url) VALUES (10, 'Kawasaki', 'Ninja ZX-6R', 130, 'https://www.kawasaki.be/content/dam/products/pim/studio/Resource_320148_26ZX636J_40TGN1DRF3CG_A.jpg')"
            };

            int count = 0;
            for (String sql : insertStatements) {
                stmt.execute(sql);
                count++;
            }
            System.out.println("✅ " + count + " motos insérées avec succès");

            // Reset de la séquence
            stmt.execute("ALTER SEQUENCE bikes_id_seq RESTART WITH 11");
            System.out.println("✅ Séquence initialisée");

            System.out.println("🏍️ Base de données initialisée avec succès!\n");

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'initialisation de la base de données!");
            e.printStackTrace();
        }
    }
}

