package co.edu.cue.proyectoc2.controllers;
import co.edu.cue.proyectoc2.mapping.dto.ProductDto;
import co.edu.cue.proyectoc2.repositories.Repository;
import jakarta.inject.Inject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
@WebServlet({"/showVehicles", "/showVehicles.html"})
public class ShowVehiclesServlet extends HttpServlet{


        @Inject
        private Repository<ProductDto> vehicleDtoService;
        /**
         * Handles GET requests to display a list of vehicles based on filter criteria.
         * @param req  The HttpServletRequest object containing the request from the client.
         * @param resp The HttpServletResponse object for sending the response to the client.
         * @throws ServletException If an error occurs while processing the request.
         * @throws IOException      If an error occurs while sending the response.
         */
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String model = req.getParameter("model");
            String year = req.getParameter("year");
            String type = req.getParameter("type");
            String minPriceReq = req.getParameter("minPrice");
            String maxPriceReq = req.getParameter("maxPrice");
            String availability = req.getParameter("availability");

            // Validate that minPriceReq and maxPriceReq are not null or empty
            BigDecimal minPrice;
            BigDecimal maxPrice = null;

            if (minPriceReq != null && !minPriceReq.isBlank()) {
                minPrice = new BigDecimal(minPriceReq);
            } else {
                minPrice = null;
            }

            if (maxPriceReq != null && !maxPriceReq.isBlank()) {
                maxPrice = new BigDecimal(maxPriceReq);
            }

            List<ProductDto> vehicleDtos = null;
            try {
                vehicleDtos = vehicleDtoService.listar();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            BigDecimal finalMaxPrice = maxPrice;
            List<ProductDto> filteredVehicles = vehicleDtos.stream()
                    .filter(vehicleDto -> {
                        if (type != null && !!type.isEmpty()) {
                            return Objects.equals(vehicleDto.type(),type);
                        }
                        return true;
                    })
                    .filter(vehicleDto -> model == null || model.isEmpty() || Objects.equals(vehicleDto.type(), model))
                    .filter(vehicleDto -> year == null || year.isEmpty() || Objects.equals(vehicleDto.type(), type))
                    .filter(vehicleDto -> minPrice == null || BigDecimal.valueOf(vehicleDto.price()).compareTo(minPrice) >= 0)
                    .filter(vehicleDto -> finalMaxPrice == null || BigDecimal.valueOf(vehicleDto.price()).compareTo(finalMaxPrice) <= 0)
                    .filter(vehicleDto -> { if (availability != null && !availability.isEmpty()) {
                return Objects.equals(vehicleDto.available(), availability);
            }
            // Si la disponibilidad no se especifica en el filtro, incluir todos los vehículos
            return true;
        })
            .toList();

            // Generate HTML response
            resp.setContentType("text/html");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("    <title>List of Vehicles</title>");
                out.println("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
                out.println("    <style>");
                out.println("        body {");
                out.println("            font-family: Arial, sans-serif;");
                out.println("            background-color: #f8f9fa;");
                out.println("            margin: 0;");
                out.println("            padding: 0;");
                out.println("        }");
                out.println("        .heading {");
                out.println("            text-align: center;");
                out.println("            margin-top: 20px;");
                out.println("            font-size: 2em;");
                out.println("            color: #1e91ed;");
                out.println("        }");
                out.println("        .vehicle-table {");
                out.println("            width: 90%;");
                out.println("            margin: 20px auto;");
                out.println("            border-collapse: collapse;");
                out.println("            background-color: #fff;");
                out.println("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                out.println("        }");
                out.println("        .vehicle-table th, .vehicle-table td {");
                out.println("            padding: 10px;");
                out.println("            border: 1px solid #ddd;");
                out.println("            text-align: left;");
                out.println("        }");
                out.println("        .vehicle-table th {");
                out.println("            background-color: #1e91ed;");
                out.println("            color: #fff;");
                out.println("        }");
                out.println("    </style>");
                out.println("</head>");
                out.println("<body>");
                out.println("    <h1 class=\"heading\">List of Vehicles</h1>");
                out.println("    <table class=\"vehicle-table\">");
                out.println("        <thead>");
                out.println("            <tr>");
                out.println("                <th>ID</th>");
                out.println("                <th>Type</th>");
                out.println("                <th>Brand</th>");
                out.println("                <th>Model</th>");
                out.println("                <th>Year</th>");
                out.println("                <th>Price Per Day</th>");
                out.println("                <th>Available</th>");
                out.println("            </tr>");
                out.println("        </thead>");
                out.println("        <tbody>");
                for (ProductDto vehicleDto : filteredVehicles) {
                    out.println("            <tr>");
                    out.println("                <td>" + vehicleDto.id() + "</td>");
                    out.println("                <td>" + vehicleDto.type() + "</td>");
                    out.println("                <td>" + vehicleDto.brand() + "</td>");
                    out.println("                <td>" + vehicleDto.model() + "</td>");
                    out.println("                <td>" + vehicleDto.year() + "</td>");
                    out.println("                <td>" + vehicleDto.price() + "</td>");
                    out.println("                <td>" + vehicleDto.available() + "</td>");
                    out.println("            </tr>");
                }
                out.println("        </tbody>");
                out.println("    </table>");
                out.println("</body>");
                out.println("</html>");
            }
        }

        /**
         * Handles POST requests by delegating to the doGet method.
         *
         * @param req  The HttpServletRequest object containing the request from the client.
         * @param resp The HttpServletResponse object for sending the response to the client.
         * @throws ServletException If an error occurs while processing the request.
         * @throws IOException      If an error occurs while sending the response.
         */
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
    }
