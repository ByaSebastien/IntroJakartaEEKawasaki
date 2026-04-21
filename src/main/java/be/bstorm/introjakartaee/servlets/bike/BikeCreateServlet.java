package be.bstorm.introjakartaee.servlets.bike;

import be.bstorm.introjakartaee.dao.BikeDao;
import be.bstorm.introjakartaee.models.Bike;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet gérant la création de nouvelles motocyclettes.
 *
 * Traite les requêtes GET pour afficher le formulaire de création
 * et les requêtes POST pour enregistrer une nouvelle moto dans la base de données.
 *
 * Accès: réservé aux administrateurs (filtre AuthFilter)
 * Routes:
 * - GET /bike/create : affiche le formulaire
 * - POST /bike/create : crée une nouvelle moto
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/bike/create")
public class BikeCreateServlet extends HttpServlet {

    /**
     * DAO pour accéder aux données des motos.
     * Injecté par le conteneur Jakarta EE.
     */
    @Inject
    private BikeDao bikeDao;

    /**
     * Affiche le formulaire de création d'une nouvelle moto.
     *
     * @param req la requête HTTP
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/bike/create.jsp").forward(req, resp);
    }

    /**
     * Traite la soumission du formulaire de création d'une moto.
     *
     * Récupère les paramètres du formulaire (marque, modèle, puissance, URL image),
     * crée une nouvelle instance de Bike, et la sauvegarde dans la base de données.
     * Redirige ensuite vers la liste des motos.
     *
     * Paramètres attendus:
     * - brand: marque de la moto
     * - model: modèle de la moto
     * - horsePower: puissance en chevaux-vapeur (nombre entier)
     * - imageUrl: URL de l'image de la moto
     *
     * @param req la requête HTTP contenant les données du formulaire
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int horsePower = Integer.parseInt(req.getParameter("horsePower"));
        String imageUrl = req.getParameter("imageUrl");

        Bike bike = new Bike(brand, model, horsePower, imageUrl);

        bikeDao.save(bike);

        resp.sendRedirect(req.getContextPath() + "/bike");
    }
}
