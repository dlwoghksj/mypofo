<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="../../../resources/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous"></script>
<style>
.divbtn{
	width:100%;
	margin:auto;
	align : center;
	padding : 0px 30px 30px 30px;
}
.listbtn{
	backgroud-color:blue;
	border:none;
	color:black;
	padding:15px;
	align:center;
	margin: auto;
	width:100%;
}
#idcksp{
	color : red;
	display : none;
}
#pwcksp{
	color : red;
	display : none;
}
#datecksp{
	color : red;
	display : none;
}
</style>
</head>

<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-light bg-light">
<jsp:include page="../includes/top.jsp" flush="false"></jsp:include>
</nav>
<div id="layoutSidenav">
<jsp:include page="../includes/sidebar.jsp" flush="false"></jsp:include>
<div id="layoutSidenav_content">
    <main>
       <div class="container-fluid px-4">
           <h1 class="mt-4"><c:out value="${ottInfo.groupCode }"></c:out>그룹의 상세정보 페이지</h1>
           <ol class="breadcrumb mb-4">
               <li class="breadcrumb-item"><a href="../includes/Index">Dashboard</a></li>
               <li class="breadcrumb-item active">Tables</li>
           </ol>
    	<div class="card mb-4">
			<div class="card-body">
				<table class="table table-hover">
				<thead>
					<tr>
						<td scope="col">User Email</td>
					</tr>
				</thead>
				<c:forEach items="${ottuser }" var="ottuser">
					<tr>
						<td scope="row"><a href='/admin/user/UserInfo?userEmail=<c:out value="${ottuser.userEmail}"/>'> 
						<c:out value="${ottuser.userEmail }"></c:out></a></td>
					</tr>
				</c:forEach>
				
				</table>
			</div>
		</div>
		<div class="card mb-4">
			<div class="card-body">
				<table class="table table-hover">
			    <tr> 
			    	<td scope="col">OTT Code</td>
			    	<td scope="row"><c:out value="${ottInfo.ottCode}"></c:out> </td>
			    </tr>
			    <tr> 
			    	<td scope="col">OTT Name</td>
			    	<td scope="row"><c:out value="${ottInfo.ottName}"></c:out> </td>
			    </tr>
			    <tr> 
			    	<td scope="col">Term-Time</td>
			    	<td scope="row"><c:out value="${ottInfo.startDate}"></c:out> ~ <c:out value="${ottInfo.endDate }"></c:out></td>
			    </tr>
				</table>
			</div>
		</div>
		<div class="card mb-4">
			<div class="card-body">
			<form id = "updateForm" action="/admin/ott/OttUpdateInfo?groupCode=<c:out value="${ottInfo.groupCode}"></c:out>" method="post">
			<table class="table">
			<thead>
					<h3>Ott ID/PW</h3>
				</thead>
			<tr>
				<td class="col-md-3">OTT ID </td>
				<td class="col-md-3"><input type="text" id="ottID" name="ottID" value="<c:out value="${ottInfo.ottID }"/>"></td>
				<td class="col-md-3"><span id="idcksp">Ott ID is Null.</span>
			</tr>
			<tr>
				<td scope="col">OTT PW </td>
				<td scope="row"><input type="text" id="ottPW" name="ottPW" value="<c:out value="${ottInfo.ottPW }"/>"></td>
				<td scope="row"><span id="pwcksp">Ott Password is Null.</span>
			</tr>
			<tr>
				<td>payment Date </td>
				<td><input type="date" id="ottPaymentDate" name="ottPaymentDate" value="<c:out value="${ottInfo.ottPaymentDate }"/>"></td>
				<td scope="row"><span id="datecksp">Ott Payment Date is Null.</span>
			</tr>
			
			</table>
			<button type="button" id="updatebtn" class="btn btn-outline-danger" style="width:15%; float:right;">Update Information</button>
			</form>	
		
			</div>
		</div>
	
	<div class="divbtn">
		<button type="button" id="ottList" class="listbtn" onclick="location.href='/admin/ott/<c:out value="${ottInfo.ottName}'"/>"> Go to [<c:out value="${ottInfo.ottName}"></c:out>] List</button>
	</div>
	
</div>
<input type="hidden" value="<c:out value="${updateresult}"/>" name="result" id="result">
</main>   
</div>
</div>
<script>
var idck = false;
var pwck = false;
var dateck = false;

$(document).ready(function(){
	
	
	$("#updatebtn").on('click', function(){
		var id = $('#ottID').val();
		var pw = $('#ottPW').val();
		var date = $('#ottPaymentDate').val();
		
		if(id == ""){
			$('#idcksp').css('display', 'inline-block');
			idck = false;
		}else{
			$('#idcksp').css('display', 'none');
			idck = true;
		}
		if(pw == ""){
			$('#pwcksp').css('display', 'inline-block');
			pwck = false;
		}else{
			$('#pwcksp').css('display', 'none');
			pwck = true;
		}
		if(date == ""){
			$('#datecksp').css('display', 'inline-block');
			dateck = false;
		}else{
			$('#datecksp').css('display', 'none');
			dateck = true;
		}
		
		if(idck && pwck && dateck){
			$("#updateForm").submit();
		}
	});
});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="../../../resources/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="../../../resources/assets/demo/chart-area-demo.js"></script>
<script src="../../../resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="../../../resources/js/datatables-simple-demo.js"></script>
</body>
</html>