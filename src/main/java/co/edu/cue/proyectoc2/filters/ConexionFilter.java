package co.edu.cue.proyectoc2.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import co.edu.cue.proyectoc2.services.ServiceJdbcException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Filtro de conexión que gestiona la transacción de base de datos para cada solicitud.
 */
@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Inject
    @Named("conn")
    private Connection conn;

    /**
     * Realiza el filtrado de la solicitud y gestiona la transacción de base de datos.
     *
     * @param request  el objeto ServletRequest que contiene la solicitud del cliente
     * @param response el objeto ServletResponse que contiene la respuesta que se enviará al cliente
     * @param chain    la cadena de filtros que se invocará después de este filtro
     * @throws IOException      si ocurre un error de E/S
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (Connection connRequest = this.conn){
            if (connRequest.getAutoCommit()){
                connRequest.setAutoCommit(false);
            }
            try {
                chain.doFilter(request,response);
                connRequest.commit();
            }catch (SQLException | ServiceJdbcException e){
                connRequest.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
