package be.bstorm.introjakartaee.servlets;

import be.bstorm.introjakartaee.data.FakeDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/bike")
public class BikeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bikes", FakeDb.bikes);
        req.getRequestDispatcher("/pages/bike/index.jsp").forward(req, resp);
    }
}