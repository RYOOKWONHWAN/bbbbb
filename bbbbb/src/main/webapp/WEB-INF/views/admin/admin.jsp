<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginPw" value="${sessionScope.adminauthInfo.loginPw}" />

<script>
	function check() {
		var checkpa = document.getElementById("checkpa");

		var loginPw = "${sessionScope.adminauthInfo.loginPw}";
		var check = document.getElementById("oldpass").value;
		var newpass = document.getElementById("newpass").value;
		var newpasscheck = document.getElementById("newpasscheck").value;
		var able = false;
		if (check.value == "") {
			alert("기존 비밀번호를 입력하세요");
			check.focus();
			return false;
		}
		if (newpass.value == "") {
			alert("새로운 비밀번호를 입력하세요");
			newpass.focus();
			return false;
		}
		if (newpasscheck.value == "") {
			alert("비밀번호확인을 입력하세요");
			newpasscheck.focus();
			return false;
		}
		if (newpass.value != newpasscheck.value) {
			alert("비밀번호가 다릅니다.");
			newpasscheck.focus();
			return false;
		}
		if (check === loginPw) {
			alert("비밀번호가 일치합니다.");
			able = true;
		} else {
			alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			check.value = "";
			check.focus();
			return false;
		}
		if (able == true) {
			alert("비밀번호가 변경되었습니다. 다시 로그인해주세요");
			checkpa.action = "changepass";
			checkpa.method = "post";
			checkpa.submit();
			check.value = "";
			newpass.value = "";
			newpasscheck.value = "";
		}
		if (able == false) {
			alert("비밀번호가 확인되지 않습니다. 잠시후에 다시 시도해주세요")
		}

	}
	function checkpass() {

		var loginPw = "${sessionScope.adminauthInfo.loginPw}"
		var check = document.getElementById("oldpass").value;

		var able = false;

		if (check === loginPw) {
			document.getElementById("resultcheck").textContent = "비밀번호가 일치합니다.";
			able = true;
		} else {
			document.getElementById("resultcheck").textContent = "비밀번호가 일치하지 않습니다.";
			check.value = "";
			check.focus();
			return false;

		}

	}
</script>
<div class="container-fluid mt-5 mb-5 content_area">
	<div class="card">
		<h5 class="card-header">관리자현황</h5>

		<div class="card-body">
			<p class="card-text">현재 접속중인 관리자
				:${sessionScope.adminauthInfo.loginId}(${sessionScope.adminName})</p>

			<div class="row">
				<div class="col-md-6">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger fw-bold"
						data-bs-toggle="modal" data-bs-target="#adminDelModal">직권탈퇴</button>
					<button type="button" class="btn btn-warning fw-bold"
						data-bs-toggle="modal" data-bs-target="#adminPwMobModal">비밀번호
						변경</button>

					<!-- Modal -->
					<div class="modal fade" id="adminDelModal" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5">회원명님 : 아이디</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body text-start">
									<p>관리자 탈퇴를 진행하시겠습니까?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">취소</button>
									<form action="deleteAdmin" method="post">
										<input type="hidden" name="admin_id"
											value=${sessionScope.adminauthInfo.loginId }>
										<button type="submit" class="btn btn-danger">관리자 탈퇴</button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<div class="modal fade" id="adminPwMobModal" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5">비밀번호 변경</h1>

									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<form id="checkpa">
									<input type="hidden" name="admin_id" id="admin_id"
										value=${sessionScope.adminauthInfo.loginId }>
									<div class="modal-body text-start">
										<div class="input-group mb-3">
											<span class="input-group-text">기존 비밀번호</span> <input
												type="password" id="oldpass" name="oldpass"
												onblur="checkpass()" class="form-control"
												placeholder="비밀번호를 입력하세요." autofocus="autofocus">
											<p id="resultcheck" class="input_inner_err">※ 입력 오류 문구</p>
										</div>
										<div class="input-group mb-3">
											<span class="input-group-text">비밀번호</span> <input
												type="password" class="form-control" name="newpass"
												id="newpass" placeholder="비밀번호를 입력하세요.">
											<p class="input_inner_err">※ 입력 오류 문구</p>
										</div>
										<div class="input-group mb-3">
											<span class="input-group-text">비밀번호 재확인</span> <input
												type="password" class="form-control" id="newpasscheck"
												placeholder="비밀번호를 다시 입력하세요.">
											<p class="input_inner_err">※ 입력 오류 문구</p>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">취소</button>
										<input type="hidden" value="">
										<button type="button" onclick="check()"
											class="btn btn-warning">비밀번호 변경</button>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-6">
					<form action="searchAdmin" method="post">
						<div class="input-group mb-3">
							<select name="option" class="form-select">
								<option value="nmq">회원명</option>
								<option value="id">아이디</option>
							</select> <input type="text" class="form-control" name="search"
								placeholder="검색어를 입력하세요.">
							<button class="btn btn-outline-primary" type="submit">검색</button>
						</div>
					</form>
				</div>

			</div>

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">아이디</th>
						<th scope="col">회원명</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty searchadmindtos }">
						<c:forEach var="dto" items="${searchadmindtos }"
							varStatus="status">
							<tr>
								<th class="align-middle" scope="row">${status.count }:</th>
								<td class="align-middle">${dto.admin_id }</td>
								<td class="align-middle">${dto.admin_name }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty searchadmindtos and empty dtos}">
						<tr>
							<td colspan="3" class="text-center">검색결과가 없습니다.</td>
						</tr>


					</c:if>
					<c:if test="${not empty dtos}">
						<c:forEach var="dto" items="${dtos }" varStatus="status">
							<tr>
								<th class="align-middle" scope="row">${status.count }:</th>
								<td class="align-middle">${dto.admin_id }</td>
								<td class="align-middle">${dto.admin_name }</td>
							</tr>
						</c:forEach>
					</c:if>




				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&lsaquo;</span>
					</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&rsaquo;</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>