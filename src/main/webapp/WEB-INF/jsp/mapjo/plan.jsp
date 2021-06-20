<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
 <script>
  $( function() {
    $( "#datepicker" ).datepicker({dateFormat : 'yy-mm-dd'});
    $( "#datepicker2" ).datepicker({dateFormat : 'yy-mm-dd'});
  } );
  </script>
</head>
<body>
<p style="margin-top:-12px">
</p>
<input type="hidden" id="travelPlan">
<input type="hidden" id="datepicker"><!-- 수정요 넘겨받을 값  -->
<input type="hidden" id="datepicker2">
	<div class="map_wrap">
		<div id="map"
			style="width: 100vm; height: 100vh; position: relative; overflow: hidden;"></div>

		<!-- 역 검색하기  -->
<!-- 		<div id="menu_wrap" class="bg_white">
			<div class="option">
				<div>
					<input type="text" name="keyword" id="keyword" size="15">
					<button type="button" id="search">search</button>
					<input type='button' value="delete markers" id="deleteList">
					<input type='hidden' value="delete markers" > 
					
				</div>
			</div>
			<hr>
			검색 결과가 출력되는 곳 
			<ul id="placesList"></ul>
			<div id="pagination"></div>
		</div> -->


		<!-- add list  -->
	<form name="plan" action="${pageContext.request.contextPath}/map/updateForm" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type="hidden" name="stationPlanNum" value="${planId}"/>
		<div id="menu_wrap" class="bg_white">
			<div class="option">
					<div id="cityplan">
						<h2>LIST</h2>
					</div>	
			</div>
			<hr>


				<ul id="placesList2">
				</ul>

				<div id="pagination"></div>
		</div>
		</form>

	</div>


	<script type="text/javascript">


	$(function () {
		
		function getPlan(){
			$.ajax({
				url: "${pageContext.request.contextPath}/mapjo/cityUpdateForm2",//서버주소 
				type: "get",// 요쳥방식 get post put delete
				dataType: "json",//서버가 보내오는 데이터 타입 -응답 : text, html, xml, json
				data: {planId : planId },//서버에게 보낼 parameter 정보 
				success: function (result) {
					
			//		alert(result)
					$.each(result, function(index, item){
			//			alert(index +" , " + item +" , " + item.stationPlanId+","+ item.trainStation.id+","+item.travelDate.toString().substr(0, 10)+","+item.trainStation.station)
					var contents="";
										 			contents += "<div class='cityItem'>"
													contents += "<div style='float:left;'>"

													contents += "<span class='itemNum'></span> "
													contents += "<span>"
													contents += "<div class='info' name='cityName'><h5>"
													contents += item.trainStation.station
													contents += "</h5>"
													contents += "<form name='plan' action='${pageContext.request.contextPath}/map/updateForm' method='post'>"
													contents += "<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />"
													
													contents += "<input type='hidden' name='stationPlanNum' value='"+item.stationPlanId+"'/>"
													contents += "<input type='hidden' name='stationPlanId' value='"+planId+"'/>"
													contents += "<input type='hidden' name='trainStation' value='"+item.trainStation.id+"'/>"
													contents += "<input type='hidden' name='travelDate' value='"+item.travelDate.toString().substr(0, 10)+"'/>"
													contents += "<input type='hidden' name='travelOrder' value='"+item.travelOrder+"'/>"
													contents += "<input type='hidden' name='lat' value='"+item.trainStation.lat+"'/>"
													contents += "<input type='hidden' name='lng' value='"+item.trainStation.lng+"'/>"
													contents += "<input type='submit' value='세부일정계획' name='detailedPlan'></input>"
													contents += "</span></div></div>"; 
													contents += "</form>"; 
													
													//alert(item.trainStation.lat)
											
													var markerPosition = new kakao.maps.LatLng(item.trainStation.lat, item.trainStation.lng);
													
													var marker = new kakao.maps.Marker({
														position : markerPosition
													});

													marker.setMap(map);
													markers.push(marker);
													
													 
													var iwContent = "<div style='padding:5px;'>"+item.trainStation.station+"<br>"
													+ "<a href='https://www.youtube.com/results?search_query="+item.trainStation.station+"여행' style='color:blue' target='_blank'>YouTube</a>"
													+ "   |  <a href='https://map.kakao.com/link/to/"+item.trainStation.station+","+item.trainStation.lat+","+item.trainStation.lng+"' style='color:blue' target='_blank'>길찾기</a></div>"
													 
													var iwRemoveable = true;
												  var iwPosition = new kakao.maps.LatLng(item.trainStation.lat, item.trainStation.lng); //인포윈도우 표시 위치입니다

												// 인포윈도우를 생성합니다
													var infowindow = new kakao.maps.InfoWindow({
												    position : iwPosition, 
												    content : iwContent,
												    removable : iwRemoveable
													});
												  
												// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
												infowindow.open(map, marker); 
													
													
													
													
													

												$("#" + item.travelDate.toString().substr(0, 10) + "").append(contents);
												})

												reorder();

							},
							error : function(err) {
								consol.log(err + ": error occured")
							}

						});//end of ajax
					}
		
		var planId = "${planId}"
	//	alert(planId)
		$.ajax({
			url: "${pageContext.request.contextPath}/planData",//서버주소 
			type: "get",// 요쳥방식 get post put delete
			dataType: "json",//서버가 보내오는 데이터 타입 -응답 : text, html, xml, json
			data: {"planId" : planId },//서버에게 보낼 parameter 정보 
			success: function (result) {
				startDate=result.startDate.toString().substr(0, 10);
				endDate=result.endDate.toString().substr(0, 10);
			
				$("#datepicker").val(startDate);
				$("#datepicker2").val(endDate);
				
				var listDate = [];

				getDateRange(startDate, endDate, listDate);
				console.log(listDate);


		totalSchedule(listDate);

				//sortable();
				getPlan();
				//reorder();

						},
						error : function(err) {
							consol.log(err + ": error occured")
						}

					});//end of ajax
					
	
			//var itemList=[];
			var resultdrawArr=[];
			var drawInfoArr=[];

			var markers = [];
			var travelPlan;
			var sList = "${stationUpdate}";
	
		//	alert(startDate)
		

			

			var city;
			var stationId;
			var address;
			var latitude;
			var longitude;

			//리스트에 있는 장소 마커 추가 
			$(document).on("click", "#addList", function() {

				city = $(this).prev().children().eq(0).text();
				stationId = $(this).prev().children().eq(1).text();
				address = $(this).prev().children().eq(2).text();
				latitude = $(this).prev().children().eq(3).text();
				longitude = $(this).prev().children().eq(4).text();

				//alert($(this).attr("name"))
				var a = $(this).attr("name").split(",")

				var markerPosition = new kakao.maps.LatLng(a[0], a[1]);
				//var markerPosition  = new kakao.maps.LatLng(t.text(), g.text()); 

				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
					position : markerPosition
				});

				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);
				//marker.setMap(null);
				markers.push(marker);

				createDaySchedule(startDate);
				//createItem(startDate);

			});

		
			//세부일정계획 버튼 이벤트 
			$(document).on('click', 'input[name=detailedPlan]', function() {
				//alert(1)

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
			


		    function reorder() {
		    	//alert(markers)
				drawInfoArr=[];
				removeRoute();
				hideMarkers();
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
												
						//마커 새로 생성 
						 marker = new kakao.maps.Marker({
							position : convertChange
						});

						// 마커가 지도 위에 표시되도록 설정합니다
						marker.setMap(map);
						markers.push(marker);
					

						$(box).find(".itemNum").html(i + 1);
			        $(box).find("input[name=travelOrder]").val(i + 1);
			        $(box).find("input[name=travelDate]").val(redate);
			        

			    });
				
				
			    drawLine(drawInfoArr, "0",
						"#000000", 0);
			    

			}
		    


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

			function getDateRange(startDate, endDate, listDate) {
				var dateMove = new Date(startDate);
				var strDate = startDate;
				if (startDate == endDate) {
					var strDate = dateMove.toISOString().slice(0, 10);
					listDate.push(strDate);
				} else {
					while (strDate < endDate) {
						var strDate = dateMove.toISOString().slice(0, 10);
						listDate.push(strDate);
						dateMove.setDate(dateMove.getDate() + 1);
					}
				}
				return listDate;
			}
			;

			//날짜가 들어왔을 때  placesList 에 div 를 만드는 함수  
			function createSchedule(date) {

				var contents

				= "<div id='"+date+"' class='dayschedule'>" + date + "</div>";

				$("#placesList2").append(contents);
			}

			//날짜 div안에 생성될 아이템들 만드는 함수 
			function createDaySchedule(date) {

				var number = $(".itemNum").innerHTML;
				travelPlan = $("#travelPlan").val();
				planId=${planId}
				var contents

				= "<div class='cityItem'>"
						+ "<div style='float:left;'>"

						+ "<span class='itemNum'></span> "
						+ "<span>"
						+ "<div class='info' name='cityName'><h5>"
						+ city
						+ "</h5>"

						+ "<form name='plan' action='${pageContext.request.contextPath}/map/updateForm' method='post'>"
						+ "<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />"
						
						+ "<input type='hidden' name='stationPlanNum' value='"+item.stationPlanId+"'/>"
						+ "<input type='hidden' name='trainStation' value='"+stationId+"'/>"
						+ "<input type='hidden' name='travelDate' value='"+date+"'/>"
						+ "<input type='hidden' name='travelOrder'/>"
						+ "<input type='hidden' name='lat' value='"+latitude+"'/>"
						+ "<input type='hidden' name='lng' value='"+longitude+"'/>"
						+ "<input type='submit' value='세부일정계획' name='detailedPlan'></input>"
						+ "</span>" + "</div>" + "</div>"
						+ "</form>";

				$("#" + date + "").append(contents);
				//$("#"+date+"").text();

				reorder();

			}

			//form 전달 전에 리네임 하는 함수
			function renameForModelAttribute() {
				$("div[name=cityName]")
						.each(
								function(index) {
									$(this)
											.find("input[name=stationPlanId]")
											.attr(
													"name",
													"list[" + index
															+ "].stationPlanId");
									$(this).find("input[name=stationPlanNum]")
											.attr(
													"name",
													"list[" + index
															+ "].stationPlanNum");
									$(this).find("input[name=trainStation]")
											.attr(
													"name",
													"list[" + index
															+ "].trainStation");
									$(this).find("input[name=travelDate]")
											.attr(
													"name",
													"list[" + index
															+ "].travelDate");
									$(this).find("input[name=travelOrder]")
											.attr(
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

				for (var i = 0; i < listDate.length; i++) {
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

	</script>
</body>
</html>