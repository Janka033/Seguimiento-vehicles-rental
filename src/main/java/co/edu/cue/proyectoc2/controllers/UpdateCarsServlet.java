package co.edu.cue.proyectoc2.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import co.edu.cue.proyectoc2.models.Car;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Servlet implementation class UpdateCarsServlet
 *
 * Servlet encargado de gestionar la actualización de productos en el carrito de compras.
 */
@WebServlet("/update-car")
public class UpdateCarsServlet extends HttpServlet {

    /** Instancia del carrito de compras */
    @Inject
    private Car car;

    /**
     * Método POST para manejar solicitudes de actualización de productos en el carrito.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateProductos(req, car);
        updateCantidades(req, car);

        resp.sendRedirect(req.getContextPath() + "/view-car");
    }

    /**
     * Método para actualizar los productos eliminados del carrito.
     *
     * @param request La solicitud HTTP recibida desde el cliente.
     * @param car El carrito de compras.
     */
    private void updateProductos(HttpServletRequest request, Car car) {
        String[] deleteIds = request.getParameterValues("deleteProducts");

        if (deleteIds != null && deleteIds.length > 0) {
            List<String> productIds = Arrays.asList(deleteIds);
            car.removeProductos(productIds);
        }
    }

    /**
     * Método para actualizar las cantidades de productos en el carrito.
     *
     * @param request La solicitud HTTP recibida desde el cliente.
     * @param car El carrito de compras.
     */
    private void updateCantidades(HttpServletRequest request, Car car) {
        Enumeration<String> enumer = request.getParameterNames();

        while (enumer.hasMoreElements()) {
            String paramName = enumer.nextElement();
            if (paramName.startsWith("cant_")) {
                String id = paramName.substring(5);
                String cantidad = request.getParameter(paramName);
                if (cantidad != null) {
                    car.updateCantidad(id, Integer.parseInt(cantidad));
                }
            }
        }
    }
}
