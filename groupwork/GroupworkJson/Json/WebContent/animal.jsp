<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>My pets</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<style type="text/css">
		.container{
			padding: 50px 0;
		}
		#info{
			margin: 0;
			padding: 20px 0;
		}
		ul#info > li.list-group-item{
			border: none;
			padding: 4px 2px;
		}
	</style>
</head>
<body>
	<div class="container">
		
		<div class="row justify-content-start">
			<header>
				<h2 id="headline">My Pets</h2>
			</header>
		</div>
	
		<div class="row justify-content-start">
			
			<div class="col-3 align-items-start">
			<select  id="name" class="custom-select">
				<option selected>Name</option>
				<option>Meowsy</option>
				<option>Barky</option>
				<option>Purrpaws</option>
			</select>
			</div>
			
			<div class="col-3 align-items-start">
				<select  id="state" class="custom-select">
					<option selected>likes?</option>
					<option>likes</option>
					<option>dislikes</option>
				</select>
			</div>
			
			<div class="col-2 align-items-start">		
				<button id="check" class="btn btn-light">Check</button>
			</div>
			
			<div class="col-2 align-items-start">
				<button id="clear" class="btn btn-danger">Clear</button>
			</div>
	
		</div>
		
		<div class="row justify-content-start">
			<div class="col-12">
				<ul id="info" class="list-group"></ul>
			</div>
		</div>	
	
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>