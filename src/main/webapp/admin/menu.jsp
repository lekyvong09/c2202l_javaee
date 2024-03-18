<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<div class="d-flex justify-content-between align-items-center">

		<a href="<c:url value="/admin" />">
			<img alt="img" src="<c:url value="/images/logo.png" />">
		</a>
		
		
		<c:if test="${sessionScope.userEmail != null}">
			<div class="d-flex justify-content-end align-items-center vw-50">
				Welcome, <c:out value="${sessionScope.userEmail}"></c:out>
				<span class="mx-3"> | </span>
				<a href="login" class="btn btn-outline-secondary ms-4" type="button">Logout</a>
			</div>
		</c:if>
		
		<c:if test="${sessionScope.userEmail == null}">
			<div class="d-flex justify-content-end align-items-center vw-50">
				<a href="login.jsp" class="btn btn-outline-secondary ms-4" type="button">Login</a>
			</div>
		</c:if>
		
	</div>
	
	
	<div class="d-flex justify-content-center">
		<a href="manage_user?command=LIST" class="fw-bold">Users</a>
		<span class="mx-3"> | </span>
		<a href="manage_category?command=LIST" class="fw-bold">Categories</a>
		<span class="mx-3"> | </span>
		<a href="#" class="fw-bold">Books</a>
		<span class="mx-3"> | </span>
		<a href="#" class="fw-bold">Customers</a>
		<span class="mx-3"> | </span>
		<a href="#" class="fw-bold">Reviews</a>
		<span class="mx-3"> | </span>
		<a href="#" class="fw-bold">Orders</a>
	</div>
	
	
</div>