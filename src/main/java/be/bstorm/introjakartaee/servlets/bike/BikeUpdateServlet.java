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

@WebServlet(value = "/bike/update")
public class BikeUpdateServlet extends HttpServlet {

    @Inject
    private BikeDao bikeDao;

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

