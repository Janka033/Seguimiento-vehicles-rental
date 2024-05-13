
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:if test="${requestScope.username.isPresent()}">
<div class="alert alert-info">Hola ${username.get()}, bienvenido</div>
</c:if>
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>tipo</th>
        <th>marca</th>
        <th>modelo</th>
        <th>año</th>
        <th>categoría</th>
        <c:if test="${requestScope.username.present}">
        <th>precio</th>
        <th>agregar</th>

        </c:if>
    </tr>
    <c:forEach items="${requestScope.productos}" var="p">
    <tr>
        <td><c:out value="${p.id}"/></td>
        <td><c:out value="${p.tipo}"/></td>
        <td><c:out value="${p.marca}"/></td>
        <td><c:out value="${p.modelo}"/></td>
        <td><c:out value="${p.anio}"/></td>
        <td><c:out value="${p.categoria.nombre}"/></td>
        <c:if test="${requestScope.username.present}">
        <td><c:out value="${p.precio}"/></td>
        <td><a class="btn btn-sm btn-primary" href="<c:out value="${pageContext.request.contextPath}"/>/agregar-carro?id=<c:out value="${p.id}"/>">Agregar al carro</a></td>
        </c:if>
    </tr>
</c:forEach>
</table>
<p>${applicationScope.mensaje}</p>
<p>${requestScope.mensaje}</p>
<jsp:include page="layout/footer.jsp"/>