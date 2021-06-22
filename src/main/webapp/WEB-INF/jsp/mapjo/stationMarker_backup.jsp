<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>마커 클러스터러 사용하기</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ede33f5c81efd47fccd6dc5201a8a50&libraries=clusterer"></script>
<style type="text/css">
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;height:100vh; margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}


</style>   
</head>
<body>
<p style="margin-top:-12px">
</p>
	<div class="map_wrap">
		<div id="map"
			style="width: 100vm; height: 100vh; position: relative; overflow: hidden;"></div>


		<div id="menu_wrap" class="bg_white">
			<div class="option">
				<div>
						<input type="text" name="keyword" id="keyword" size="15">
						<button type="button" id="search">search</button>
				</div>
			</div>
			<hr>
			<!-- 검색 결과가 출력되는 곳  -->
			<ul id="placesList"></ul>
			<div id="pagination"></div>
		</div>
	</div>

<script type="text/javascript">


	$(function () {
		//alert(1)
		
		$("#search").click(function() {
			$.ajax({
				url: "/stationMarker",//서버주소 
				type: "get",// 요쳥방식 get post put delete
				dataType: "json",//서버가 보내오는 데이터 타입 -응답 : text, html, xml, json
				data: {keyword : $('input[name=keyword]').val()},//서버에게 보낼 parameter 정보 
				success: function (result) {
					//alert(result)
					 var str="";
					$.each(result, function (index, item) {
						//alert(item.lat)
						alert(index)
						//마커모양 변경요 !!
						str+=	"<li class='item'><span class='markerbg marker_"+(index+1) +"'></span>";
						str+=	"<div class='info'><h5>"+item.station+"</h5>";
						str+=	"<span class='jibun gray'>"+item.addr+"</span>";
						str+=	"<span class='tel'>"+item.lat+"</span><span class='tel'>"+item.lng+"</span></div>"; 
						str+=	"<input type='button' value='추가' id='addList'><input type='button' value='삭제' id='deleteList'></li>"; 
						
					
						
					})//end of each
					
				
					
					$("#placesList").html(str); 
				},
				error: function (err) {
					consol.log(err+": error occured")
				}
					
			});//end of ajax
			

		});//end of click
		
		

		
		//리스트에 있는 장소 마커 추가 
		$(document).on("click","#addList",function () {
			alert(1)
		});
		
		
		//리스트에 있는 장소 마커 삭제 
		$(document).on("click","#deleteList",function () {
			alert(2)
		});

	});//end of ready
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 
        level : 12 // 지도의 확대 레벨 
    });
    
    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 10 // 클러스터 할 최소 지도 레벨 
    });
 
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("${pageContext.request.contextPath}/data/korail.json", function(data) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = $(data.positions).map(function(i, position) {
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(position.lat, position.lng)
            });
        });

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });
</script>
</body>
</html>