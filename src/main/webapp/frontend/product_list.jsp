	<%@include file="header.jsp" %>
</head>
<body>
	<%@include file="menu.jsp" %>
	<div class="container py-5">
		<%@include file="sidebar.jsp" %>
	
		<div class="container">
			<div class="row">
				
				<c:forEach var="product" items="${productList}" varStatus="iterationCount">
					<div class="col-xl-4 col-md-6 col-sm-12 py-3 px-2">
						<div class="card shadow-sm h-100">
						
							<img src="data:image/jpg;base64, ${product.base64Image}"
								style="height:100%; width:100%; object-fit:cover;" alt="${product.name}">
							
						</div>
					</div>
				
				</c:forEach>
				
				
			</div>
		</div>
		
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>