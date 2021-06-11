<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>간단한 지도 표시하기</title>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ede33f5c81efd47fccd6dc5201a8a50"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

	<style type="text/css">
		body{margin: 0px}
		#map{
			width: 100vm; height:100vh
		}
	
	</style>
	
</head>
<body>
<div style="position: absolute; z-index: 10; padding: 80px;">
	
	<p>
    <button onclick="hideMarkers()">마커 삭제 </button>
</p> 
</div>
	station : ${stationDetail.station}  
	<br> lat : ${stationDetail.lat}
	<br> lng : ${stationDetail.lng}
	<div id="map"></div>

	<script>
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






















