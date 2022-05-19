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
	<form method="post">
	<div id="top" style="height:100px;padding:35px">
		
			<table>
				<tr>
					<td nowrap><font size="17">${mapData.fstvlNm}</font></td>
					<td><input type="hidden" class="class" name="fnum" id="fnum" value="${mapData.fnum}"></td>
					<td><input type="hidden" class="class" name="fname" id="fname" value="${mapData.fstvlNm}"></td>
					<td><input type="hidden" class="class" name="lat" id="lat" value="${mapData.latitude}"></td>
					<td><input type="hidden" class="class" name="lot" id="lot" value="${mapData.longitude}"></td>
				</tr>
			</table>
	</div>
	<div id="text_f" style="height: 50vh;width:60vw;  float: left;padding:35px">
		<div>	
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
				       <td nowrap>홈페이지</td> <td><a href="${mapData.homepageUrl}">${mapData.homepageUrl}</a> </td>     
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
		<div>
			<select name="km" onchange="categoryChange(this)" class="form-select" style="width:200px;">
				      <option value="20">20km</option>				
				      <option value="10">10km</option>				
				      <option value="50">50km</option>						      			
			</select>
			<button type="submit" class="btn btn-success" formaction="${contextPath}/trr/distance.do">주변 관광지 검색</button>	
		</div>
	</div>
	<div id="map" style="width:30vw; height: 50vh; float:right;margin: 50px 30px 0 0px;"></div>
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
	<div style="margin: 600px 0px 0px 50px;height: 50vh;width:80vw;">
		<table class="table" style="font-size: 15px;text-align:center"> 
			    <tr>	
			       <td style="width:150px">날짜</td>
			       <c:forEach  var="sw" items="${sweatherData.day}"> 
						<td nowrap></td>
						<td nowrap>${fn:substring(sw,0,2) }월 ${fn:substring(sw,2,4) }일</td>
						<td nowrap></td>
					</c:forEach>        
			    </tr> 
			    <tr>	
			       <td nowrap>기온</td>			       
					<c:forEach var="sw" items="${sweatherData.min}" varStatus="status"> 
						<td></td>
						<td>${sw}°C / ${sweatherData.max[status.index]}°C
						
						</td>
						<td></td>
					</c:forEach>  					
			    </tr> 			    
			    <tr>	
			       <td nowrap>시간</td>
					<td nowrap>6시</td>
					<td nowrap>12시</td>
					<td nowrap>18시</td>
					<td nowrap>6시</td>
					<td nowrap>12시</td>
					<td nowrap>18시</td>
					<td nowrap>6시</td>
					<td nowrap>12시</td>
					<td nowrap>18시</td>     
			    </tr> 
			    <tr>	
			       <td nowrap>하늘</td>
			       <c:forEach  var="sw" items="${sweatherData.sky}"> 
						<c:choose>
							<c:when test='${ sw == "1"}'>		
						       		<td nowrap><img src="../images/sun.png"  width="50px" height="50px"/></td>     
							</c:when>
							<c:when test='${ sw == "3"}'>		
						       		<td nowrap><img src="../images/cloudy-day.png"  width="50px" height="50px"/></td>    
							</c:when>
							<c:when test='${ sw == "4"}'>	
						       		<td nowrap><img src="../images/cloud.png"  width="50px" height="50px"/></td>
							</c:when>	    
					    </c:choose>
					</c:forEach>     
			    </tr> 
			    <tr>	
			       <td nowrap>강수확률</td>
			       <c:forEach  var="sw" items="${sweatherData.pop}"> 
						<td nowrap>${sw}%</td>
					</c:forEach>        
			    </tr> 
			    <tr>
			    	<td>강수형태</td>
			    	<c:forEach  var="sw" items="${sweatherData.pty}"> 
					    <c:choose>
							<c:when test='${ sw == "1"}'>		
						       		<td nowrap><img src="../images/rain-drops.png"  width="50px" height="50px"/></td>     
							</c:when>
							<c:when test='${ sw == "2"}'>		
						       		<td nowrap><img src="../images/snow_rain.png"  width="50px" height="50px"/></td>    
							</c:when>
							<c:when test='${ sw == "3"}'>	
						       		<td nowrap><img src="../images/snowing.png"  width="50px" height="50px"/></td>
							</c:when>
							<c:when test='${ sw == "4"}'>	
						       		<td nowrap><img src="../images/a.png"  width="50px" height="50px"/></td>
							</c:when>
							<c:otherwise> 
						       		<td nowrap><img src="../images/stop.png"  width="25px" height="25px"/></td>    
							</c:otherwise>			    
					    </c:choose>
				    </c:forEach>
			    </tr>
			    <tr>
			    	<td>강수량</td>
			    	<c:forEach  var="sw" items="${sweatherData.pcp}"> 
			    		<c:choose>
							<c:when test='${ sw == "강수없음"}'>		
						       		<td nowrap><img src="../images/rainx.png"  width="30px" height="30px"/></td>   
							</c:when>
							<c:otherwise> 
						       		<td nowrap>${sw}mm</td>    
							</c:otherwise>
						</c:choose>	
				    </c:forEach>	
			    </tr> 
			    <tr>
			    	<td>적설량</td>
			    	<c:forEach  var="sw" items="${sweatherData.sno}"> 
			    		<c:choose>
							<c:when test='${ sw == "적설없음"}'>		
						       		<td nowrap><img src="../images/snowx.png"  width="30px" height="30px"/></td>   
							</c:when>
							<c:otherwise> 
						       		<td nowrap>${sw}cm</td>    
							</c:otherwise>
						</c:choose>			    		
				    </c:forEach>	
			    </tr> 
		</table>
	</div>
</body>
</html>