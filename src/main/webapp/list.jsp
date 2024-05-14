
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:if test="${requestScope.username.isPresent()}">
<div class="alert alert-info">Hola ${username.get()}, welcom</div>
</c:if>
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>type</th>
        <th>brand</th>
        <th>model</th>
        <th>year</th>
        <th>category</th>
        <c:if test="${requestScope.username.present}">
        <th>price</th>
        <th>add</th>

        </c:if>
    </tr>
    <c:forEach items="${requestScope.productos}" var="p">
    <tr>
        <td><c:out value="${p.id}"/></td>
        <td><c:out value="${p.type}"/></td>
        <td><c:out value="${p.brand}"/></td>
        <td><c:out value="${p.model}"/></td>
        <td><c:out value="${p.year}"/></td>
        <td><c:out value="${p.category.name}"/></td>
        <c:if test="${requestScope.username.present}">
        <td><c:out value="${p.price}"/></td>
        <td><a class="btn btn-sm btn-primary" href="<c:out value="${pageContext.request.contextPath}"/>/add-car?id=<c:out value="${p.id}"/>">add to cart</a></td>
        </c:if>
    </tr>
</c:forEach>
</table>
<p>${applicationScope.mensaje}</p>
<p>${requestScope.mensaje}</p>
<jsp:include page="layout/footer.jsp"/>