<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container-fluid mt-5 mb-5 content_area">
	<div class="card">
		<h5 class="card-header">관리자현황</h5>

		<div class="card-body">
			<p class="card-text">현재 접속중인 관리자 : 회원명(아이디)</p>

			<div class="row">
				<div class="col-md-6">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger fw-bold" data-bs-toggle="modal" data-bs-target="#adminDelModal">직권탈퇴</button>
					<button type="button" class="btn btn-warning fw-bold" data-bs-toggle="modal" data-bs-target="#adminPwMobModal">비밀번호 변경</button>

					<!-- Modal -->
					<div class="modal fade" id="adminDelModal" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5">회원명님 : 아이디</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<div class="modal-body text-start">
									<p>관리자 탈퇴를 진행하시겠습니까?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
									<form>
										<input type="hidden" value="">
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
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<form>
									<div class="modal-body text-start">
										<div class="input-group mb-3">
											<span class="input-group-text">기존 비밀번호</span>
											<input type="password" class="form-control" placeholder="비밀번호를 입력하세요.">
											<p class="input_inner_err">※ 입력 오류 문구</p>
										</div>
										<div class="input-group mb-3">
											<span class="input-group-text">비밀번호</span>
											<input type="password" class="form-control" placeholder="비밀번호를 입력하세요.">
											<p class="input_inner_err">※ 입력 오류 문구</p>
										</div>
										<div class="input-group mb-3">
											<span class="input-group-text">비밀번호 재확인</span>
											<input type="password" class="form-control" placeholder="비밀번호를 다시 입력하세요.">
											<p class="input_inner_err">※ 입력 오류 문구</p>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
										<input type="hidden" value="">
										<button type="submit" class="btn btn-warning">비밀번호 변경</button>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-6">
					<form>
						<div class="input-group mb-3">
							<select class="form-select">
								<option value="">회원명</option>
								<option value="">아이디</option>
							</select>
							<input type="text" class="form-control" placeholder="검색어를 입력하세요.">
							<button class="btn btn-outline-primary" type="button">검색</button>
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
					<tr>
						<th class="align-middle" scope="row">1</th>
						<td class="align-middle">abcd1234</td>
						<td class="align-middle">김누구</td>
					</tr>
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item">
						<a class="page-link" href="#" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#" aria-label="Previous">
							<span aria-hidden="true">&lsaquo;</span>
						</a>
					</li>
					<li class="page-item active">
						<a class="page-link" href="#">1</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#">2</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#">3</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#" aria-label="Next">
							<span aria-hidden="true">&rsaquo;</span>
						</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</div>