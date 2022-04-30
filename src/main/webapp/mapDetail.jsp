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
	<c:choose>
	    <c:when test='${check == "1"}' >
	        <script>
				alert('이미 있는 즐겨찾기입니다.'); 
			</script>
	   </c:when> 
   </c:choose>
	<form method="post">
	<div id="top" style="height:100px;padding:35px">
		
			<table>
				<tr>
					<td nowrap><font size="17">${mapData.fstvlNm}</font></td>
					<td><input type="hidden" class="class" name="fnum" id="fnum" value="${mapData.fnum}"></td>
				</tr>
			</table>
	</div>
	<div id="text_f" style="width:60vw;  float: left;padding:35px">	
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
			    <tr>	
			    </tr> 
		  </tbody>
		</table>
			<c:choose>
				<c:when test="${not empty msg}">	
					<button type="submit" class="btn btn-info" formaction="${contextPath}/login/favor2.do">즐겨찾기</button>	
				</c:when>
			</c:choose>
		<button class="btn btn-danger" type="button" onclick="location.href='${contextPath}/map/main.do'" >돌아가기</button>	
	</div>
	<div id="map" style="width:30vw; height: 50vh; float:right;margin: 100px 30px 0 50px;"></div>
  	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBv_OQaaCohFpbHGvrUZRQrK_XTOSCWh4I&callback=initMap&region=kr"></script> 	
  		<script>
		    function initMap() {
		      var base = { lat: 36.5642135 ,lng: 127.5016985 };
		      var map = new google.maps.Map(
		        document.getElementById('map'), {
		          zoom: 7,
		          center: base
		        });		      	
			    	var a = { lat:${mapData.latitude}, lng:${mapData.longitude}}; 
			    	var markerName = "${mapData.fnum}"
			    	var marker = new google.maps.Marker({
			    	    position: a,
			    	    map: map,	
			    	    label: markerName
			    	  }); 
			    	google.maps.event.addListener(marker, 'click', (function(marker) {
	                    return function() {;
	                    }
	                })(marker));
	                if (marker) {
	                    marker.addListener('click', function() {
	                        map.setCenter(this.getPosition());
	                        map.setZoom(12);
	                    });
	                }
		    }
		    
		  </script>
	</form>
</body>
</html>