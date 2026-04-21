package be.bstorm.introjakartaee.servlets.auth;

import be.bstorm.introjakartaee.dao.UserDao;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

/**
 * Servlet gérant l'authentification des utilisateurs.
 *
 * Traite les requêtes GET pour afficher le formulaire de connexion
 * et les requêtes POST pour valider les identifiants et créer une session utilisateur.
 *
 * Accès: uniquement pour les utilisateurs non connectés (filtre AuthFilter)
 * Routes:
 * - GET /auth/login : affiche le formulaire
 * - POST /auth/login : traite la connexion
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/auth/login")
public class LoginServlet extends HttpServlet {

    /**
     * DAO pour accéder aux données des utilisateurs.
     * Injecté par le conteneur Jakarta EE.
     */
    @Inject
    private UserDao userDao;

    /**
     * Affiche le formulaire de connexion.
     *
     * @param req la requête HTTP
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/auth/login.jsp").forward(req, resp);
    }

    /**
     * Traite la soumission du formulaire de connexion.
     *
     * Valide les identifiants (email et mot de passe) contre la base de données.
     * Si les identifiants sont valides, crée une session utilisateur et redirige vers la liste des motos.
     * Sinon, réaffiche le formulaire avec un message d'erreur.
     *
     * @param req la requête HTTP contenant les paramètres "email" et "password"
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        var user = userDao.findByEmail(email);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            req.getSession(true).setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/bike");
        } else {
            req.setAttribute("error", "Email ou mot de passe incorrect");
            req.getRequestDispatcher("/pages/auth/login.jsp").forward(req, resp);
        }
    }
}
