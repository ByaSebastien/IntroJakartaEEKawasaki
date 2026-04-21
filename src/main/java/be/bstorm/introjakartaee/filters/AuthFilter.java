package be.bstorm.introjakartaee.filters;

import be.bstorm.introjakartaee.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

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
     * Routes publiques accessibles à TOUS
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
     * Routes d'authentification (login/register) - UNIQUEMENT pour non-connecté
     */
    private boolean isAuthRoute(String path) {
        return path.equals("/auth/login") ||
               path.equals("/auth/register");
    }

    /**
     * Routes administrateur (create/update/delete) - UNIQUEMENT pour ADMIN
     */
    private boolean isAdminRoute(String path) {
        return path.equals("/bike/create") ||
               path.equals("/bike/update") ||
               path.equals("/bike/delete");
    }
}
