package co.edu.cue.proyectoc2.controllers;

import co.edu.cue.proyectoc2.mapping.dto.UserDto;
import co.edu.cue.proyectoc2.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ProductUserServlet
 *
 * Servlet encargado de gestionar la visualización del listado de usuarios.
 */
@WebServlet({"/users"})
public class ProductUserServlet extends HttpServlet {

    /** Servicio para operaciones relacionadas con productos */
    @Inject
    private ProductService service;

    /**
     * Método GET para manejar solicitudes de visualización del listado de usuarios.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Obtener la lista de usuarios
        List<UserDto> users = service.listarUser();

        // Establecer atributos y enviar la solicitud al JSP correspondiente para mostrar el listado de usuarios
        req.setAttribute("user",users);
        req.setAttribute("title",req.getAttribute("title") + ": Register");
        getServletContext().getRequestDispatcher("/register.jsp").forward(req,resp);
    }
}
