package co.edu.cue.proyectoc2.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import co.edu.cue.proyectoc2.services.LoginService;
import co.edu.cue.proyectoc2.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

/**
 * Filtro de autenticación que verifica si el usuario está autorizado para acceder a ciertas páginas.
 */
@WebFilter({"/view-car","/add-car","/update-car","/products/form/*"})
public class LoginFiltro implements Filter {

    /**
     * Verifica si el usuario está autenticado antes de permitir el acceso a ciertas páginas.
     *
     * @param request  el objeto ServletRequest que contiene la solicitud del cliente
     * @param response el objeto ServletResponse que contiene la respuesta que se enviará al cliente
     * @param chain    la cadena de filtros que se invocará después de este filtro
     * @throws IOException      si ocurre un error de E/S
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if (username.isPresent()){
            chain.doFilter(request, response);
        }
        else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Sorry, you are not authorized to enter this page!");
        }
    }
}
