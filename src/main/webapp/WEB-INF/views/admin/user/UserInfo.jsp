<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html xmlns:th = "http://www.thymeleaf.org">
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
           <h1 class="mt-4"><c:out value="${userInfo.userName }"></c:out>님의 상세정보 페이지</h1>
           <ol class="breadcrumb mb-4">
               <li class="breadcrumb-item"><a href="../includes/Index">Dashboard</a></li>
               <li class="breadcrumb-item active">Tables</li>
           </ol>
    	<div class="card mb-4">
			<div class="card-body">
				<table class="table table-hover">
				<tbody>
					<tr>
				      <td scrop="col" width=300>User Email</td>
				      <td scope="row" width=300><c:out value="${userInfo.userEmail}"></c:out> </td>
 					</tr>
 					<tr>
 						<td scope="col">User Name</td>
    					<td scope="row"><c:out value="${userInfo.userName}"></c:out> </td>	
 					</tr>
					 <tr> 
				    	<td scope="col">User Join Date</td>
				    	<td scope="row"><c:out value="${userInfo.joinDate}"></c:out> </td>
				    </tr>
				</tbody>
				</table>
			</div>
		</div>
		<div class="card mb-4">
			<div class="card-body">
				<table class="table table-hover">
			    <c:forEach items="${userottlist }" var="userottlist">
			    <tr> 
			    	<td scope="col" width=300>OTT Group</td>
			    	
			    	<td scope="row" width=300>
			    	<c:set var="groupuse" scope="session" value="${userottlist.groupuse}"/>
					<c:choose>
						<c:when test="${groupuse == 'No Use'}">
							No Use
						</c:when>
						<c:otherwise>
							<a href='/admin/ott/OttInfo?groupCode=<c:out value="${userottlist.groupuse}"/>'> 
			    			<c:out value="${userottlist.groupuse}"></c:out></a>
						</c:otherwise>
					</c:choose>
					</td>
			    </tr>
			   </c:forEach>
			    <tr> 
			    	<td scope="col">Periodic payment date</td>
			    	<td scope="row"> - </td>
			    </tr>
				</table>
			</div>
		</div>
	<div class="divbtn">
		<button type="button" id="userList" class="listbtn" onclick="location.href='/admin/user/UserList'"> Go to User List</button>
	</div>
	
</div>

</main>   
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="../../../resources/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="../../../resources/assets/demo/chart-area-demo.js"></script>
<script src="../../../resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="../../../resources/js/datatables-simple-demo.js"></script>
</body>
</html>