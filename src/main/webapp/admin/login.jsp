<%@include file="header.jsp" %>

	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>

</head>
<body>
	<%-- <%@include file="menu.jsp" %> --%>
	
	<c:if test="${message != null}">
		<input id="notification" type="hidden" value="${message}">
	</c:if>
	
	<div class="container py-5">
		<form id="adminLoginForm" method="post" action="login" class="d-flex flex-column align-items-center">
			<div style="width:350px;">
				<h2 class="text-center"></h2>
				<div class="form-floating mb-3">
					<input name="email" type="email" class="form-control" id="floatingInput" placeholder="abc">
					<label for="floatingInput">Email address</label>
				</div>
				<div class="form-floating mb-3">
					<input name="password" type="password" class="form-control" id="floatingPassword" placeholder="pass">
					<label for="floatingPassword">Password</label>
				</div>
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary btn-lg mt-4 w-50">Login</button>
				</div>
			</div>
			
		</form>
	
	</div>
	
	<jsp:include page="footer.jsp"/>
	
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
			$("#adminLoginForm").validate({
				rules: {
					email: {required: true, email: true},
					password: {required: true},
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