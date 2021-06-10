<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>간단한 지도 표시하기</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ede33f5c81efd47fccd6dc5201a8a50"></script>
<style>
 a {text-decoration: none}
</style>


<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption>Station LIST</caption>
	<colgroup>
		<col width="15%"/>
		<col width="30%"/>
		<col width="16%"/>
		<col width="16%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
	</colgroup>
	<tr>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">번호</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">역명 </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">위도  </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">경도  </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">주소 </span></b></font></p>
        </td>
        
    </tr>
    <c:choose>
    <c:when test="${empty requestScope.stationList}">
	<tr>
        <td colspan="5">
            <p align="center"><b><span style="font-size:9pt;">등록된 게시물이 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
	<c:forEach items="${requestScope.stationList.content}" var="st"><!-- pageList.getContent() -->
		    <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.id}</span></p>
		        </td>
		        <td bgcolor="">
					<p><span style="font-size:9pt;">
					<a href="${pageContext.request.contextPath}/mapjo/list/${st.id}">
					${st.station}
					</span></p>
		        </td>
		        
		       
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.lat}</span></p>
		        </td>
		         
		        
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.lng}</span></p>
		        </td>
		         <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.addr}</span></p>
		        </td>
		    </tr>
    </c:forEach>
	</c:otherwise>
    </c:choose>
</table>
<hr>



 <%-- ${stationList.hasPrevious()}  /  ${stationList.hasNext()} --%>
<div style="text-align: center">
<!-- 페이징 처리 -->
 <c:forEach begin="0" end="${stationList.totalPages-1}" var="i">
   <c:choose>
     <c:when test="${stationList.number==i}">
         <a href="${pageContext.request.contextPath}/mapjo/stationList?nowPage=${i}" style="color:red"> [ ${i+1} ] </a>
     </c:when>
     <c:otherwise>
         <a href="${pageContext.request.contextPath}/mapjo/stationList?nowPage=${i}"> [ ${i+1} ] </a>
     </c:otherwise>
   </c:choose>
   
 </c:forEach>
</div>



<div>
	<a href="${pageContext.request.contextPath}/mapTotal">전체지도보기 </a><p>
</div>

<div id="map"></div>


<script type="text/javascript">
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
	center : new kakao.maps.LatLng("${stationDetail.lat}",
			"${stationDetail.lng}"), // 지도의 중심좌표
	level : 3
// 지도의 확대 레벨
};



var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var markers =[];





//지도 클릭시 마커생성 
kakao.maps.event.addListener(map, 'click', function(event) {
	//alert(2)
	addMarker({
		location : event.latLng
	});

});




//클릭하면 마커를 표시 
function addMarker(prop) {

	//마커가 표시될 위치 
	var markerPosition = new kakao.maps.LatLng("${stationDetail.lat}",
			"${stationDetail.lng}");

	const marker = new kakao.maps.Marker({
		position : prop.location,
		map : map

	});
	marker.setMap(map);
	markers.push(marker);

}

function setMarkers(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }            
}


// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers() {
    setMarkers(null);    
}




</script>
</body>
</html>






