<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>

<form action="${pageContext.request.contextPath}/login" method="post">
  <div class="row mb-3">  <label for="username" class="col-sm-2 col-form-label">Username</label>
    <div class="col-sm-10">
      <input type="text" name="username" id="username" class="form-control">
    </div>
  </div>
  <div class="row mb-3">
    <label for="password" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
      <input type="password" name="password" id="password" class="form-control">
    </div>
  </div>
  <div class="row">  <div class="offset-sm-2 col-sm-10">  <input type="submit" value="Login" class="btn btn-primary">
  </div>
  </div>
</form>

<jsp:include page="layout/footer.jsp" />
