package be.bstorm.introjakartaee.filters;

import be.bstorm.introjakartaee.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Filtre d'authentification et d'autorisation pour l'application.
 *
 * Contrôle l'accès aux différentes ressources en fonction du statut de connexion
 * et du rôle de l'utilisateur (ADMIN ou USER).
 *
 * Les routes sont catégorisées comme suit:
 * - Routes publiques: accessibles à tous
 * - Routes d'authentification: réservées aux utilisateurs non connectés
 * - Routes administrateur: réservées aux administrateurs uniquement
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    /**
     * Applique les règles d'authentification et d'autorisation à chaque requête.
     *
     * @param request la requête HTTP
     * @param response la réponse HTTP
     * @param chain la chaîne de filtres
     * @throws IOException si une erreur d'entrée/sortie se produit
     * @throws ServletException si une erreur servlet se produit
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        // Récupérer l'utilisateur de la session
        User user = (User) httpRequest.getSession().getAttribute("user");

        // Routes accessibles à TOUS
        if (isPublicRoute(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Routes pour les UTILISATEURS NON CONNECTÉS UNIQUEMENT
        if (isAuthRoute(path)) {
            if (user != null) {
                // Déjà connecté, rediriger vers les motos
                httpResponse.sendRedirect(contextPath + "/bike");
                return;
            }
            chain.doFilter(request, response);
            return;
        }

        // Routes pour les ADMINISTRATEURS UNIQUEMENT
        if (isAdminRoute(path)) {
            if (user == null) {
                // Non connecté, rediriger vers login
                httpResponse.sendRedirect(contextPath + "/auth/login");
                return;
            }
            if (!user.getRole().name().equals("ADMIN")) {
                // Connecté mais pas admin, accès refusé
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès réservé aux administrateurs");
                return;
            }
            chain.doFilter(request, response);
            return;
        }

        // Routes normales (motos, accueil, etc.)
        chain.doFilter(request, response);
    }

    /**
     * Détermine si une route est publique et accessible à tous.
     *
     * Routes publiques incluent:
     * - La page d'accueil
     * - La liste des motos
     * - Les détails d'une moto
     * - Les ressources statiques (CSS, JS, images)
     *
     * @param path le chemin de la requête
     * @return true si la route est publique, false sinon
     */
    private boolean isPublicRoute(String path) {
        return path.equals("/") || 
               path.isEmpty() ||
               path.equals("/index.jsp") ||
               path.startsWith("/bike") && path.contains("/details") ||
               path.equals("/bike") ||
               path.equals("/bike/") ||
               path.startsWith("/resources/") ||
               path.startsWith("/WEB-INF/") ||
               path.endsWith(".css") ||
               path.endsWith(".js") ||
               path.endsWith(".jpg") ||
               path.endsWith(".png") ||
               path.endsWith(".gif");
    }

    /**
     * Détermine si une route est une route d'authentification.
     *
     * Ces routes sont accessibles UNIQUEMENT aux utilisateurs non connectés.
     * Les utilisateurs connectés sont redirigés vers la liste des motos.
     *
     * Routes d'authentification incluent:
     * - Login
     * - Register
     *
     * @param path le chemin de la requête
     * @return true si c'est une route d'authentification, false sinon
     */
    private boolean isAuthRoute(String path) {
        return path.equals("/auth/login") ||
               path.equals("/auth/register");
    }

    /**
     * Détermine si une route est réservée aux administrateurs.
     *
     * Ces routes sont accessibles UNIQUEMENT aux utilisateurs avec le rôle ADMIN.
     * Les autres utilisateurs reçoivent une erreur 403 Forbidden.
     * Les utilisateurs non connectés sont redirigés vers le login.
     *
     * Routes administrateur incluent:
     * - Création d'une moto
     * - Modification d'une moto
     * - Suppression d'une moto
     *
     * @param path le chemin de la requête
     * @return true si c'est une route administrative, false sinon
     */
    private boolean isAdminRoute(String path) {
        return path.equals("/bike/create") ||
               path.equals("/bike/update") ||
               path.equals("/bike/delete");
    }
}
