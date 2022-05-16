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
  <title>국내관광지검색</title>
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
  	.sidenav {
	    height: 100%;
	    width: 0;
	    position: fixed;
	    z-index: 1;
	    top: 0;
	    right: 0;
	    background-color: #B6FDFD;
	    overflow-x: hidden;
	    transition: 0.5s;
	    padding-top: 60px;
	    float: right;
	    text-align: center;
	}
	
	.sidenav a {
	    padding: 8px 8px 8px 32px;
	    text-decoration: none;
	    font-size: 25px;
	    color: #818181;
	    display: block;
	    transition: 0.3s;
	}
	
	.sidenav a:hover {
	    color: #f1f1f1;
	}
	
	.sidenav .closebtn {
	    position: absolute;
	    top: 0;
	    right: 25px;
	    font-size: 36px;
	    margin-left: 50px;
	}
	
	@media screen and (max-height: 450px) {
	  .sidenav {padding-top: 15px;}
	  .sidenav a {font-size: 18px;}
	}
	html, body {
	width: 100%; height: 100%;
}
  </style>
</head>

<body>
	<c:choose>
	    <c:when test='${checkt == "0"}' >
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
	<div id="top" style="height:50%;">		
		<div style="height:30%; display: flex;" align="center">
			<div style="flex:1; margin:1% 0px 10px 0px">				
				<label style="width:500px"><img src="../images/balloon.png"  width="80px" height="80px"/><font size=25>여행정보검색</font></label>
			</div>
			<div style="flex:1; margin:1% 0px 0px 0px">
				<c:choose>
					<c:when test="${empty msg}">				
						<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
					</c:when>
					<c:otherwise> 
						<span style="font-size:30px;cursor:pointer" onclick="openNav2()">&#9776;</span>						  
					</c:otherwise>	
				</c:choose>
			</div>							  								
		</div>
		<div style="height:20%" align="center">
			<div style="height:100%; background-color:#F8F6F6"> 
				<button style="width: 200px; height:100%; background-color:#F8F6F6; font-size:25px" class="btn btn-light" type="button" onclick="location.href='${contextPath}/map/main.do'" style="width:120px">축제</button> 
				<button style="width: 200px; height:100%; background-color:#E0DDDD; font-size:25px" class="btn btn-light" type="button" onclick="location.href='${contextPath}/trr/main.do'" style="width:120px">관광지</button> 	 
	 		</div>
		</div>
		<div style="height:50%" >
			<table style="margin:40px 0px 0px 30px">			
				<form method="post" action="${contextPath}/trr/trrSearch.do"> 		
				<tr>
					<td></td><td>지역&nbsp&nbsp
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
					<td></td><td>관광지명&nbsp&nbsp<input type="text" name="tname" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" >검색</button>
				</tr>
			</form>
			</table>
		</div>
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
	<div id="text_map" style="overflow:auto; width:30vw; height: 700px; float: right;">	
		<table class="table"> 
		  <thead>
		    <tr>
		      <th scope="col" style="width:50px">번호</th>
		      <th scope="col" style="width:300px">관광지명</th>
		    </tr>
		  </thead> 
		  <tbody>
		  	<c:forEach  var="mem" items="${mapList}"> 
			    <tr>	
			      <form method="post">		    								     	
				      <td scope="row">
				      	 <input style="width:50px" type="text" id="a1" name="tnum" value="${mem.tnum}" readonly>
				      </td>
				      <td><button type="submit" class="btn btn-Light" formaction="${contextPath}/trr/trrDetail.do">${mem.trrsrtNm}</button></td>	
				      <c:choose>
					    <c:when test="${not empty msg}">
				      		<td style="white-space:nowrap;"><button type="submit" class="btn btn-Light" formaction="${contextPath}/login/favort.do">즐겨찾기</button></td>	
				      	</c:when>
				      </c:choose>
			      </form>	      
			    </tr>
		    </c:forEach>
		  </tbody> 
		</table>	
	</div>
	<div id="mySidenav" class="sidenav" align="center">
	  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	  	
		    <form name = "frm"  method="post"   action="${contextPath}/login/logint.do">
		    <div class="mb-3" align="center" style="margin: 15px;">
			    <label for="inputid3" class="form-label" style="width:50px">아이디</label>
			    <input type="text" class="form-control" name="id" style="width:150px">
			  </div>
			  <div class="mb-3" align="center" style="margin: 15px;">
			    <label for="inputPassword3" style="width:70px" >비밀번호</label>
			    <input type="password" class="form-control" name="pwd" style="width:150px">
			  </div>
			  <div style="margin: 15px;"><button type="submit" style="width:80px; background-color:#A9A9F5; color:#000000" class="btn btn-secondary">로그인</button></div>			  					     
			</form>							
			<div style="margin: 15px;">
				<button type="button" class="btn btn-success" onclick="location.href='${contextPath}/login/memberForm.do'" style="width:100px">회원가입</button>						  
			</div>								
	</div>
	<div id="mySidenav2" class="sidenav" >
	  <a href="javascript:void(0)" class="closebtn" onclick="closeNav2()">&times;</a>
	  <div style="margin: 15px;">
	  	<button class="btn btn-success" type="button" onclick="location.href='${contextPath}/login/mypage.do'" style="width:120px">마이페이지</button>
	  </div>
	  <div style="margin: 15px;">
	  	<button class="btn btn-danger" type="button" onclick="location.href='${contextPath}/login/logout.do'" style="width:120px">로그아웃</button> 
	  </div>
	</div>
	

	
	<script>
		function openNav() {
		    document.getElementById("mySidenav").style.width = "250px";
		}
		
		function closeNav() {
		    document.getElementById("mySidenav").style.width = "0";
		}
		function openNav2() {
		    document.getElementById("mySidenav2").style.width = "250px";
		}
		
		function closeNav2() {
		    document.getElementById("mySidenav2").style.width = "0";
		}
	</script>
			
</body>
</html>