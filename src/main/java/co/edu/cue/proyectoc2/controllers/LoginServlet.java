package co.edu.cue.proyectoc2.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import co.edu.cue.proyectoc2.mapping.dto.UserDto;
import co.edu.cue.proyectoc2.services.LoginService;
import co.edu.cue.proyectoc2.services.LoginServiceSessionImpl;
import co.edu.cue.proyectoc2.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * Servlet implementation class LoginServlet
 *
 * Servlet encargado de gestionar el inicio de sesión de los usuarios.
 */
@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    /** Instancia del servicio de usuario */
    @Inject
    private UserService service;

    /**
     * Método GET para manejar solicitudes de inicio de sesión.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Hello " + usernameOptional.get() + "</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Hola " + usernameOptional.get() + " you have logged in successfully!</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.jsp'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("title",req.getAttribute("title") + ": Log in");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    /**
     * Método POST para manejar solicitudes de inicio de sesión.
     *
     * @param req La solicitud HTTP recibida desde el cliente.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<UserDto> usuarioOptional = service.login(username,password);

        if (usuarioOptional.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry, you are not authorized to enter this page.!");
        }
    }
}
