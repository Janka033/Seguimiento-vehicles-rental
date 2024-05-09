package org.jan.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.aguzman.apiservlet.webapp.headers.services.*;
import org.jan.apiservlet.webapp.headers.services.LoginService;
import org.jan.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.jan.apiservlet.webapp.headers.services.ProductoService;
import org.jan.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        List<ProductoDto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos",productos);
        req.setAttribute("username",usernameOptional);
        req.setAttribute("title",req.getAttribute("title") + ": Listado de productos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);
        }
    }
