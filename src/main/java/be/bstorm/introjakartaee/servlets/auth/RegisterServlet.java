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

@WebServlet(value = "/auth/register")
public class RegisterServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/pages/auth/register.jsp").forward(req, resp);
    }

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
