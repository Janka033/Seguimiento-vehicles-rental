package co.edu.cue.proyectoc2.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Listener de la aplicación que realiza acciones durante el ciclo de vida de la aplicación web.
 */
@WebListener
public class ApplicationListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    /**
     * Se ejecuta cuando se inicializa el contexto de la aplicación.
     *
     * @param sce el evento que representa la inicialización del contexto de la aplicación
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Initializing the application!");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "some global value of the app");
    }

    /**
     * Se ejecuta cuando se destruye el contexto de la aplicación.
     *
     * @param sce el evento que representa la destrucción del contexto de la aplicación
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destroying the application!");
    }

    /**
     * Se ejecuta cuando se inicializa una solicitud.
     *
     * @param sre el evento que representa la inicialización de la solicitud
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Initializing request");
        sre.getServletRequest().setAttribute("mensaje", "saving some value for the request");
        sre.getServletRequest().setAttribute("title", "Servlet Catalog");
    }

    /**
     * Se ejecuta cuando se destruye una solicitud.
     *
     * @param sre el evento que representa la destrucción de la solicitud
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destroying request");
    }

    /**
     * Se ejecuta cuando se crea una sesión HTTP.
     *
     * @param se el evento que representa la creación de la sesión HTTP
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Initializing http session");
    }

    /**
     * Se ejecuta cuando se destruye una sesión HTTP.
     *
     * @param se el evento que representa la destrucción de la sesión HTTP
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destroying http session");
    }
}
