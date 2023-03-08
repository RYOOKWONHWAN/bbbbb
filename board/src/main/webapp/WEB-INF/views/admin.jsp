<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- sign up -->
<section class="sign_area">
	<article class="inner">
		<h2>회원가입</h2>
		<p>영원한 도서관 회원가입을 환영합니다.</p>
		<form class="signup_form" action="admin" method="post">
			<ul>
				<li class="id_input">
					<h4>아이디</h4> <input type="text" placeholder="아이디를 입력하세요." />
					<button type="button">아이디 중복확인</button>
					<p class="on">※ 작성오류 문구</p>
				</li>
				<li>
					<h4>비밀번호</h4> <input type="password" placeholder="패스워드를 입력하세요." />
					<p class="on">※ 작성오류 문구</p>
				</li>
				<li>
					<h4>비밀번호 재확인</h4> <input type="password" placeholder="패스워드를 입력하세요." />
					<p class="on">※ 작성오류 문구</p>
				</li>
				<li>
					<h4>성명</h4> <input type="text" placeholder="성명을 입력하세요." />
					<p class="on">※ 작성오류 문구</p>
				</li>
				
			</ul>
			<button type="submit">가입하기</button>
		</form>
	</article>
</section>