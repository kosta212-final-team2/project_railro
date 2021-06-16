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
 <script>
  $( function() {
    $( "#datepicker" ).datepicker();
    $( "#datepicker2" ).datepicker();
  } );
  </script>
</head>
<body>
<p style="margin-top:-12px">
</p>
<p>StartDate: <input type="text" id="datepicker"></p>
<p>EndDate: <input type="text" id="datepicker2"></p>
	<div class="map_wrap">
		<div id="map"
			style="width: 100vm; height: 800px; position: relative; overflow: hidden;"></div>

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


	$(function () {
		//alert(1)
		//var itemList=[];
		var markers=[];
		
		
		
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
			
			/* alert($(this).prev().children().eq(0).text())
			alert($(this).prev().children().eq(1).text())
			alert($(this).prev().children().eq(2).text())
			alert($(this).prev().children().eq(3).text())
			alert($(this).prev().children().eq(4).text())
			 */
			city = $(this).prev().children().eq(0).text();
			stationId = $(this).prev().children().eq(1).text();
			address = $(this).prev().children().eq(2).text();
			latitude = $(this).prev().children().eq(3).text();
			longitude = $(this).prev().children().eq(4).text();
			
			
			
			//alert($(this).prev().text())
			/* var t = $(this).prev().children().eq(2);
			var g = $(this).prev().children().eq(3);
			alert(t.text())
			alert(g.text())  */
			
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
			
			
			createItem(); 
	
	});
		
		
		
		
		
		//리스트에 있는 장소 마커 삭제 
		$(document).on("click","#deleteList",function () {
	
			hideMarkers();
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
	    $("#placesList2").sortable({

	        placeholder:"itemBoxHighlight",

	        start: function(event, ui) {

	            ui.item.data('start_pos', ui.item.index());

	        },

	        stop: function(event, ui) {

	            var spos = ui.item.data('start_pos');

	            var epos = ui.item.index();

		    reorder();

	        }

	    });
		
	    function reorder() {

		    $(".cityItem").each(function(i, box) {

		        $(box).find(".itemNum").html(i + 1);

		    });

		}

		
		//아이템 추가하기
		function createItem() {

		    $(createBox())

		    .appendTo("#placesList2")

		    .hover(

		        function() {

		            $(this).css('backgroundColor', '#f9f9f5');

		            $(this).find('.deleteBox').show();

		        },

		        function() {

		            $(this).css('background', 'none');

		            $(this).find('.deleteBox').hide();

		        }

		    )

		    .append("<div class='deleteBox'>[삭제]</div>")

		    .find(".deleteBox").click(function() {

		        var valueCheck = false;

		        $(this).parent().find('input').each(function() {

		            if($(this).attr("name") != "type" && $(this).val() != '') {

		                valueCheck = true;

		            }

		        });



		        if(valueCheck) {

		            var delCheck = confirm('입력하신 내용이 있습니다.\n삭제하시겠습니까?');

		        }

		        if(!valueCheck || delCheck == true) {

		            $(this).parent().remove();

		            reorder();

		        }

		    });

		    // 숫자를 다시 붙인다.

		    reorder();

		}
		
		// 아이템을 구성할 태그를 반환합니다.

		// itemBox 내에 번호를 표시할 itemNum 과 입력필드가 있습니다.
 var index=0;
		
		function createBox() {
		    var contents 

		        = "<div class='cityItem'>"
		      + "<div style='float:left;'>"

		      + "<span class='itemNum'></span> "
					+ "<span>"
					+	"<div class='info'><h5>"+city+"</h5>"
					+ "<input type='text' name='travelDay'/>"
					+ "<input type='hidden' name='list["+index+"].travelPlan' value='1'/>"
					+ "<input type='hidden' name='list["+index+"].trainStation' value='"+stationId+"'/>"
					+ "<input type='hidden' name='list["+index+"].travelDate' value='20210801'/>"
					+ "<input type='hidden' name='list["+index+"].travelOrder' value='1'/>"
					+ "</span>"
					+ "</div>"
					+ "</div>";
					
					index++;
					
					/* var data= 
				    + "<div><input type='hidden' name='list["+index+"].id' value='"+stationId+"'/>"
				    + "<input type='hidden' name='list["+index+"].addr' value='"+address+"'/>"
				    + "<input  type='hidden' name='list["+index+"].lat' value='"+latitude+"'/>"
				    + "<input  type='hidden' name='list["+index+"].lng' value='"+longitude+"'/>"
				    +"<input  type='hidden' name='list["+index+"].planNo' value='"+longitude+"'/><div>";    
				    
		        
					
					//alert(data +" , " + index)
					$("#cityplan").append(data); */
			
		    return contents;

		}
		
		//드래그 가능한 리스트로 만들기
		$(function() {

		    $("#sortable").sortable();

		    $("#sortable").disableSelection();

		});

		

	});//end of ready
	
	
	

	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



		





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
 
    
    var imageSrc = '${pageContext.request.contextPath}/images/train.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(30, 34);
    //imageOption = {offset: new kakao.maps.Point(25, 32)};// 마커이미지의 크기입니다
      
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    
    
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("${pageContext.request.contextPath}/data/korail.json", function(data) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = $(data.positions).map(function(i, position) {
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(position.lat, position.lng),
            		image: markerImage
            });
        });

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });
    
    
    
</script>
</body>
</html>