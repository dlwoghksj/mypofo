<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<link rel="stylesheet" href="../resources/css/bootstrap.css">

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
<h1 style="text-align:center;">User List</h1>
<div>
<table class="table table-hover" style="text-align: center;">
  <thead>
    <tr>
      <td scope="col" width=300 height=50 style='table-layout:fixed'>User Email</td>
      <td scope="col">User Name</td>
      <td scope="col">User OTT Information</td>
    </tr>
  </thead>
  <c:forEach items="${list}" var="list">
    <tr>
      <td scope="row"><a class="userInfo" href='<c:out value="${list.userEmail}"/>'> 
      <c:out value="${list.userEmail }"></c:out></a></td>
      <td scope="row"><c:out value="${list.userName}"></c:out> </td>
      <td scope="row"><a class="ottInfo" href='<c:out value="${list.groupCode}"/>'> 
      <c:out value="${list.groupCode }"></c:out></a></td>
    </tr>
   </c:forEach>
</table>

</div>

<div class="pageMaker_wrap" >
	<ul class="pageMaker">
		<c:if test="${pageMaker.prev}">
		<li class="pageMaker_btn prev">
		<a href="${pageMaker.pageStart - 1}">이전</a></li>
		</c:if>
	<c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
		<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
		<a href="${num}">${num}</a>
		</li>
	</c:forEach>
	
	<c:if test="${pageMaker.next}">
		<li class="pageMaker_btn next">
			<a href="${pageMaker.pageEnd + 1 }">다음</a>
		</li>
	</c:if>
	</ul>
</div>
	


<form id="actionForm" action="/user/UserList" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
	<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
</form>

<script type="text/javascript">
let actionForm = $('#actionForm');

$(".pageMaker_btn a").on("click", function(e){
	e.preventDefault();
	actionForm.find("input[name='pageNum']").val($(this).attr("href"));
	actionForm.submit();
});

$(".userInfo").on("click", function(e){
	e.preventDefault();
	$("input[name=pageNum]").remove();
	$("input[name=amount]").remove();
	$("input[name=keyword]").remove();
	actionForm.append("<input type='hidden' name='userEmail' value='" + $(this).attr("href") + "'>");
	actionForm.attr("action", "/user/UserInfo");
	actionForm.submit();
});

$(".ottInfo").on("click", function(e){
	e.preventDefault();
	$("input[name=pageNum]").remove();
	$("input[name=amount]").remove();
	$("input[name=keyword]").remove();
	actionForm.append("<input type='hidden' name='groupCode' value='" + $(this).attr("href") + "'>");
	actionForm.attr("action", "/ott/OttInfo");
	actionForm.submit();
});
</script>
</body>
</html>