package be.bstorm.introjakartaee.servlets.auth;

import be.bstorm.introjakartaee.dao.UserDao;
import be.bstorm.introjakartaee.models.User;
import be.bstorm.introjakartaee.models.enums.UserRole;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

/**
 * Servlet gérant l'inscription des nouveaux utilisateurs.
 *
 * Traite les requêtes GET pour afficher le formulaire d'inscription
 * et les requêtes POST pour créer un nouvel utilisateur après validation.
 *
 * Accès: uniquement pour les utilisateurs non connectés (filtre AuthFilter)
 * Routes:
 * - GET /auth/register : affiche le formulaire
 * - POST /auth/register : crée un nouvel utilisateur
 *
 * Validations effectuées:
 * - Email non vide et valide
 * - Mot de passe au minimum 6 caractères
 * - Email pas déjà enregistré
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/auth/register")
public class RegisterServlet extends HttpServlet {

    /**
     * DAO pour accéder aux données des utilisateurs.
     * Injecté par le conteneur Jakarta EE.
     */
    @Inject
    private UserDao userDao;

    /**
     * Affiche le formulaire d'inscription.
     *
     * @param req la requête HTTP
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/pages/auth/register.jsp").forward(req, resp);
    }

    /**
     * Traite la soumission du formulaire d'inscription.
     *
     * Valide l'email et le mot de passe, puis vérifie qu'aucun utilisateur
     * n'existe déjà avec cet email. Si tout est valide, crée un nouvel utilisateur
     * avec un mot de passe hashé et le rôle USER par défaut.
     *
     * Validations:
     * - Email non vide
     * - Mot de passe au minimum 6 caractères
     * - Email pas déjà utilisé dans la base de données
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

        // Validation
        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("error", "L'email est requis");
            req.getRequestDispatcher("/pages/auth/register.jsp").forward(req, resp);
            return;
        }

        if (password == null || password.length() < 6) {
            req.setAttribute("error", "Le mot de passe doit contenir au moins 6 caractères");
            req.getRequestDispatcher("/pages/auth/register.jsp").forward(req, resp);
            return;
        }

        // Vérifier si l'utilisateur existe déjà
        if (userDao.findByEmail(email) != null) {
            req.setAttribute("error", "Cet email est déjà utilisé");
            req.getRequestDispatcher("/pages/auth/register.jsp").forward(req, resp);
            return;
        }

        User user = new User(
                email,
                BCrypt.hashpw(password, BCrypt.gensalt()),
                UserRole.USER
        );

        userDao.save(user);

        req.setAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
        req.getRequestDispatcher("/pages/auth/login.jsp").forward(req, resp);
    }
}
