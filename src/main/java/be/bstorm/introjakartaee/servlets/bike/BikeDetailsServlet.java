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
 * Servlet affichant les détails completos d'une motocyclette spécifique.
 *
 * Récupère les informations détaillées d'une moto en fonction de son ID
 * et les affiche dans une page dédiée.
 *
 * Accès: publique (accessible à tous)
 * Route:
 * - GET /bike/details?id=XX : affiche les détails de la moto avec l'ID XX
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/bike/details")
public class BikeDetailsServlet extends HttpServlet {

    /**
     * DAO pour accéder aux données des motos.
     * Injecté par le conteneur Jakarta EE.
     */
    @Inject
    private BikeDao bikeDao;

    /**
     * Affiche les détails d'une motocyclette spécifique.
     *
     * Récupère l'ID de la moto depuis le paramètre "id" de la requête.
     * Valide que l'ID est fourni et valide (entier positif).
     * Si la moto existe, affiche ses détails. Sinon, redirige vers la liste.
     *
     * Validations:
     * - Le paramètre "id" doit être présent et non vide
     * - L'ID doit être un nombre entier valide
     * - La moto avec cet ID doit exister dans la base de données
     *
     * @param req la requête HTTP contenant le paramètre "id"
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/bike");
            return;
        }
        
        try {
            Integer id = Integer.parseInt(idParam);
            Bike bike = bikeDao.findById(id);
            
            if (bike == null) {
                resp.sendRedirect(req.getContextPath() + "/bike");
                return;
            }
            
            req.setAttribute("bike", bike);
            req.getRequestDispatcher("/pages/bike/details.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/bike");
        }
    }
}

