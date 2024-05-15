<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:choose>
    <c:when test="${car.items.isEmpty()}">
        <div class="alert alert-warning">¡Lo sentimos no hay productos en el carro de compras!</div>
    </c:when>
    <c:otherwise>
        <form name="formcarro" action="${pageContext.request.contextPath}/update-car" method="post">
            <table class="table table-hover table-striped">
                <tr>
                    <th>id</th>
                    <th>type</th>
                    <th>brand</th>
                    <th>model</th>
                    <th>year</th>
                    <th>category</th>
                    <th>precio por dia</th>
                    <th>Dias de uso</th>
                    <th>total</th>
                    <th>borrar</th>
                </tr>
                <c:forEach items="${car.items}" var="item">
                    <tr>
                        <td>${item.product.id}</td>
                        <td>${item.product.type}</td>
                        <td>${item.product.brand}</td>
                        <td>${item.product.model}</td>
                        <td>${item.product.year}</td>
                        <td>${item.product.category.name}</td>
                        <td>${item.product.price}</td>
                        <td><input type="text" size="4" name="cant_${item.product.id}" value="${item.cantidad}"/></td>
                        <td>${item.total}</td>
                        <td><input type="checkbox" value="${item.product.id}" name="deleteProducts"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" style="text-align: right">Total:</td>
                    <td>${car.total}</td>
                </tr>
            </table>
            <a class="btn btn-primary" href="javascript:document.formcarro.submit();">Update</a>
        </form>
    </c:otherwise>
</c:choose>
<div class="my-2">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/products">keep buying</a>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">return</a>
</div>
<jsp:include page="layout/footer.jsp"/>
