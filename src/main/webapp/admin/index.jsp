	<%@include file="header.jsp" %>
</head>
<body>
	<%@include file="menu.jsp" %>
	
	<div class="container py-5">
	
		<h1 class="text-center mb-4">Administrative page</h1>
		
		<hr class="mx-auto" style="width:50%">
		
		<h2 class="text-center">Quick action:</h2>
		<div class="d-flex justify-content-center">
			<a href="#" class="me-4">New book</a>
			<a href="user_form.jsp" class="me-4">New user</a>
			<a href="#" class="me-4">New category</a>
			<a href="#" class="me-4">New Customer</a>
		</div>
		
		<hr class="mx-auto" style="width:50%">
		
		<h2 class="text-center">TODO: Statistic</h2>
		
	</div>
	
	
	<jsp:include page="footer.jsp" />
</body>
</html>