<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:choose>
    <c:when test="${carro.items.isEmpty()}">
        <div class="alert alert-warning">¡Lo sentimos no hay productos en el carro de compras!</div>
    </c:when>
    <c:otherwise>
        <form name="formcarro" action="${pageContext.request.contextPath}/actualizar-carro" method="post">
            <table class="table table-hover table-striped">
                <tr>
                    <th>id</th>
                    <th>tipo</th>
                    <th>marca</th>
                    <th>modelo</th>
                    <th>año</th>
                    <th>categoría</th>
                    <th>precio por dia</th>
                    <th>Dias de uso</th>
                    <th>total</th>
                    <th>borrar</th>
                </tr>
                <c:forEach items="${carro.items}" var="item">
                    <tr>
                        <td>${item.producto.id}</td>
                        <td>${item.producto.tipo}</td>
                        <td>${item.producto.marca}</td>
                        <td>${item.producto.modelo}</td>
                        <td>${item.producto.anio}</td>
                        <td>${item.producto.categoria.nombre}</td>
                        <td>${item.producto.precio}</td>
                        <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}"/></td>
                        <td>${item.total}</td>
                        <td><input type="checkbox" value="${item.producto.id}" name="deleteProductos"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" style="text-align: right">Total:</td>
                    <td>${carro.total}</td>
                </tr>
            </table>
            <a class="btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a>
        </form>
    </c:otherwise>
</c:choose>
<div class="my-2">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/productos">seguir comprando</a>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">volver</a>
</div>
<jsp:include page="layout/footer.jsp"/>
