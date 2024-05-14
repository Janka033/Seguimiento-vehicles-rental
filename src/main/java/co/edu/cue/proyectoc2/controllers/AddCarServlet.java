package co.edu.cue.proyectoc2.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import co.edu.cue.proyectoc2.mapping.dto.ProductDto;
import co.edu.cue.proyectoc2.models.Car;
import co.edu.cue.proyectoc2.models.ItemCar;
import co.edu.cue.proyectoc2.services.ProductService;

import java.io.IOException;
import java.util.Optional;

/**
 * Servlet implementation class AddCarServlet
 *
 * Servlet encargado de agregar un producto al carrito de compras.
 */
@WebServlet("/add-car")
public class AddCarServlet extends HttpServlet {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Instancia del carrito de compras */
    @Inject
    private Car car;

    /** Servicio para operaciones relacionadas con productos */
    @Inject
    private ProductService service;

    /**
     * Método GET para manejar solicitudes de agregar un producto al carrito.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener el ID del producto de la solicitud
        Long id = Long.parseLong(req.getParameter("id"));

        // Buscar el producto por su ID en el servicio
        Optional<ProductDto> product = service.porIdProduct(id);

        // Si el producto está presente, agregarlo al carrito
        if (product.isPresent()) {
            ItemCar item = new ItemCar(1, product.get());
            car.addItemCarro(item);
        }

        // Redireccionar al usuario a la página de visualización del carrito
        resp.sendRedirect(req.getContextPath() + "/view-car");
    }
}
