<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<style> 
#tb1{
	border: 2px solid black;
	border-spacing: 10px;
	margin:auto;
	}
</style>
</head>
<body>

<form id="Login_Form" action="/Login.do" method="post">
<table id="tb1" width="500px">
<tbody>
<tr align ="center">
	<td colspan = "3"><h1>MANAGER LOGIN</h1></td>
</tr>
<tr>
	<td style="text-align:center;"><input style="height:30px; width:200px;" type="text" placeholder="아이디" name="managerCode"><p></td>
</tr>
<tr>
	<td style="text-align:center;"><input style="height:30px; width:200px;" type="password" placeholder="비밀번호" name="managerPw"><p></td>
</tr>
<tr>
	<td style="text-align:center;" ><input type="submit" id="Login_Button" style="height:30px; width:200px; border : 2px solid black;" value="LOGIN"></td> 
</tr>
<tr>
	<td style="text-align:center;" ><button type="button" onclick="location.href='/Join'" style="height:30px; width:200px; border : 2px solid black;">JOIN US</button></td>	 
</tr>
<tr>
<td style="text-align:center;"><a href="/">Go to Main</a></td>
</tr>
</tbody>
</table>
</form>

</body>
</html>