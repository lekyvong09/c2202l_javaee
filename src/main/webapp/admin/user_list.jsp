	<%@include file="header.jsp" %>
</head>
<body>
	<%@include file="menu.jsp" %>
	
	<div class="container py-5">
		<h1 class="text-center mb-4">User Management</h1>
		
		<hr class="mx-auto" style="width:50%;"/>
		
		<div class="d-flex justify-content-center">
			<a href="manage_user?command=NEW" class="me-4">New user</a>
		</div>
		
		<hr class="mx-auto" style="width:50%;"/>
		
		
		<div class="d-flex justify-content-center py-3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Index</th>
						<th>Id</th>
						<th>Email</th>
						<th>FullName</th>
						<th>Actions</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="user" items="${userList}" varStatus="iterationCount">
					
						<!-- ....manage_user?command=LOAD&userId=${user.userId } -->
						<c:url var="updateLink" value="manage_user">
							<c:param name="command" value="LOAD" />
							<c:param name="userId" value="${user.userId }" />
						</c:url>
					
						<tr>
							<td>${iterationCount.index + 1}</td>
							<td>${user.userId}</td>
							<td>${user.email}</td>
							<td>${user.fullName}</td>
							<td>
								<a href="${updateLink}">Edit</a>
								<span class="mx-3"> | </span>
								<a href="#">Delete</a>
							</td>
						</tr>
					
					</c:forEach>
				</tbody>
				
				
			</table>
		</div>
		
		
	</div>
	
	
	<jsp:include page="footer.jsp" />
</body>
</html>