package be.bstorm.introjakartaee.servlets.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet gérant la déconnexion des utilisateurs.
 *
 * Invalide la session utilisateur et redirige vers la page d'accueil.
 *
 * Accès: accessibles à tous (aucune restriction d'accès)
 * Route:
 * - GET /auth/logout : déconnecte l'utilisateur
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebServlet(value = "/auth/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Traite la déconnexion de l'utilisateur.
     *
     * Invalide la session actuelle, supprimant toutes les données de session
     * incluant les informations de l'utilisateur connecté.
     * Redirige ensuite vers la page d'accueil.
     *
     * @param req la requête HTTP
     * @param resp la réponse HTTP
     * @throws ServletException si une erreur servlet se produit
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

