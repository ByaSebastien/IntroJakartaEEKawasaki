package be.bstorm.introjakartaee.servlets;

import be.bstorm.introjakartaee.data.FakeDb;
import be.bstorm.introjakartaee.models.Bike;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/bike/details")
public class BikeDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/bike");
            return;
        }
        
        try {
            Integer id = Integer.parseInt(idParam);
            Bike bike = FakeDb.bikes.stream()
                    .filter(b -> b.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            
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

