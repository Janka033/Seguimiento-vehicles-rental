package co.edu.cue.proyectoc2.controllers;

import co.edu.cue.proyectoc2.mapping.dto.UserDto;
import co.edu.cue.proyectoc2.services.LoginService;
import co.edu.cue.proyectoc2.services.LoginServiceSessionImpl;
import co.edu.cue.proyectoc2.services.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/register","/register.html"})
public class RegisterServlet extends HttpServlet {
    @Inject
    private UserService service;
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
                out.println("        <h1>Hola " + usernameOptional.get() + " you have register in successfully!</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.jsp'>Return</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/login'>login</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("title",req.getAttribute("title") + ": Register");
            getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<UserDto> usuarioOptional = service.login(username,password);

        if (usuarioOptional.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/register.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry, you are not authorized to enter this page.!");
        }
    }
}

