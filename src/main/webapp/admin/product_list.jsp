	<%@include file="header.jsp" %>
</head>
<body>
	<%@include file="menu.jsp" %>
	
	<div class="container py-5">
		<h1 class="text-center mb-4">Product Management</h1>
		
		<hr class="mx-auto" style="width:50%;"/>
		
		<div class="d-flex justify-content-center">
			<a href="manage_product?command=NEW" class="me-4">New product</a>
		</div>
		
		<hr class="mx-auto" style="width:50%;"/>
		
		
		<div class="d-flex justify-content-center py-3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Index</th>
						<th>Id</th>
						<th>Image</th>
						<th>Title</th>
						<th>Author</th>
						<th>Category</th>
						<th>Price</th>
						<th>Last Updated</th>
						<th>Actions</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="product" items="${productList}" varStatus="iterationCount">
					
						<c:url var="updateLink" value="manage_product">
							<c:param name="command" value="LOAD" />
							<c:param name="productId" value="${product.productId }" />
						</c:url>
					
						<tr>
							<td>${iterationCount.index + 1}</td>
							<td>${product.productId}</td>
							<td>
								<img src="data:image/jpg;base64, ${product.base64Image}"/>
							</td>
							<td>${product.name}</td>
							<td>${product.author}</td>
							<td>${product.category.name}</td>
							<td>${product.price}</td>
							<td>${product.lastUpdateTime}</td>
							<td>
								<a href="${updateLink}">Edit</a>
								<span class="mx-3"> | </span>
								<a href="javascript:confirmDelete(${product.productId})">Delete</a>
							</td>
						</tr>
					
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(productId) {
			if (confirm('Are you sure')) {
				window.location = 'manage_product?command=DELETE&productId=' + productId;
			}
		}
	</script>
</body>
</html>