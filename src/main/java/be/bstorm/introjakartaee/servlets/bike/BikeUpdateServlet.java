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
 * Servlet gérant la modification des motocyclettes existantes.
 *
 * Traite les requêtes GET pour afficher le formulaire de modification
 * pré-rempli avec les données actuelles de la moto, et les requêtes POST
 * pour enregistrer les modifications dans la base de données.
 *
 * Accès: réservé aux administrateurs (filtre AuthFilter)
 * Routes:
 * - GET /bike/update?id=XX : affiche le formulaire avec les données de la moto XX
 * - POST /bike/update : met à jour la moto
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/bike/update")
public class BikeUpdateServlet extends HttpServlet {

    /**
     * DAO pour accéder aux données des motos.
     * Injecté par le conteneur Jakarta EE.
     */
    @Inject
    private BikeDao bikeDao;

    /**
     * Affiche le formulaire de modification d'une motocyclette existante.
     *
     * Récupère l'ID de la moto depuis le paramètre "id" de la requête.
     * Charge les données actuelles de la moto et les passe au formulaire.
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
            req.getRequestDispatcher("/pages/bike/update.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/bike");
        }
    }

    /**
     * Traite la soumission du formulaire de modification d'une moto.
     *
     * Récupère l'ID de la moto et les nouveaux paramètres du formulaire,
     * met à jour la moto existante avec les nouvelles valeurs,
     * et la sauvegarde dans la base de données.
     * Redirige ensuite vers la page de détails de la moto modifiée.
     *
     * Paramètres attendus:
     * - id: identifiant de la moto à mettre à jour
     * - brand: nouvelle marque de la moto
     * - model: nouveau modèle de la moto
     * - horsePower: nouvelle puissance en chevaux-vapeur
     * - imageUrl: nouvelle URL de l'image
     *
     * @param req la requête HTTP contenant l'ID et les données du formulaire
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
            Bike bike = bikeDao.findById(id);

            if (bike == null) {
                resp.sendRedirect(req.getContextPath() + "/bike");
                return;
            }

            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            int horsePower = Integer.parseInt(req.getParameter("horsePower"));
            String imageUrl = req.getParameter("imageUrl");

            bike.setBrand(brand);
            bike.setModel(model);
            bike.setHorsePower(horsePower);
            bike.setImageUrl(imageUrl);

            bikeDao.update(bike);

            resp.sendRedirect(req.getContextPath() + "/bike/details?id=" + id);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/bike");
        }
    }
}

