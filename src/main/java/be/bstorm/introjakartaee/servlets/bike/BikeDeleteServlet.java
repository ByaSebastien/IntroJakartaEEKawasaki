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
 * Servlet gérant la suppression des motocyclettes.
 *
 * Traite les requêtes POST pour supprimer une motocyclette de la base de données
 * en fonction de son identifiant.
 *
 * Accès: réservé aux administrateurs (filtre AuthFilter)
 * Route:
 * - POST /bike/delete : supprime une moto
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/bike/delete")
public class BikeDeleteServlet extends HttpServlet {

    /**
     * DAO pour accéder aux données des motos.
     * Injecté par le conteneur Jakarta EE.
     */
    @Inject
    private BikeDao bikeDao;

    /**
     * Traite la suppression d'une motocyclette.
     *
     * Récupère l'ID de la moto depuis le paramètre "id" de la requête.
     * Valide que l'ID est fourni et valide (entier positif).
     * Supprime la moto de la base de données et redirige vers la liste.
     *
     * Validations:
     * - Le paramètre "id" doit être présent et non vide
     * - L'ID doit être un nombre entier valide
     *
     * En cas d'erreur (ID invalide), redirige simplement vers la liste des motos.
     *
     * @param req la requête HTTP contenant le paramètre "id"
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/bike");
            return;
        }

        try {
            Integer id = Integer.parseInt(idParam);
            bikeDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/bike");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/bike");
        }
    }
}

