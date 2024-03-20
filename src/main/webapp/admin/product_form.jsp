	<%@include file="header.jsp" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/blitzer/jquery-ui.css"/>
	
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
			<c:when test="${theProduct != null && not empty theProduct.productId}">
				<h1 class="text-center mb-4">Edit product</h1>
				<c:url var="actionLink" value="manage_product">
					<c:param name="command" value="UPDATE"/>
				</c:url>
			</c:when>
			<c:otherwise>
				<h1 class="text-center mb-4">Create product</h1>
				<c:url var="actionLink" value="manage_product">
					<c:param name="command" value="INSERT"/>
				</c:url>
			</c:otherwise>
		</c:choose>
		
		
		<hr class="mx-auto" style="width:50%">
		
		<div class="d-flex flex-column align-items-center py-5">
			<form id="productForm" action="${actionLink}" method="post" enctype="multipart/form-data" style="width:350px;">
				
				<c:if test="${theProduct != null}">
					<input type="hidden" name="productId" value="${theProduct.productId}">
					<input type="hidden" id="theSelectedCategory" value="${theProduct.category.name}">
				</c:if>
				
				<div class="form-floating mb-3">
					<select name="category" class="form-select" id="selectCategory" aria-label="select category">
						<option value="" selected>Select a category</option>
						<c:forEach items="${categoryList}" var="category">
							<option value="${category.categoryId}">${category.name}</option>
						</c:forEach>
					</select>
					<label for="selectCategory">Category</label>
				</div>
				
				<div class="form-floating mb-3">
					<input name="name" type="text" value="${theProduct.name}" class="form-control" id="inputProductName" placeholder="name">
					<label for="inputProductName">Name</label>
					<div class="invalid-feedback">Please provide a valid name</div>
				</div>
				
				<div class="form-floating mb-3">
					<input name="author" type="text" value="${theProduct.author}" class="form-control" id="inputAuthor" placeholder="author">
					<label for="inputAuthor">Author</label>
					<div class="invalid-feedback">Please provide a valid author</div>
				</div>
				
				<div class="form-floating mb-3">
					<input name="isbn" type="text" value="${theProduct.isbn}" class="form-control" id="inputISBN" placeholder="isbn">
					<label for="inputISBN">ISBN</label>
					<div class="invalid-feedback">Please provide a valid ISBN</div>
				</div>
				
				<div class="form-floating mb-3">
					<input 
						name="publishDate" 
						type="text" 
						value='<fmt:formatDate pattern="MM/dd/yyyy" value="${theProduct.publishDate}" />' 
						class="form-control" 
						id="inputPublishDate" 
						placeholder="date">
					<label for="inputPublishDate">Publish Date</label>
					<div class="invalid-feedback">Please provide a valid publish Date</div>
				</div>
				
				<div class="mb-3">
					<label for="inputBookImage" class="form-label">Image</label>
					<input 
						name="image" 
						onChange="loadFile(event)" 
						class="form-control" 
						type="file" id="inputBookImage" accept="image/*">
				</div>
				
				<div class="form-floating mb-3">
					<input name="price" type="text" value="${theProduct.price}" class="form-control" id="inputPrice" placeholder="price">
					<label for="inputPrice">Price</label>
					<div class="invalid-feedback">Please provide a valid price</div>
				</div>
				
				<div class="form-floating mb-3">
					<textarea 
						name="description" class="form-control" 
						id="inputDescription" placeholder="description">${theProduct.description}</textarea>
					<label for="inputDescription">Description</label>
					<div class="invalid-feedback">Please provide a valid Description</div>
				</div>
				
				<c:if test="${theProduct != null}">
					<img 
						id="preview-image" 
						src="data:image/jpg;base64, ${theProduct.base64Image}"
						style="width:440px; object-fit: cover;"/>
				</c:if>
				<c:if test="${theProduct == null}">
					<img id="preview-image" style="width:440px; object-fit: cover;"/>
				</c:if>
				
				
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary btn-lg mt-4 me-4" style="width:30%;">Save</button>
					<a href="manage_product" class="btn btn-primary btn-lg mt-4" style="width: 30%;">Cancel</a>
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
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
	
	<script type="text/javascript">
		var notification = document.getElementById("notification");
		// console.log(notification.value);
		if (notification != null && notification.value.length > 0) {
			alertify.error(notification.value);
		}
		
		function loadFile(event) {
			if (event.target.files.length > 0) {
				var previewImage = document.getElementById('preview-image');
				previewImage.src = URL.createObjectURL(event.target.files[0]);
			}
		}
		
		
		var selectedCategory = document.getElementById("theSelectedCategory");
		
		if (selectedCategory != null && selectedCategory.value.length > 0) {
			var fieldProductCategory = document.getElementById("selectCategory");
			var options = Array.from(fieldProductCategory.options);
			var optionToSelect = options.find(i => i.text === selectedCategory.value);
			optionToSelect.selected = true;
		}
		
	</script>
	
	<script type="text/javascript">
		$(document).ready(function () {
			$('#inputPublishDate').datepicker();
		});
		
		$(document).ready(function() {
			$("#productForm").validate({
				rules: {
					name: {required: true},
					category: {required: true},
					author: {required: true},
					isbn: {required: true},
					// image: {required: true},
					publishDate: {required: true},
					price: {required: true, number: true},
					description: {required: true},
				},
				highlight: function (element, errorClass, validClass) {
					$(element).addClass("is-invalid").removeClass("is-valid");	
				},
				unhighlight: function (element, errorClass, validClass) {
					$(element).addClass("is-valid").removeClass("is-invalid");	
				},
				errorPlacement: function(error, element) { }
			});
		});
	</script>
	
	
</body>
</html>