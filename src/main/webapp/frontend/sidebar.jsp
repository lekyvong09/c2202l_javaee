<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="d-flex flex-column flex-shrink-0 p-3 float-start h-100" style="width:280px;">

	<ul class="nav nav-pills flex-column mb-auto">
		<c:forEach var="category" items="${categoryList}" varStatus="status">
			<li class="nav-item mb-3">
				<a 
					href="product?command=listByCategory&id=${category.categoryId }" 
					class="nav-link link-dark d-flex align-items-center" aria-current="page">
						<c:choose>
							<c:when test="${fn:toLowerCase(category.name) == 'comic' }">
								<img alt="comic" src="<c:url value="/images/comic.svg"/>" class="me-2" style="width:50px; height:50px;">
							</c:when>
							<c:when test="${fn:toLowerCase(category.name) == 'programming' }">
								<img alt="comic" src="<c:url value="/images/programming.svg"/>" class="me-2" style="width:50px; height:50px;">
							</c:when>
							<c:when test="${fn:toLowerCase(category.name) == 'romantic' }">
								<img alt="comic" src="<c:url value="/images/romantic.svg"/>" class="me-2" style="width:50px; height:50px;">
							</c:when>
							<c:when test="${fn:toLowerCase(category.name) == 'fiction' }">
								<img alt="comic" src="<c:url value="/images/fiction.svg"/>" class="me-2" style="width:50px; height:50px;">
							</c:when>
							<c:otherwise>
								<img alt="other" src="<c:url value="/images/book.svg"/>">
							</c:otherwise>
						</c:choose>
				
						<c:out value="${category.name}"/>
				</a>
			</li>
			
			<c:if test="${status.last}">
				<hr>
			</c:if>
			
		</c:forEach>
	
	
	</ul>
</div>
