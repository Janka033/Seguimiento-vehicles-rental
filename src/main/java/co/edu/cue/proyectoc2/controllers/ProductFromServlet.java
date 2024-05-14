package co.edu.cue.proyectoc2.controllers;

import co.edu.cue.proyectoc2.mapping.UserMapper;
import co.edu.cue.proyectoc2.mapping.dto.UserDto;
import co.edu.cue.proyectoc2.models.User;
import co.edu.cue.proyectoc2.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Servlet implementation class ProductFromServlet
 *
 * Servlet encargado de gestionar la creación y edición de usuarios.
 */
@WebServlet("/users/form")
public class ProductFromServlet extends HttpServlet {

    /** Servicio para operaciones relacionadas con productos */
    @Inject
    private ProductService service;

    /**
     * Método GET para manejar solicitudes de visualización del formulario de productos.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        User user = new User();
        if (id > 0) {
            Optional<UserDto> o = service.porIdUser(id);
            if (o.isPresent())
                user = UserMapper.mapFromDto(o.get());
        }
        req.setAttribute("user", UserMapper.mapFrom(user));
        req.setAttribute("title", req.getAttribute("title") + ": Formulario de productos");
        getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);

    }

    /**
     * Método POST para manejar solicitudes de creación y edición de usuarios.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Map<String, String> mistakes = new HashMap<>();
        if (username == null || username.isBlank()) {
            mistakes.put("username", "The username is required");
        }
        if (email == null || username.isBlank()) {
            mistakes.put("email", "The email is required and it must have an email format.");
        } else if (!email.contains("@")) {
            mistakes.put("email", "The email is required and it must have an email format.");
        }
        if (password == null || username.isBlank()) {
            mistakes.put("password", "The password is required");
        }
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        if (mistakes.isEmpty()) {
            service.saveUser(UserMapper.mapFrom(user));
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("mistakes", mistakes);
            req.setAttribute("user", UserMapper.mapFrom(user));
            req.setAttribute("title", req.getAttribute("title") + ": Users");
            getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);

        }
    }
}
