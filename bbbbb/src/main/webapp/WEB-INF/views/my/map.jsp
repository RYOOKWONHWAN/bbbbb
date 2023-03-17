<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta charset="UTF-8">
<style>
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=eeb3697ba67b965b981c89f05428889e"></script>
<div id="map" style="width: 100%; height: 650px;"></div>
<table>
	<thead>
		<tr>
			<th>#</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>url</th>

		</tr>

	</thead>
	<tbody>
		<c:if test="${not empty mapdtos }">
			<c:forEach var="dto" items="${mapdtos }" varStatus="status">
				<tr>
					<th scope="row">${status.count}:</th>
					<td id="name${status.count}">${dto.name }</td>
					<td id="address${status.count}">${dto.address }</td>
					<td id="phone${status.count}">${dto.phone }</td>
					<td id="url${status.count}">${dto.url }</td>

				</tr>
				<span id="x${status.count}">${dto.x }</span>
				<span id="y${status.count}">${dto.y }</span>
			</c:forEach>
		</c:if>
		<c:if test="${empty mapdtos }">
			<tr>
				<td colspan="4" class="text-center">검색결과가 없습니다.</td>
			</tr>

		</c:if>



	</tbody>

</table>

<span id="centerx">${x }</span>
<span id="centery">${y }</span>
<script>
	var size = parseInt("${mapdtos.size() }");
	var infowindow = new kakao.maps.InfoWindow({
		zIndex : 1
	});
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new kakao.maps.LatLng(centery.textContent, centerx.textContent), // 지도의 중심좌표
		level : 3
	// 지도의 확대 레벨
	};

	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);

	/* for(var i =0; i<namelist.length; i++){
		console.log(namelist[i])
	} */

	// 마커 찍는 함수 
	var name = "name";
	var x = "x";
	var y = "y";

	for (var i =0; i < size; i++) {
		var nameval = name + i;
		var xval = x + i;
		var yval = y + i;
		var gn = document.getElementById(nameval);
		var gx = document.getElementById(xval);
		var gy = document.getElementById(yval);
		var contn = gn.textContent;
		var contx = gx.textContent;
		var conty = gy.textContent;

		displayMarker(i, contx, conty, contn);
	}

	function displayMarker(idx, place_x, place_y, place_name) {

		// 마커 이미지의 이미지 크기 입니다
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', imageSize = new kakao.maps.Size(
				36, 37), imgOptions = {
			spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
			spriteOrigin : new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset : new kakao.maps.Point(13, 37)
		// 마커 좌표에 일치시킬 이미지 내에서의 좌표
		};
		// 마커 이미지를 생성합니다    
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
				imgOptions);
		// 마커를 생성하고 지도에 표시합니다
		var marker = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(place_y, place_x),
			title : place_name,
			image : markerImage
		});
		// 마커에 클릭이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'click', function() {
			// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			infowindow.setContent('<div style="padding:5px;font-size:12px;">'
					+ place_name + '</div>');
			infowindow.open(map, marker);
		});
		function displayInfowindow(marker, title) {
			var content = '<div style="padding:5px;z-index:1;">' + title
					+ '</div>';
			infowindow.setContent(content);
			infowindow.open(map, marker);
		}
	}
</script>


