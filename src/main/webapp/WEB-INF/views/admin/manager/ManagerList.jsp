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
           <h1 class="mt-4" text-align:center;>Manager List</h1>
           <ol class="breadcrumb mb-4">
               <li class="breadcrumb-item"><a href="../includes/Index">Dashboard</a></li>
               <li class="breadcrumb-item active">Tables</li>
           </ol>
    	<div class="card mb-4">
			<div class="card-body">
				<form id="actionForm" action="/admin/manager/ManagerDelete" method="get">
				<table id="datatablesSimple">
				<thead>
					<tr>
				      <td>Manager Code</td>
				      <td>Manager Name</td>
				      <td>Manager Email</td>
				      <td>Position</td>
				      <td></td>
 					</tr>
				</thead>
                       
				<tbody>
				<c:forEach items="${ managerList}" var="list">
			    	<tr>
						<td scope="row"><c:out value="${list.managerCode}"></c:out></td>
						<td scope="row"><c:out value="${list.managerName}"></c:out></td>
						<td scope="row"><c:out value="${list.managerEmail}"></c:out></td>
						<td scope="row">
						<c:set var="position" scope="session" value="${list.position}"/>
						<c:choose>
							<c:when test="${position == '1'}">
				    			Administrator
							</c:when>
							<c:otherwise>
								Manager
							</c:otherwise>
						</c:choose>
						</td>
						<td scope="row">
						<c:set var="position" scope="session" value="${list.position}"/>
						<c:choose>
							<c:when test="${position == '1'}">
				    			
							</c:when>
							<c:otherwise>
								<input type="hidden" name="manager" id="manager" value="${list.managerCode }">
								<button type="button" id="deleteBtn" class="btn btn-outline-danger">Manager Delete</button>
							</c:otherwise>
						</c:choose>
						</td>
				    </tr>
				</c:forEach>
				</tbody>
			</table>
			</form>
		</div>
	</div>
</div>



</main>   
</div>
</div>

<script>


$(document).ready(function(){
	$("#deleteBtn").on("click", function(e){
		e.preventDefault();
		let actionForm = $("#actionForm");
		actionForm.append('<input type="hidden" name="manager" value="${list.managerCode}">');
		actionForm.attr("action", "/admin/manager/ManagerDelete");
		actionForm.attr("method", "post");
		actionForm.submit();
	});
	
	let result = '${result}';
	if(result == 1){
		alert("삭제 완료 ");
	}
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