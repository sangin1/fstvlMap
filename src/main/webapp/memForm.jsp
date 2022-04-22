<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  
<%
request.setCharacterEncoding("UTF-8");
%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>회원가입</title> 
</head>
<body>
	<c:choose>
	    <c:when test='${check == "1"}' >
	        <script>
				alert('존재하는 아이디입니다.'); 
			</script>
	   </c:when>
	   <c:when test='${check == "2"}' >
	        <script>
				alert('빈칸이 있습니다.'); 
			</script>
	   </c:when>
   </c:choose>
	   <form name = "frm"  method="post"   action="${contextPath}/login/addMember.do">
		    <div class="mb-3" style="padding:30px;width:400px">
		    <label for="exampleInputEmail1" class="form-label">아이디</label>
		    <input type="text" class="form-control" name="id" aria-describedby="emailHelp">
		    <div id="emailHelp" class="form-text">즐겨찾기에 사용할 아이디 입력</div>
			  </div>
			  <div class="mb-3" style="padding:30px;width:400px">
			    <label for="exampleInputPassword1" class="form-label">비밀번호</label>
			    <input type="password" class="form-control" id="exampleInputPassword1" name="pass">
			  </div> 
			  <div style="padding:30px">
		  		<button style="width:150px" type="submit" class="btn btn-primary">가입</button>
		  		<button class="btn btn-danger" type="button" onclick="location.href='${contextPath}/map/main.do'" >돌아가기</button> 
		  	  </div>
		</form>
</body>
</html>
