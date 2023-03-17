<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 통합도서조회 -->
<section class="subpage_wrap">
	<div class="inner">
		<h2>통합도서조회</h2>
		<div class="contents books_cont">
			<c:set var="index" value="${1}" />

			<p class="books_tab">
				<a class="books_tab_1 active"
					onclick="<c:set var="index" value="${1}" />">도서조회</a> · <a
					class="books_tab_2" onclick="<c:set var="index" value="${2}" />">신착도서</a>
				· <a class="books_tab_3"
					onclick="<c:set var="index" value="${3}" />">인기도서</a>
			</p>

			<div class="books_tab_cont_1 active">
				<div class="books_search_area">
					<form action="search" method="post">
						<select name="search_item">
							<option value="search_title" selected="selected">도서명</option>
							<option value="search_author">저자명</option>
							<option value="search_publisher">발행처</option>
						</select> <input type="text" name="query" placeholder="검색어를 입력하세요." />

						<!-- 도서분류값 -->
						<input class="bn_input" type="hidden" value="all" />

						<button type="submit">
							<img src="/myapp/resources/assets/images/search_icon.png"
								alt="돋보기 검색 아이콘" />
						</button>
					</form>
				</div>
				<p class="bn_area">
					<a class="bn_all" onclick="sendData('a')">전체</a> <span>·</span> <a
						class="bn_0" onclick="sendData('b')">총류</a> <span>·</span> <a
						class="bn_1" onclick="sendData('c')">철학</a> <span>·</span> <a
						class="bn_2" onclick="sendData('d')">종교</a> <span>·</span> <a
						class="bn_3" onclick="sendData('e')">사회과학</a><span>·</span> <a
						class="bn_4" onclick="sendData('f')">순수과학</a> <span>·</span> <a
						class="bn_5" onclick="sendData('g')">기술과학</a> <span>·</span> <a
						class="bn_6" onclick="sendData('h')">예술</a> <span>·</span> <a
						class="bn_7" onclick="sendData('i')">언어</a> <span>·</span> <a
						class="bn_8" onclick="sendData('j')">문학</a> <span>·</span> <a
						class="bn_9" onclick="sendData('k')">역사</a>

				</p>
				<c:if test="${empty printdtos && empty search_result}">
					<h4 class="booklist_text">
						<!-- 총 <span>0000</span>건 -->
						"<span class="search_text">검색어 검색어 검색어 검색어 검색어 검색어 검색어</span>"에 대한
						<span>0000</span>건의 검색 결과
					</h4>
				</c:if>

				<div class="booklist_box">
					<c:if test="${not empty printdtos && empty search_result}">
						<c:forEach var="dto" items="${printdtos }">
							<div class="list">
								<a href="${pageContext.request.contextPath}/books/view"> <img
									src="${dto.thumbnail }" alt="책 표지" />
								</a>
								<div class="list_text">

									<p>
										제목 : ${dto.title} <br /> 저자 : ${dto.author }<br /> 출판사 :
										${dto.publisher }<br /> 평점 : <span>★4.5</span> (24)
									</p>
									<p>${dto.contents }</p>
								</div>
								<p>
									대출 : <span>${dto.loan_state }</span> <br> 예약 : <span>${dto.borrow_state }</span>
								</p>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${not empty  search_result&& empty printdtos}">
						<c:forEach var="dto" items="${search_result }">
							<div class="list">
								<a href="${pageContext.request.contextPath}/books/view"><img
									src="${dto.thumbnail }" alt="책 표지" /> </a>
								<div class="list_text">

									<p>
										제목 : ${dto.title} <br /> 저자 : ${dto.author }<br /> 출판사 :
										${dto.publisher }<br /> 평점 : <span>★4.5</span> (24)
									</p>
									<p>${dto.contents }</p>
								</div>
								<p>
									대출 : <span>${dto.loan_state }</span> <br> 예약 : <span>${dto.borrow_state }</span>
								</p>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${not empty  catedtos }">
						<c:forEach var="dto" items="${catedtos}">
							<div class="list">
								<a href="${pageContext.request.contextPath}/books/view"><img
									src="${dto.thumbnail }" alt="책 표지" /> </a>
								<div class="list_text">

									<p>
										제목 : ${dto.title} <br /> 저자 : ${dto.author }<br /> 출판사 :
										${dto.publisher }<br /> 평점 : <span>★4.5</span> (24)
									</p>
									<p>${dto.contents }</p>
								</div>
								<p>
									대출 : <span>${dto.loan_state }</span> <br> 예약 : <span>${dto.borrow_state }</span>
								</p>
							</div>
						</c:forEach>
					</c:if>

				</div>

				<div class="pagination_area">
					<ul class="pagination">
						<!-- 처음 출력 시작 -->
						<c:choose>
							<c:when test="${pv.startPage >1}">
								<li class="page-item disabled"><a class="page-link"
									style="cursor: pointer;" href="list?currentPage=1"> <img
										style="opacity: .5;"
										src="/myapp/resources/assets/images/first.svg"> <span>first</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/first.svg"> <span>first</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 처음 출력 끝 -->

						<!-- 이전 출력 시작 -->
						<c:choose>
							<c:when test="${pv.startPage >1}">
								<li class="page-item disabled"><a class="page-link"
									style="cursor: pointer;"
									href="list?currentPage=${pv.startPage-pv.blockPage}"> <img
										style="opacity: .5;"
										src="/myapp/resources/assets/images/prev.svg"> <span>prev</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/prev.svg"> <span>prev</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 이전 출력 끝 -->

						<!-- 페이지번호 출력 시작 -->
						<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
							<c:choose>
								<c:when test="${i==pv.currentPage}">
									<li class="page-item active"><a class="page-link"
										style="cursor: pointer;" href="list?currentPage=${i}">${i}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;" href="list?currentPage=${i}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 페이지번호 출력 끝 -->

						<!-- 다음 출력 시작 -->
						<c:choose>
							<c:when test="${pv.endPage < pv.totalPage}">
								<li class="page-item"><a class="page-link"
									style="cursor: pointer;"
									href="list?currentPage=${pv.startPage + pv.blockPage}"> <img
										style="opacity: .5;"
										src="/myapp/resources/assets/images/next.svg"> <span>next</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/next.svg"> <span>next</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 다음 출력 끝 -->

						<!-- 마지막 출력 시작 -->
						<c:choose>
							<c:when test="${pv.endPage < pv.totalPage}">
								<li class="page-item"><a class="page-link"
									style="cursor: pointer;"
									href="list?currentPage=${pv.totalPage}"> <img
										style="opacity: .5;"
										src="/myapp/resources/assets/images/last.svg"> <span>last</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/last.svg"> <span>last</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 마지막 출력 끝 -->

					</ul>
				</div>

				<div class="books_tab_cont_2">
					<%
					out.flush();
					RequestDispatcher dispatcher1 = request.getRequestDispatcher("new.jsp");
					dispatcher1.include(request, response);
					%>
				</div>

				<div class="books_tab_cont_3">
					<%
					out.flush();
					RequestDispatcher dispatcher2 = request.getRequestDispatcher("popular.jsp");
					dispatcher2.include(request, response);
					%>
				</div>
			</div>
		</div>
</section>


<script>
	for(let i=1; i<12; i++){
		if($('.bn_input').val() == $('.bn_area a:nth-of-type('+ i +')').attr('class').split('_')[1]){
			$('.bn_area a.bn_' + String($('.bn_input').val())).addClass('active');
		}
	}
	
	$('.bn_area a').on('click', function(){
		$('.bn_area a').removeClass('active');
		$('.bn_input').val($(this).attr('class').split('_')[1]);
		$(this).addClass('active');
	})


	$('.books_tab_1').on('click', function() {
		if (!$('.books_tab_cont_1').hasClass('active')) {
			$('.books_tab_1').addClass('active');
			$('.books_tab_2').removeClass('active');
			$('.books_tab_3').removeClass('active');
			
			$('.books_tab_cont_1').addClass('active');
			$('.books_tab_cont_2').removeClass('active');
			$('.books_tab_cont_3').removeClass('active');
		}
	});
	
	$('.books_tab_2').on('click', function() {
		if (!$('.books_tab_cont_2').hasClass('active')) {
			$('.books_tab_2').addClass('active');
			$('.books_tab_1').removeClass('active');
			$('.books_tab_3').removeClass('active');
			
			$('.books_tab_cont_2').addClass('active');
			$('.books_tab_cont_1').removeClass('active');
			$('.books_tab_cont_3').removeClass('active');
		}
	});

	$('.books_tab_3').on('click', function() {
		if (!$('.books_tab_cont_3').hasClass('active')) {
			$('.books_tab_3').addClass('active');
			$('.books_tab_1').removeClass('active');
			$('.books_tab_2').removeClass('active');
			
			$('.books_tab_cont_3').addClass('active');
			$('.books_tab_cont_1').removeClass('active');
			$('.books_tab_cont_2').removeClass('active');
		}
	});
	
	function sendData(value) {
		  $.ajax({
		    url: "searchByCategories",
		    type: "POST",
		    data: { selectedValue: value },
		    success: function() {
		      console.log("Data sent successfully!");
		    },
		    error: function() {
		      console.log("Error sending data!");
		    }
		  });
		}
</script>