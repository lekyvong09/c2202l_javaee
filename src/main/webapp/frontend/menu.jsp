<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<div class="d-flex justify-content-between align-items-center">
		<!-- <img src="images/logo.png" /> -->
		<%-- <img src="<%=pageContext.getServletContext().getInitParameter("baseURL")%>/images/logo.png" /> --%>
		
		<a href="<c:url value="/" />">
			<img alt="img" src="<c:url value="/images/logo.png" />">
		</a>
		
		
		<div class="d-flex justify-content-end vw-50">
			<form action="<c:url value="/" />" method="post" class="me-5">
				<div class="input-group">
					<input class="form-control" type="text" name="keyword" size="30">
					<button class="btn btn-outline-secondary" type="submit" id="product-search-name">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</form>
			
			<a href="#" class="text-decoration-none text-dark">
				<i class="fas fa-shopping-cart fa-2x me-3"></i>
			</a>
			
			<input class="btn btn-outline-secondary me-3" type="button" value="Login" />
			
			<input class="btn btn-outline-secondary" type="button" value="Sign up" />
		</div>
	</div>
</div>