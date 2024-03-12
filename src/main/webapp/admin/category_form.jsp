	<%@include file="header.jsp" %>
	
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
	
	<style type="text/css">
		.error { color: red; }
	</style>
</head>
<body>
	<%@include file="menu.jsp" %>
	
	<c:if test="${message != null}">
		<input id="notification" type="hidden" value="${message}">
	</c:if>
	
	
	<div class="container py-5">
		<c:choose>
			<c:when test="${theCategory != null && not empty theCategory.categoryId}">
				<h1 class="text-center mb-4">Edit category</h1>
				<c:url var="actionLink" value="manage_category">
					<c:param name="command" value="UPDATE"/>
				</c:url>
			</c:when>
			<c:otherwise>
				<h1 class="text-center mb-4">Create category</h1>
				<c:url var="actionLink" value="manage_category">
					<c:param name="command" value="INSERT"/>
				</c:url>
			</c:otherwise>
		</c:choose>
		
		
		<hr class="mx-auto" style="width:50%">
		
		<div class="d-flex flex-column align-items-center py-5">
			<form id="categoryForm" style="width:350px;" action="${actionLink}" method="post">
				
				<c:if test="${theCategory != null}">
					<input type="hidden" name="categoryId" value="${theCategory.categoryId}">
				</c:if>
				
				<div class="form-floating mb-3">
					<input name="name" type="text" value="${theCategory.name}" class="form-control" id="inputCategoryName" placeholder="name">
					<label for="inputCategoryName">Name</label>
					<div class="invalid-feedback">Please provide a valid name</div>
				</div>
				
				
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary btn-lg mt-4 me-4" style="width:30%;">Save</button>
					<a href="manage_category" class="btn btn-primary btn-lg mt-4" style="width: 30%;">Cancel</a>
				</div>
			
			</form>
			
		</div>
		
	</div>
	
	
	<jsp:include page="footer.jsp" />
	
	<!-- JavaScript -->
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
	
	
	<script type="text/javascript">
		var notification = document.getElementById("notification");
		console.log(notification.value);
		if (notification != null && notification.value.length > 0) {
			alertify.error(notification.value);
		}
		
	</script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#categoryForm").validate({
				rules: {
					name: {required: true, minlength: 2},
				},
				highlight: function (element, errorClass, validClass) {
					$(element).addClass("is-invalid").removeClass("is-valid");	
				},
				unhighlight: function (element, errorClass, validClass) {
					$(element).addClass("is-valid").removeClass("is-invalid");	
				},
				errorPlacement: function(error, element) { }
			});
		})
	</script>
	
	
</body>
</html>