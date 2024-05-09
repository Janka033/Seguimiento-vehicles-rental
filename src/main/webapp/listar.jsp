
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:if test="${requestScope.username.isPresent()}">
<div class="alert alert-info">Hola ${username.get()}, bienvenido</div>
<a class="btn btn-primary my-2" href="<c:out value="${pageContext.request.contextPath}" />/productos/form">Crear [+]</a>
</c:if>
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>tipo</th>
        <c:if test="${requestScope.username.present}">
        <th>precio</th>
        <th>agregar</th>
        <th>editar</th>
        <th>eliminar</th>

        </c:if>
    </tr>
    <c:forEach items="${requestScope.productos}" var="p">
    <tr>
        <td><c:out value="${p.id}"/></td>
        <td><c:out value="${p.nombre}"/></td>
        <td><c:out value="${p.categoria.nombre}"/></td>
        <c:if test="${requestScope.username.present}">
        <td><c:out value="${p.precio}"/></td>
        <td><a class="btn btn-sm btn-primary" href="<c:out value="${pageContext.request.contextPath}"/>/agregar-carro?id=<c:out value="${p.id}"/>">Agregar al carro</a></td>
        <td><a class="btn btn-sm btn-success" href="<c:out value="${pageContext.request.contextPath}"/>/productos/form?id=<c:out value="${p.id}"/>">Editar</a></td>
        <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Estas seguro que desea eliminar?')"
                href="<c:out value="${pageContext.request.contextPath}"/>/productos/eliminar?id=<<c:out value="${p.id}"/>">Eliminar</a></td>

        </c:if>
    </tr>
</c:forEach>
</table>
<p>${applicationScope.mensaje}</p>
<p>${requestScope.mensaje}</p>
<jsp:include page="layout/footer.jsp"/>