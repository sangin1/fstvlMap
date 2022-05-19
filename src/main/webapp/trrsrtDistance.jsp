<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<div id="top" style="height:100px;padding:35px">
		
			<table>
				<tr>
					<td nowrap><font size="17">${fname} 주변 관광지</font></td>
					
				</tr>
			</table>
	</div>	
	<div id="text_f" style="height: 500px;width:45vw;padding:35px;float: left;overflow:auto;margin:100px 0px 0px 0px">	
		<table class="table"> 
		  <thead>
		    <tr>
		      <th scope="col" style="width:50px">번호</th>
		      <th scope="col" style="width:300px">관광지명</th>
		      <th scope="col" style="width:300px">거리</th>
		    </tr>
		  </thead> 
		  <tbody>
		  	<c:forEach  var="mem" items="${mapList}"> 
			    <tr>	
			      <form method="post">		    								     	
				      <td scope="row">
				      	 <input style="width:50px" type="text" id="a1" name="tnum" value="${mem.tnum}" readonly>
				      </td>
				      <td scope="row"><button type="submit" class="btn btn-Light" formaction="${contextPath}/trr/trrDetail.do">${mem.trrsrtNm}</button></td>
				      <td scope="row">
				      	 ${mem.km}KM
				      </td>
			      </form>	      
			    </tr>			    
		    </c:forEach>			   
		  </tbody> 
		</table>
		
	</div>
	<div id="map" style="width:50vw; height: 600px; float: right;margin:100px 0px 0px 30px"></div>
  	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBv_OQaaCohFpbHGvrUZRQrK_XTOSCWh4I&callback=initMap&region=kr"></script> 	
  		<script>
		    function initMap() {
		      var base = { lat: 36.5642135 ,lng: 127.5016985 };
		      var map = new google.maps.Map(
		        document.getElementById('map'), {
		          zoom: 8,
		          center: base
		        });
		      var arr = new Array();
		      <c:forEach items="${mapList}" var="m">		
		    	arr.push({name:"${m.trrsrtNm}"
		    		,lat:"${m.latitude}"
		    		,lng:"${m.longitude}"
		    		,tnum:"${m.tnum}"
		    		});
			    </c:forEach>
			    var infowindow = new google.maps.InfoWindow();
	            for (var i = 0; i < arr.length; i++) {
	                var marker = new google.maps.Marker({
	                    map: map,
	                    position: new google.maps.LatLng(arr[i].lat, arr[i].lng),
	                    label: arr[i].tnum
	                });
	                google.maps.event.addListener(marker, 'click', (function(marker, i) {
	                    return function() {
	                        infowindow.setContent(
	                        		'<div><div><h4>'+arr[i].name+'</h4><a href="${contextPath}/trr/trrDetail.do?fnum='+arr[i].tnum+'"><p>상세정보</p></a></div>'
	                        );
	                        infowindow.open(map, marker);
	                    }
	                })(marker, i));
	                if (marker) {
	                    marker.addListener('click', function() {
	                        map.setCenter(this.getPosition());
	                        map.setZoom(10);
	                    });
	                }
	            }
			  }
		    
		  </script>
	<div style="margin:630px 0px 0px 5%">
	<form method="post">			
			<input type="hidden" class="class" name="fnum" id="fnum" value="${fnum}">
			<button class="btn btn-danger" type="submit" formaction='${contextPath}/map/mapDetail.do'" >돌아가기</button>
		</form>
	</div>
</body>
</html>