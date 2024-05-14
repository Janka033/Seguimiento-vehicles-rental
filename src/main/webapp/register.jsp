<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/users/form" method="post">
    <div class="row mb-2">
        <label for="username" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4">
            <input type="text" name="username" id="username" value="${user.name}" class="form-control">
        </div>
        <c:if test="${mistakes != null && mistakes.containsKey('username')}">
            <div style="color:red;">${mistakes.username}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="email" class="col-form-label col-sm-2">Email</label>
        <div class="col-sm-4">
            <input type="text" name="email" id="email" value="${user.email}" class="form-control">
        </div>
        <c:if test="${mistakes != null && !empty mistakes.containsKey('email')}">
            <div style="color:red;">${mistakes.email}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="password" class="col-form-label col-sm-2">password</label>
        <div class="col-sm-4">
            <input type="text" name="password" id="password" value="${user.password}" class="form-control">
        </div>
        <c:if test="${mistakes != null && !empty mistakes.containsKey('password')}">
            <div style="color:red;">${mistakes.password}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="Create">
        </div>
    </div>
    <input hidden="hidden" name="id" value="${user.id}">
</form>
<jsp:include page="layout/footer.jsp"/>

