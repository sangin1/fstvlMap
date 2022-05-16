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
  <title>마이페이지</title>
</head>

<body>
	<div id="top" style="height:100px;padding:35px">
		
			<table>
				<tr>
					<td nowrap><font size="17">마이페이지</font></td>
				</tr>
			</table>
	</div>
	<div id="map" style="width:40%; height: 400px; float: right;"></div>
  	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBv_OQaaCohFpbHGvrUZRQrK_XTOSCWh4I&callback=initMap&region=kr"></script> 	
  		<script>
		    function initMap() {
		      var base = { lat: 36.5642135 ,lng: 127.5016985 };
		      var map = new google.maps.Map(
		        document.getElementById('map'), {
		          zoom: 7,
		          center: base
		        });
		      
		      var arr = new Array();
		      var arr2 = new Array();
		      <c:forEach items="${mapList}" var="m">		
		    	arr.push({name:"${m.fstvlNm}"
		    		,lat:"${m.latitude}"
		    		,lng:"${m.longitude}"
		    		,fnum:"${m.fnum}"
		    		});
			    </c:forEach>
			    <c:forEach items="${trrList}" var="t">		
		    	arr2.push({name:"${t.trrsrtNm}"
		    		,lat:"${t.latitude}"
		    		,lng:"${t.longitude}"
		    		,tnum:"${t.tnum}"
		    		});
			    </c:forEach>
			    var infowindow = new google.maps.InfoWindow();
	            for (var i = 0; i < arr.length; i++) {
	                var marker = new google.maps.Marker({
	                    map: map,
	                    position: new google.maps.LatLng(arr[i].lat, arr[i].lng),
	                    label: arr[i].fnum
	                });
	                google.maps.event.addListener(marker, 'click', (function(marker, i) {
	                    return function() {
	                        infowindow.setContent(
	                        		'<div><div><h4>'+arr[i].name+'</h4><a href="${contextPath}/map/mapDetail.do?fnum='+arr[i].fnum+'"><p>상세정보</p></a></div>'
	                        );
	                        infowindow.open(map, marker);
	                    }
	                })(marker, i));
	                if (marker) {
	                    marker.addListener('click', function() {
	                        map.setCenter(this.getPosition());
	                        map.setZoom(12);
	                    });
	                }
	            }
	            for (var i = 0; i < arr2.length; i++) {
	                var marker = new google.maps.Marker({
	                    map: map,
	                    position: new google.maps.LatLng(arr2[i].lat, arr2[i].lng),
	                    label: arr2[i].tnum
	                });
	                google.maps.event.addListener(marker, 'click', (function(marker, i) {
	                    return function() {
	                        infowindow.setContent(
	                        		'<div><div><h4>'+arr2[i].name+'</h4><a href="${contextPath}/trr/trrDetail.do?fnum='+arr[i].tnum+'"><p>상세정보</p></a></div>'
	                        );
	                        infowindow.open(map, marker);
	                    }
	                })(marker, i));
	                if (marker) {
	                    marker.addListener('click', function() {
	                        map.setCenter(this.getPosition());
	                        map.setZoom(12);
	                    });
	                }
	            }
		    }
		     
		  </script>
	<div id="text_map" style="overflow:auto; width:40%; height: 700px; float:left;">	
		<table class="table"> 
		  <thead>
		    <tr>
		      <th scope="col" style="width:50px">번호</th>
		      <th scope="col" style="width:300px">이름</th>
		    </tr>
		  </thead> 
		  <tbody>
		  	<c:forEach  var="mem" items="${mapList}"> 
			    <tr>	
			      <form method="post">		    								     	
				      <td scope="row">
				      	 <input style="width:50px" type="text" id="a1" name="fnum" value="${mem.fnum}" readonly>
				      </td>
				      <td><button type="submit" class="btn btn-Light" formaction="${contextPath}/map/mapDetail.do">${mem.fstvlNm}</button></td>	
				      <td><button type="submit" class="btn btn-Light" formaction="${contextPath}/login/favorDel.do">삭제</button></td>					      
			      </form>	      
			    </tr>
		    </c:forEach>
		    <c:forEach  var="trr" items="${trrList}"> 
			    <tr>	
			      <form method="post">		    								     	
				      <td scope="row">
				      	 <input style="width:50px" type="text" id="a1" name="tnum" value="${trr.tnum}" readonly>
				      </td>
				      <td><button type="submit" class="btn btn-Light" formaction="${contextPath}/trr/trrDetail.do">${trr.trrsrtNm}</button></td>	
				      <td><button type="submit" class="btn btn-Light" formaction="${contextPath}/login/favorDel.do">삭제</button></td>					      
			      </form>	      
			    </tr>
		    </c:forEach>
		  </tbody> 
		</table>
		<button class="btn btn-danger" type="button" onclick="location.href='${contextPath}/map/main.do'" >돌아가기</button>	
	</div>
</body>
</html>