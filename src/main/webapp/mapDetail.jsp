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
  <title>축제세부정보</title>
</head>

<body>
	<div id="top" style="height:100px">
			<table>
				<tr>
					<td nowrap><font size="17">${mapData.fstvlNm} 세부정보</font></td>
				</tr>
			</table>
	</div>
	<div id="text_f" style="width:60vw;  float: left;">	
		<table class="table"> 
		  <tbody> 
			    <tr>	
			       <td nowrap>축제 시작일</td> <td nowrap>${mapData.fstvlStartDate}</td>      
			    </tr> 
			    <tr>	
			       <td nowrap>축제 종료일</td> <td>${mapData.fstvlEndDate}</td>      
			    </tr> 
			    <tr>	
			       <td nowrap>축제 내용</td> <td>${mapData.fstvlCo}</td>      
			    </tr> 
			    <tr>	
			       <td nowrap>축제 장소</td> <td>${mapData.opar} </td>     
			    </tr> 
			    <tr>	
			       <td nowrap>홈페이지</td> <td>${mapData.homepageUrl} </td>     
			    </tr> 
			    <tr>	
			       <td nowrap>전화번호</td> <td>${mapData.phoneNumber}  </td>    
			    </tr> 
			    <tr>	
			       <td nowrap>도로명주소</td> <td>${mapData.rdnmadr}  </td>    
			    </tr> 
			    <tr>	
			       <td nowrap>지번주소</td> <td>${mapData.lnmadr}  </td>    
			    </tr> 
			    <tr>	
			       <td nowrap>주관</td> <td>${mapData.mnnst}   </td>   
			    </tr> 
		  </tbody>
		</table>	
	</div>
</body>
</html>