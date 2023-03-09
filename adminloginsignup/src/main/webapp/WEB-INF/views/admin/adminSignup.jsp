<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(
		
		function test(data){
			if (data == "N") { // 만약 성공할시
				alert(JSON.stringify("사용 가능한 아이디입니다."));
			} else { // 만약 실패할시
				alert(JSON.stringify("이미 사용중인 아이디입니다."));
			}
		};
		
	function() {
		$("#idCheck").click(function() {
			let adminId = $("#idCheckInput").val();
			$.ajax({
				type : 'post', //post 형식으로 controller 에 보내기위함!!
				url : "./adminSignup/idcheck", // 컨트롤러로 가는 mapping 입력
				data : {"adminId" : adminId},// 원하는 값을 중복확인하기위해서  JSON 형태로 DATA 전송
				success : test(data);,		
				error : function(error) {
					alert(error);
					}
				});
			});
	
		
			$("input[name='adminPw']").keyup(function() {
				if ($("input[name='adminPw']").val() != $("input[name='adminPwCheck']").val()) {
					$('#pwCheck').addClass('on');
				} else {
					$('#pwCheck').removeClass('on');
					}
				});
			$("input[name='adminPwCheck']").keyup(function() {
				if ($("input[name='adminPw']").val() != $("input[name='adminPwCheck']").val()) {
					$('#pwCheck').addClass('on');
					} else {
						$('#pwCheck').removeClass('on');
					}
			});
		});
	
</script>
<!-- sign up -->
<section class="sign_area">
	<article class="inner">
		<h2>회원가입</h2>
		<p>영원한 도서관 회원가입을 환영합니다.</p>
		<form class="signup_form" action="adminSignup.do" method="post">
			<ul>
				<li class="id_input">
					<h4>아이디</h4> <input type="text" placeholder="아이디를 입력하세요."
					name="adminId" id="idCheckInput" />
					<button type="button" id="idCheck">아이디 중복확인</button>
					<p class="" id="toggle">※ 작성오류 문구</p>
				</li>
				<li>
					<h4>비밀번호</h4> <input type="password" placeholder="패스워드를 입력하세요."
					name="adminPw" />
					<p class="">※ 작성오류 문구</p>
				</li>
				<li>
					<h4>비밀번호 재확인</h4> <input type="password" placeholder="패스워드를 입력하세요."
					name="adminPwCheck" />
					<p class="" id="pwCheck">※ 작성오류 문구</p>
				</li>
				<li>
					<h4>성명</h4> <input type="text" placeholder="성명을 입력하세요."
					name="adminName" />
					<p class="">※ 작성오류 문구</p>
				</li>
			</ul>
			<button type="submit">가입하기</button>
		</form>
	</article>
</section>