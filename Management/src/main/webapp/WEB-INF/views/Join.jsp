<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style> 
#tb1{
	border: 2px solid black;
	border-spacing: 10px;
	margin:auto;
	}
#check1{
	color : blue;
	display : none;
	}
#check2{
	color : red;
	display : none;
	}
#check3{
	color : red;
	display : none;
	}	
	
#code_ck{
	color : red;
	display : none;
}
#pw_ck{
	color : red;
	display : none;
}
#name_ck{
	color : red;
	display : none;
}
#email_ck{
	color : red;
	display : none;
}
</style>
<script
  src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous">
 </script>
</head>
<body>

<form id = "Join_Form" method="post">
<table id="tb1" width="500px">
<tbody>
<tr align ="center">
	<td colspan = "3"><h1>MANAGER JOIN</h1></td>
</tr>
<tr>
	<td style="text-align:center;"><input style="height:30px; width:200px;" type="text" placeholder="Manager Number" id="managerCode" name="managerCode"></td>
</tr>
<tr>
	<td style="text-align:center;">
		<span id="check1">관리자 등록번호가 확인되었습니다. </span>
		<span id="check2">관리자 등록번호가 존재하지 않습니다. </span>
		<span id="check3">이미 등록된 관리자입니다. </span>
		<span id="code_ck">관리자 등록번호를 입력하세요. </span></td>
</tr>
<tr>
	<td style="text-align:center;"><input style="height:30px; width:200px;" type="password" placeholder="비밀번호" id="managerPw" name="managerPw"></td>
</tr>
<tr>
	<td style="text-align:center;">
		<span id="pw_ck">비밀번호를 입력하세요. </span></td>
</tr>
<tr>
	<td style="text-align:center;"><input style="height:30px; width:200px;" type="text" placeholder="성명" id="managerName" name="managerName"></td>
</tr>
<tr>
	<td style="text-align:center;">
		<span id="name_ck">성명을 입력하세요. </span></td>
</tr>
<tr>
	<td style="text-align:center;"><input style="height:30px; width:200px;" type="text" id="managerEmail" name="managerEmail" title="E-mail" placeholder="e-mail (ex : abc@naver.com)"></td>
</tr>
<tr>
	<td style="text-align:center;">
		<span id="email_ck">이메일을 입력하세요. </span></td>
</tr>
<tr>
	<td style="text-align:center;" ><button style="height:30px; width:200px; border : 2px solid black;" type="button" id="Join_Button">JOIN</button></td> 
</tr>
</tbody>
</table>
</form>
<script>
var codeck = false;
var pwck = false;
var nameck = false;
var emailck = false;

$('#managerCode').on('propertychange change keyup paste input', function() {
  	var managerCode = $('#managerCode').val();
  	var data = {managerCode : managerCode};
  	$.ajax({
  		type : "post",
  		url : "/check/managerCK",
  		data : data,
  		success : function(result1){
  			if(result1 == 'true'){
  				$('#check1').css("display", "inline-block");
	  			$('#check2').css("display", "none");
	  			$('#code_ck').css('display', "none");
	  			$.ajax({
	  				type : "post", url : "/check/IDCK", data : data, 
	  				success : function(result2){
	  					if(result2 == 'true'){
	  						$('#check3').css("display", "none");
  							$('#code_ck').css('display', "none");
  							codeck = true;
  							console.log(codeck);
	  					}
	  					else if(result2 == 'fail'){
	  						$('#check1').css("display", "none");
  			  				$('#check2').css("display", "none");
  							$('#check3').css("display", "inline-block");
  							$('#code_ck').css('display', "none");
  							codeck = false;
  							console.log(codeck);
	  					}
	  				}
	  			});
  			}else if(managerCode == ""){
  				$('#code_ck').css('display', 'inline-block');
  				$('#check1').css("display", "none");
	  			$('#check2').css("display", "none");
	  			$('#check3').css("display", "none");
  			}
  			
  			else{
  				$('#check2').css("display", "inline-block");
	  			$('#check1').css("display", "none");
	  			$('#check3').css("display", "none");
  			}
  		}
  	});
  	
})


$(document).ready(function(){
	$("#Join_Button").on('click', function(){
		var code = $('#managerCode').val();
		var pw = $('#managerPw').val();
		var name = $('#managerName').val();
		var email = $('#managerEmail').val();

		console.log(codeck);
		
		if(pw == ""){
			$('#pw_ck').css('display', 'inline-block');
			pwck = false;
		}else{
			$('#pw_ck').css('display', 'none');
			pwck = true;
		}	
		if(name == ""){
			$('#name_ck').css('display', 'inline-block');
			nameck = false;
		}else{
			$('#name_ck').css('display', 'none');
			nameck = true;
		}	
		if(email == ""){
			$('#email_ck').css('display', 'inline-block');
			emailck = false;
		}else{
			$('#email_ck').css('display', 'none');
			emailck = true;
		}
		
		if(codeck && pwck && nameck && emailck){
			$("#Join_Form").submit();
			
		}
	});
})
</script>
</body>
</html>