	<%@include file="header.jsp" %>
</head>
<body>
	<%@include file="menu.jsp" %>
	
	<div class="container py-5">
		<h1 class="text-center mb-4">Category Management</h1>
		
		<hr class="mx-auto" style="width:50%;"/>
		
		<div class="d-flex justify-content-center">
			<a href="manage_category?command=NEW" class="me-4">New category</a>
		</div>
		
		<hr class="mx-auto" style="width:50%;"/>
		
		
		<div class="d-flex justify-content-center py-3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Index</th>
						<th>Id</th>
						<th>Name</th>
						<th>Actions</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="category" items="${categoryList}" varStatus="iterationCount">
					
						<c:url var="updateLink" value="manage_category">
							<c:param name="command" value="LOAD" />
							<c:param name="categoryId" value="${category.categoryId }" />
						</c:url>
					
						<tr>
							<td>${iterationCount.index + 1}</td>
							<td>${category.categoryId}</td>
							<td>${category.name}</td>
							<td>
								<a href="${updateLink}">Edit</a>
								<span class="mx-3"> | </span>
								<a href="javascript:confirmDelete(${category.categoryId})">Delete</a>
							</td>
						</tr>
					
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(categoryId) {
			if (confirm('Are you sure')) {
				window.location = 'manage_category?command=DELETE&categoryId=' + categoryId;
			}
		}
	</script>
</body>
</html>