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

@WebServlet(value = "/bike/create")
public class BikeCreateServlet extends HttpServlet {

    @Inject
    private BikeDao bikeDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/bike/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int horsePower = Integer.parseInt(req.getParameter("horsePower"));
        String imageUrl = req.getParameter("imageUrl");

        Bike bike = new Bike(brand, model, horsePower, imageUrl);

        bikeDao.save(bike);

        resp.sendRedirect(req.getContextPath() + "/bike");
    }
}
