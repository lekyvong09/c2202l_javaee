<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<div class="d-flex justify-content-between align-items-center">
		<!-- <img src="images/logo.png" /> -->
		<%-- <img src="<%=pageContext.getServletContext().getInitParameter("baseURL")%>/images/logo.png" /> --%>
		<img alt="img" src="<c:url value="/images/logo.png" />">
		
		<div class="d-flex justify-content-end vw-50">
			<input class="form-control w-25 me-3" type="text" name="keyword" size="50" />
			<input class="btn btn-primary me-5" type="button" value="Search" />
			
			<a href="#" class="text-decoration-none text-dark">
				<i class="fas fa-shopping-cart fa-2x me-3"></i>
			</a>
			
			<input class="btn btn-outline-secondary me-3" type="button" value="Login" />
			
			<input class="btn btn-outline-secondary" type="button" value="Sign up" />
		</div>
	</div>
</div>