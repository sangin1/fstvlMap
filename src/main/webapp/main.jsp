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
  <title>국내축제검색</title>
  <style>
  	.titleNm{
  		margin-left: 5vw;
  	}
  	#a1{
  		border-color: cyan;
  		width: 40px;
  		font-weight : bold;
  	}
  </style>
</head>

<body>
	<div id="top" style="height:250px">
		<form method="post"   action="${contextPath}/map/mapSearch.do"> 
			<table>
				<tr>
					<td></td><td><font size="20">국내축제검색</font></td>
				</tr>
				<tr><td><font size="10">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font></td></tr>
				<tr>
					<td></td><td>축제시작일&nbsp&nbsp<input type="date" id="start" name="trip-start"  value="${fsv.startDate}">&nbsp&nbsp&nbsp&nbsp~&nbsp&nbsp
					축제종료일&nbsp&nbsp<input type="date" id="end" name="trip-end"  value="${fsv.endDate}"></td>
				</tr>
				<tr>
					<td></td><td>축제지역&nbsp&nbsp
					<select name="main" onchange="categoryChange(this)">
				      <option value="전체">전체</option>				
				      <option value="서울">서울</option>				
				      <option value="경기도">경기</option>
				      <option value="강원도">강원</option>	
				      <option value="충청북도">충북</option>	
				      <option value="충청남도">충남</option>	
				      <option value="전라북도">전북</option>	
				      <option value="전라남도">전남</option>	
				      <option value="경상북도">경북</option>	
				      <option value="경상남도">경남</option>	
				      <option value="인천">인천</option>	
				      <option value="부산">부산</option>		
				      <option value="대구">대구</option>	
				      <option value="대전">대전</option>	
				      <option value="광주">광주</option>	
				      <option value="울산">울산</option>
				      <option value="세종">세종</option>					      			
				    </select>
				    &nbsp&nbsp&nbsp&nbsp
				    <select name="sub" id="sub">
						<option value="all">전체</option>
					</select>
					<script>
						function categoryChange(e) {
							var Gyeonggi = ["전체","가평","과천","광주","군포","남양주","부천","수원","안산","안양","양평","연쳔","용인","의정부","파주","포천","화성"
								,"고양","광명","구리","김포","동두천","성남","시흥","안성","양주","여주","오산","의왕","이천","평택","하남"];
							var Gangwon = ["전체","춘천","원주","강릉","동해","태백","속초","삼척","홍천","횡성","영월","평창","정선","철원","화천","양구","인제"
								,"고성","양양"];
							var Chungbuk = ["전체","청주","충주","단양","음성","괴산","증평","진천","영동","옥천","보은","제천"];
							var Chungnam = ["전체","천안","공주","보령","아산","서산","논산","계룡","당진","금산","부여","서천","청양","홍성","예산","태안"];
							var Jeonbuk = ["전체","전주","군산","익산","정읍","남원","김제","완주","진안","무주","장수","임실","순창","고창","부안"];
							var Jeonnam = ["전체","목포","여수","순천","나주","광양","담양","곡성","구례","고흥","보성","화순","장흥","강진","해남","영얌"
								,"무안","함평","영광","장성","완도","진도","신안"];
							var Gyeongbuk = ["전체","포항","경주","김천","안동","구미","영주","영천","상주","문경","경산","군위","의성","청송","영양","영덕"
								,"청도","고령","성주","칠곡","예천","봉화","울진","울릉"];
							var Gyeongnam = ["전체","창원","진주","통영","사천","김해","밀양","거제","양산","의령","함안","창녕","고성","남해","하동","산청","함양"
								,"거창","합천"];
							var sub = ["전체"];
							var target = document.getElementById("sub");
	
							if(e.value == "경기도") var d = Gyeonggi;
							else if(e.value == "강원도") var d = Gangwon;
							else if(e.value == "충청북도") var d = Chungbuk;
							else if(e.value == "충청남도") var d = Chungnam;
							else if(e.value == "전라북도") var d = Jeonbuk;
							else if(e.value == "전라남도") var d = Jeonnam;
							else if(e.value == "경상북도") var d = Gyeongbuk;
							else if(e.value == "경상남도") var d = Gyeongnam;	
							else var d = sub;
							target.options.length = 0;
	
							for (x in d) {
								var opt = document.createElement("option");
								opt.value = d[x];
								opt.innerHTML = d[x];
								target.appendChild(opt);
							}	
						}
					</script>
					</td>
				</tr>
				<tr>
					<td></td><td>축제명&nbsp&nbsp<input type="text" name="fname" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" >검색</button>
				</tr>
			</table>
		</form>
	</div>
	<div id="map" style="width:70vw; height: 700px; float: left;"></div>
  	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBv_OQaaCohFpbHGvrUZRQrK_XTOSCWh4I&callback=initMap&region=kr"></script> 	
  		<script>
		    function initMap() {
		      var base = { lat: 36.5642135 ,lng: 127.5016985 };
		      var map = new google.maps.Map(
		        document.getElementById('map'), {
		          zoom: 8,
		          center: base
		        });
		      
		      <c:forEach items="${mapList}" var="marker">		
			    	var a = { lat:${marker.latitude}, lng:${marker.longitude}}; 
			    	var markerName = "${marker.fnum}"
			    	new google.maps.Marker({
			    	    position: a,
			    	    map: map,	
			    	    label: markerName
			    	  }); 
			    </c:forEach> 
		    }
		    
		  </script>
	<div id="text_map" style="width:25vw; height: 80vh; float: right;">	
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">번호</th>
		      <th scope="col">축제명</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach  var="mem" items="${mapList}"> 
			    <tr>	
			      <form method="get" action="${contextPath}/map/mapDetail.do">		    								     	
				      <th scope="row">
				      	 <input type="text" id="a1" name="fnum" value="${mem.fnum}" readonly>
				      </th>
				      <td><button type="submit" class="btn btn-Light">${mem.fstvlNm}</button></td>	
				      <td></td>	
			      </form>	      
			    </tr>
		    </c:forEach>
		  </tbody>
		</table>	
	</div>
</body>
</html>