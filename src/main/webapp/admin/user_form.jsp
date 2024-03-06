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
	
		<h1 class="text-center mb-4">Create new user</h1>
		
		<hr class="mx-auto" style="width:50%">
		
		<div class="d-flex flex-column align-items-center py-5">
			<form id="userForm" style="width:350px;" action="manage_user" method="post">
				
				<div class="form-floating mb-3">
					<input name="email" type="email" class="form-control" id="inputUserEmail" placeholder="email">
					<label for="inputUserEmail">Email address</label>
					<div class="invalid-feedback">Please provide a valid email</div>
				</div>
				
				<div class="form-floating mb-3">
					<input name="fullName" type="text" class="form-control" id="inputFullname" placeholder="fullname">
					<label for="inputFullname">Full name</label>
					<div class="invalid-feedback">Please provide a valid full name</div>
				</div>
				
				<div class="form-floating mb-3">
					<input name="password" type="password" class="form-control" id="inputPassword" placeholder="pass">
					<label for="inputPassword">Password</label>
					<div class="invalid-feedback">Password is require</div>
				</div>
				
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary btn-lg mt-4 me-4" style="width:30%;">Save</button>
					<a href="manage_user" class="btn btn-primary btn-lg mt-4" style="width: 30%;">Cancel</a>
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
			$("#userForm").validate({
				rules: {
					email: {required: true, email: true},
					fullName: {required: true, minlength: 2},
					password: {required: true, minlength: 2},
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