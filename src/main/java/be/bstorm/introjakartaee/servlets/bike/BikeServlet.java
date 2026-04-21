package be.bstorm.introjakartaee.servlets.bike;

import be.bstorm.introjakartaee.dao.BikeDao;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet affichant la liste de toutes les motocyclettes disponibles.
 *
 * Récupère toutes les motos de la base de données et les transmet
 * à la page JSP pour affichage.
 *
 * Accès: publique (accessible à tous)
 * Route:
 * - GET /bike : affiche la liste des motos
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/bike")
public class BikeServlet extends HttpServlet {

    /**
     * DAO pour accéder aux données des motos.
     * Injecté par le conteneur Jakarta EE.
     */
    @Inject
    private BikeDao bikeDao;

    /**
     * Affiche la liste de toutes les motocyclettes.
     *
     * Récupère toutes les motos de la base de données via le DAO
     * et les passe à la page de visualisation.
     *
     * @param req la requête HTTP
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bikes", bikeDao.findAll());
        req.getRequestDispatcher("/pages/bike/index.jsp").forward(req, resp);
    }
}