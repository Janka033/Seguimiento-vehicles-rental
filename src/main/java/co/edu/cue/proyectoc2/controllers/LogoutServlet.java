package co.edu.cue.proyectoc2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import co.edu.cue.proyectoc2.services.LoginService;
import co.edu.cue.proyectoc2.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

/**
 * Servlet implementation class LogoutServlet
 *
 * Servlet encargado de gestionar la finalización de la sesión de un usuario.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Método GET para manejar solicitudes de cierre de sesión.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);
        if (username.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
