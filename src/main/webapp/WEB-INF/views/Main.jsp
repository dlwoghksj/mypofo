<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script type="text/javascript" src="../resources/js/bootstrap.js"></script>
<style> 
#dv{
	width : 500px;
	margin:auto;
	border-spacing: 10px;
	align : center;
	padding:50px;
	}
</style>
</head>
<body>
<div id="dv">
<h1 style="text-align:center; margin: 0px 0px 50px 0px">GIRIBOY</h1>
<p>
<button type="button" class="btn btn-primary btn-lg btn-block" onclick="location.href='/Login'" style="margin: 0px 0px 50px 0px">Admin</button>
<p>
<button type="button" class="btn btn-secondary btn-lg btn-block" onclick="location.href='/error'">Guest</button>
<p>

</div>
</body>
</html>

