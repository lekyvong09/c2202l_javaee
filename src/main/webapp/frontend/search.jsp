	<%@include file="header.jsp" %>
</head>
<body>
	<%@include file="menu.jsp" %>
	<div class="container py-5">
		<%@include file="sidebar.jsp" %>
	
		<div class="container">
			<h2 class="text-center">Search result of ${searchString}</h2>
			
			<div class="row">
			
				<c:forEach var="product" items="${searchResult}">
				
					<div class="col-xl-4 col-md-6 col-sm-12 g-3">
						<div class="card h-100">
							<img src="data:image/jpg;base64, ${product.base64Image}" 
								style="height:100%; width:100%; object-fit:cover;" alt="${product.name}">
							
							<div class="card-body d-flex flex-column">
								<h6 class="text-truncate">${product.name}</h6>
								<div class="text-center">Rating *****</div>
								<div class="text-center">by ${product.author}</div>
								<div class="text-center mb-2">$${product.price}</div>
							</div>
						</div>
					</div>
				
				</c:forEach>
			
			</div>
		
		</div>
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>