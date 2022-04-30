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
  	#a2{
  		position: absolute;
		  width: 100%;
		  height: 100%;
  	}
  </style>
</head>

<body>
	<c:choose>
	    <c:when test='${check1 == "0"}' >
	        <script>
				alert('아이디 또는 비밀번호가 틀렸습니다.');
				history.back();
			</script>
	   </c:when>
	   <c:when test='${checkFavor == "1"}' >
	        <script>
				alert('존재하는 즐겨찾기입니다.');
				history.back();
			</script>
	   </c:when>
	  </c:choose>
	<div id="top" style="height:20%">		
			<table>
				<tr>
					<th style="width:5vw;"></th><th><label style="width:500px"><font size=20>국내축제검색</font></label></th>	
					  <c:choose>
					    <c:when test="${empty msg}">				
							<form name = "frm"  method="post"   action="${contextPath}/login/login.do">
								<th><label for="inputid3" style="width:50px">아이디</label></th>						    
								      <th><input type="text" name="id" style="width:100px"></th>								   
								    <th><label for="inputPassword3" style="width:70px" >비밀번호</label></th>								
								      <th><input type="password"  name="pwd" style="width:100px"></th>								    
								    <th><button type="submit" style="width:80px" class="btn btn-info">로그인</button></th>
								    </form>							
								      <td><button type="button" class="btn btn-success" onclick="location.href='${contextPath}/login/memberForm.do'" style="width:100px">회원가입</button></td>								  
							
						</c:when>
						 <c:otherwise> 
						 	<td>
						 		<label style="width:250px"></label></td><td><button class="btn btn-success" type="button" onclick="location.href='${contextPath}/login/mypage.do'" style="width:120px">마이페이지</button>
						 	</td>
						 	<td>
						 		<button class="btn btn-info" type="button" onclick="location.href='${contextPath}/login/logout.do'" style="width:120px">로그아웃</button> 
						 	</td>							  
						 </c:otherwise>	
					  </c:choose>				
				</tr>
				<tr style="height:70px;"><td></td></tr>
				<form method="post"   action="${contextPath}/map/mapSearch.do"> 
				<tr>
					<td></td><td><label>축제시작일</label>
					<input type="date" id="start" name="trip-start"  value="${fsv.startDate}">
					<label>~ 축제종료일</label>
					<input type="date" id="end" name="trip-end"  value="${fsv.endDate}"></td>
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
	<div id="map" style="width:65vw; height: 700px; float: left;"></div>
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
		    	arr.push({name:"${m.fstvlNm}"
		    		,lat:"${m.latitude}"
		    		,lng:"${m.longitude}"
		    		,fnum:"${m.fnum}"
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
	                        map.setZoom(10);
	                    });
	                }
	            }
			  }
		    
		  </script>
	<div id="text_map" style="overflow:auto; width:30vw; height: 700px; float: right;">	
		<table class="table"> 
		  <thead>
		    <tr>
		      <th scope="col" style="width:50px">번호</th>
		      <th scope="col" style="width:300px">축제명</th>
		    </tr>
		  </thead> 
		  <tbody>
		  	<c:forEach  var="mem" items="${mapList}"> 
			    <tr>	
			      <form method="post">		    								     	
				      <th scope="row">
				      	 <input style="width:50px" type="text" id="a1" name="fnum" value="${mem.fnum}" readonly>
				      </th>
				      <td><button type="submit" class="btn btn-Light" formaction="${contextPath}/map/mapDetail.do">${mem.fstvlNm}</button></td>	
				      <c:choose>
					    <c:when test="${not empty msg}">
				      		<td style="white-space:nowrap;"><button type="submit" class="btn btn-Light" formaction="${contextPath}/login/favor.do">즐겨찾기</button></td>	
				      	</c:when>
				      </c:choose>
			      </form>	      
			    </tr>
		    </c:forEach>
		  </tbody> 
		</table>	
	</div>
</body>
</html>