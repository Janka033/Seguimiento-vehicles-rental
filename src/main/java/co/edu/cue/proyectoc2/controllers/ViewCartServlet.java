package co.edu.cue.proyectoc2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class ViewCartServlet
 *
 * Servlet encargado de gestionar la visualización del carrito de compras.
 */
@WebServlet("/view-car")
public class ViewCartServlet extends HttpServlet {

    /**
     * Método GET para manejar solicitudes de visualización del carrito de compras.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title",req.getAttribute("title") + ": Shopping cart");
        getServletContext().getRequestDispatcher("/car.jsp").forward(req, resp);
    }
}
