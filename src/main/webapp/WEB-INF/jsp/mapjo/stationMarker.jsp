<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>주요역 마커 생성 및 일정생성</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ede33f5c81efd47fccd6dc5201a8a50&libraries=clusterer"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/stationMarkerCSS.css" rel="stylesheet" type="text/css" />

</head>
<body>
<p style="margin-top:-12px">
</p>

<button type="button" id="addScheduel">일정생성 </button>
	<div class="map_wrap">
		<div id="map"
			style="width: 100vm; height: 100vh; position: relative; overflow: hidden;"></div>

		<!-- 역 검색하기  -->
		<div id="menu_wrap" class="bg_white">
			<div class="option">
				<div>
					<input type="text" name="keyword" id="keyword" size="15">
					<button type="button" id="search">search</button>
					<input type='button' value="delete markers" id="deleteList">
					<input type='hidden' value="delete markers" > 
					
				</div>
			</div>
			<hr>
			<!-- 검색 결과가 출력되는 곳  -->
			<ul id="placesList"></ul>
			<div id="pagination"></div>
		</div>


		<!-- add list  -->
	<form name="plan" action="${pageContext.request.contextPath}/mapjo/citySave" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
		<div id="menu_wrap2" class="bg_white">
			<div class="option">
					<div id="cityplan">
						<h2>LIST</h2>
						<input id="savePlan" type="submit" value="save plan"/>
					</div>	
			</div>
			<hr>
			<!-- 추가한 장소가 저장되는 곳  -->
			
			<ul id="placesList2"></ul>
			<div id="pagination"></div>
		</div>
		</form>

	</div>


	<script type="text/javascript">
/* 
	${travelPlan.planId}
	${travelPlan.userId}
	${travelPlan.startDate}
	${travelPlan.endDate}
	 */

	$(function () {
		//alert(1)
		//var itemList=[];
		
		
		var markers=[];
		var startDate = "${travelPlan.startDate}"; 
		var endDate = "${travelPlan.endDate}";
		var travelPlan = "${travelPlan.planId}";
			alert(startDate)
			alert(endDate)
			alert(travelPlan)
			var listDate=[];
				getDateRange(startDate, endDate, listDate);
				console.log(listDate); 
				
				totalSchedule(listDate);
				
				sortable();
		/*	
		//일정 생성 버튼 이벤트 
		$("#addScheduel").click(function () {
			//alert(1)
				
 		startDate = $("#datepicker").val();
			endDate = $("#datepicker2").val(); 
			
	
			
		})
			*/
		
		
		//역검색 이벤트 
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
						//alert(index)
						//마커모양 변경요 !!
						str+=	"<li class='item'><span class='markerbg marker_"+(index+1) +"'></span>";
						str+=	"<div class='info'><h5>"+item.station +"</h5>";
						str+=	"<h5>"+item.id+"</h5>";
						str+=	"<span class='jibun gray' name=''>"+item.addr+"</span>";
						str+=	"<span class='tel'>"+item.lat+"</span><span class='tel'>"+item.lng+"</span></div>"; 
						str+=	"<input type='button' value='추가' id='addList' name='"+item.lat+","+item.lng+"'></li>"; 
						
					//itemList.push(item);
					
						
					})//end of each
					
					
					$("#placesList").html(str); 
				},
				error: function (err) {
					consol.log(err+": error occured")
				}
					
			});//end of ajax
			

		});//end of click
		
		
		var city;
		var stationId;
		var address;
		var latitude;
		var longitude;
		
		
		//리스트에 있는 장소 마커 추가 
		$(document).on("click","#addList",function () {
	
			city = $(this).prev().children().eq(0).text();
			stationId = $(this).prev().children().eq(1).text();
			address = $(this).prev().children().eq(2).text();
			latitude = $(this).prev().children().eq(3).text();
			longitude = $(this).prev().children().eq(4).text();
			
			//alert($(this).attr("name"))
			var a =$(this).attr("name").split(",")
			
			var markerPosition  = new kakao.maps.LatLng(a[0], a[1]); 
			//var markerPosition  = new kakao.maps.LatLng(t.text(), g.text()); 

			
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});
			
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
			//marker.setMap(null);
			markers.push(marker);
			
			
			createDaySchedule(startDate);
			//createItem(startDate);
	
	});
		
		
		//리스트 장소 목록 삭제 버튼 이벤트 
		$(document).on('click', 'input[name=deletePlan]', function() {

			var delCheck = confirm('삭제하시겠습니까?');
			if (delCheck) {
				$(this).parent().parent().parent().parent().remove();
				reorder();
			}

		});
		
		
		
		//리스트에 있는 장소 마커 삭제 
		$(document).on("click","#deleteList",function () {
	
			hideMarkers();
		});
		
		//submit 전 리네임 
		$("form[name=plan]").bind('submit', function() {
			renameForModelAttribute();
		});
		
		
		function setMarkers(map) {
		    for (var i = 0; i < markers.length; i++) {
		        markers[i].setMap(map);
		    }            
		}
		

		// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
		function hideMarkers() {
		    setMarkers(null);  
		    markers.splice(0);
		    //markers=[];
		}
		
		
//. 리스트 생성 코드

		function sortable() {
			
	    $(".dayschedule").sortable({
	    	
	    	connectWith: ".dayschedule",

	       placeholder:"itemBoxHighlight",

	        start: function(event, ui) {

	            ui.item.data('start_pos', ui.item.index());

	        },

	        stop: function(event, ui) {

	            var spos = ui.item.data('start_pos');

	            var epos = ui.item.index();

		    	reorder();

	        }
	        
	        //$(".dayschedule").disableSelection();

	    });
		}
		var resultdrawArr=[];
		var drawInfoArr=[];
	    function reorder() {
			drawInfoArr=[];
			removeRoute();
		    $(".cityItem").each(function(i, box) {
						//alert($(box).parent().attr("id"))
		        var redate = $(box).parent().attr("id");
		    		startX = $(box).find(
							"input[name=lng]")
							.val();
					startY = $(box).find(
							"input[name=lat]")
							.val();
					var convertChange = new kakao.maps.LatLng(
							startY,
							startX);
					// 배열에 담기
					drawInfoArr.push(convertChange);
				
				
		        $(box).find(".itemNum").html(i + 1);
		        $(box).find("input[name=travelOrder]").val(i + 1);
		        $(box).find("input[name=travelDate]").val(redate);
		        
		        

		    });
		    drawLine(drawInfoArr, "0",
					"#000000", 0);

		}

		
		
		//드래그 가능한 리스트로 만들기
		$(function() {

		    $("#sortable").sortable();

		    $("#sortable").disableSelection();

		});
		//선 긋기
		function drawLine(arrPoint, traffic, color, zindex) {
			var polyline;

			polyline = new kakao.maps.Polyline({
				endArrow : false,
				path : arrPoint,
				strokeColor : color,
				strokeWeight : 3,
				strokeStyle : 'dashed',
				zIndex : zindex

			});
			polyline.setMap(map);
			resultdrawArr.push(polyline);
		}
		//그린 루트 지우기
		function removeRoute(){
			if (resultdrawArr.length > 0) {
				for (var i = 0; i < resultdrawArr.length; i++) {
					resultdrawArr[i].setMap(null);
				}
			}
		}
		
		//시작일과 종료일 사이의 날짜를 구하는 함수 
		function getDateRange(startDate, endDate, listDate){

				var dateMove = new Date(startDate);

				var strDate = startDate;

				if (startDate == endDate)

				{

					var strDate = dateMove.toISOString().slice(0, 10);

					listDate.push(strDate);

				}

				else

				{

					while (strDate < endDate)

					{

						var strDate = dateMove.toISOString().slice(0, 10);

						listDate.push(strDate);

						dateMove.setDate(dateMove.getDate() + 1);

					}

				}

				return listDate;

			};
			
			
			
			//날짜가 들어왔을 때  placesList 에 div 를 만드는 함수  
			function createSchedule(date){
				
				var contents 
				
		        	="<div id='"+date+"' class='dayschedule'>"
		        	+	date
							+ "</div>";
							
					$("#placesList2").append(contents);
			}
			
			
			
			
			//날짜 div안에 생성될 아이템들 만드는 함수 
			function createDaySchedule(date) {
				
					var number = $(".itemNum").innerHTML;
					//travelPlan = $("#travelPlan").val();
					alert(travelPlan);
					var contents

				    = "<div class='cityItem'>"
					      + "<div style='float:left;'>"

					      + "<span class='itemNum'></span> "
								+ "<span>"
								+	"<div class='info' name='cityName'><h5>"+city+"</h5>"
								+ "<input type='hidden' name='travelPlan' value='"+travelPlan+"'/>"
								+ "<input type='hidden' name='trainStation' value='"+stationId+"'/>"
								+ "<input type='hidden' name='travelDate' value='"+date+"'/>"
								+ "<input type='hidden' name='travelOrder'/>"
								+ "<input type='hidden' name='lat' value='"+latitude+"'/>"
								+ "<input type='hidden' name='lng' value='"+longitude+"'/>"
								+ "<input type='button' value='삭제' name='deletePlan'></input>"
								+ "</span>"
								+ "</div>"
								+ "</div>";
								

					$("#"+date+"").append(contents);
					//$("#"+date+"").text();
					
					reorder();
				
			}
			
			
			//form 전달 전에 리네임 하는 함수
			function renameForModelAttribute() {
				$("div[name=cityName]").each(
						function(index) {
							$(this).find("input[name=travelPlan]").attr(
									"name",
									"list[" + index
											+ "].travelPlan");
							$(this).find("input[name=trainStation]").attr(
									"name",
									"list[" + index
											+ "].trainStation");
							$(this).find("input[name=travelDate]").attr(
									"name",
									"list[" + index
											+ "].travelDate");
							$(this).find("input[name=travelOrder]").attr(
									"name",
									"list[" + index
											+ "].travelOrder");
							$(this).find("input[name=lat]").attr(
									"name",
									"list[" + index
											+ "].trainStation.lat");
							$(this).find("input[name=lng]").attr(
									"name",
									"list[" + index
											+ "].trainStation.lng");
					
							//  $(this).find("input[name=targetName]").attr("name", "targets[" + index + "].targetName");
						})
			}
			
			
	
			function totalSchedule(listDate) {
				
				for(var i=0; i < listDate.length;i++){
					createSchedule(listDate[i]);
				}
				
			}

		});//end of ready

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
			center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 
			level : 12
		// 지도의 확대 레벨 
		});

		// 마커 클러스터러를 생성합니다 
		var clusterer = new kakao.maps.MarkerClusterer({
			map : map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
			averageCenter : true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
			minLevel : 10
		// 클러스터 할 최소 지도 레벨 
		});

		var imageSrc = '${pageContext.request.contextPath}/images/train.png', // 마커이미지의 주소입니다    
		imageSize = new kakao.maps.Size(40, 45);
		//imageOption = {offset: new kakao.maps.Point(25, 32)};// 마커이미지의 크기입니다

		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

		// 데이터를 가져오기 위해 jQuery를 사용합니다
		// 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
		$.get("${pageContext.request.contextPath}/data/korail.json", function(
				data) {
			// 데이터에서 좌표 값을 가지고 마커를 표시합니다
			// 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
			var markers = $(data.positions).map(
					function(i, position) {
						return new kakao.maps.Marker({
							position : new kakao.maps.LatLng(position.lat,
									position.lng),
							image : markerImage
						});
					});

			// 클러스터러에 마커들을 추가합니다
			clusterer.addMarkers(markers);
		});
	</script>
</body>
</html>