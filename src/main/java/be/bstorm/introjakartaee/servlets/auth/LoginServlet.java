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

@WebServlet(value = "/auth/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/auth/login.jsp").forward(req, resp);
    }

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
