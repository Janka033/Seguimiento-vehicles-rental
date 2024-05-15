<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Veloce Rentals</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .heading {
            text-align: center;
            margin-top: 20px;
            font-size: 2em;
            color: #1e91ed;
        }
        .filter-form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        .filter-form .form-group {
            margin-bottom: 15px;
        }
        .filter-button {
            background-color: #1e91ed;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 1em;
        }
        .filter-button:hover {
            background-color: #1e91ed;
        }
        .vehicle-table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .vehicle-table th, .vehicle-table td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .vehicle-table th {
            background-color: #1e91ed;
            color: #fff;
        }
        .btn-primary {
            background-color: #1e91ed;
            color: #fff;
        }


    </style>
</head>
<body>

<h1 class="heading">Filter Vehicles</h1>
<form action="${pageContext.request.contextPath}/showVehicles" method="get" class="filter-form">
    <div class="mb-3">
        <label for="type" class="form-label">Type:</label>
        <select name="type" id="type" class="form-select">
            <option value="Deportiva">Naked</option>
            <option value="Naked">Deportiva</option>
            <option value="Scrambler">Scrambler</option>
            <option value="Compacto">Compacto</option>
            <option value="Deportivo">Deportiva</option>
            <option value="Electrico">Electrico</option>
        </select>
    </div>
    <div class="mb-3">
        <label for="minPrice" class="form-label">Min Price:</label>
        <input type="number" name="minPrice" id="minPrice" step="1000" class="form-control">
    </div>
    <div class="mb-3">
        <label for="maxPrice" class="form-label">Max Price:</label>
        <input type="number" name="maxPrice" id="maxPrice" step="1000" class="form-control">
    </div>
    <div class="mb-3">
        <label for="availability" class="form-label">Availability:</label>
        <select name="availability" id="availability" class="form-select">
            <option value="Disponible">Available</option>
            <option value="No Disponible">Rented</option>
        </select>
    </div>
    <div class="text-center">
        <button type="submit" class="btn-primary">Filter</button>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>

