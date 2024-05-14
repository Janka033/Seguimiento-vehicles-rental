package co.edu.cue.proyectoc2.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import co.edu.cue.proyectoc2.mapping.dto.ProductDto;
import co.edu.cue.proyectoc2.services.LoginService;
import co.edu.cue.proyectoc2.services.LoginServiceSessionImpl;
import co.edu.cue.proyectoc2.services.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Servlet implementation class ProductServlet
 *
 * Servlet encargado de gestionar la visualización del listado de productos.
 */
@WebServlet({"/products.html", "/products"})
public class ProductServlet extends HttpServlet {

    /** Servicio para operaciones relacionadas con productos */
    @Inject
    private ProductService service;

    /**
     * Método GET para manejar solicitudes de visualización del listado de productos.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Obtener la lista de productos
        List<ProductDto> products = service.listarProduct();

        // Verificar si hay un usuario autenticado
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        // Establecer atributos y enviar la solicitud al JSP correspondiente para mostrar el listado de productos
        req.setAttribute("productos",products);
        req.setAttribute("username",usernameOptional);
        req.setAttribute("title",req.getAttribute("title") + ": Listado de productos");
        getServletContext().getRequestDispatcher("/list.jsp").forward(req,resp);
    }
}
