package member.dto;

//로그인 성공 후 인증 상태 정보를 세션에 보관할 때 사용
public class AuthInfo {

	private String memberId;
	private String memberName;
	private String memberPass;
	
	public AuthInfo() {
	
	}

	public AuthInfo(String memberId,String memberPass) {
	
		this.memberId = memberId;
		
		this.memberPass = memberPass;
	}
	
	public AuthInfo(String memberId, String memberName, String memberPass) {
		
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPass = memberPass;
	}

	public String getMemberid() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberPass() {
		return memberPass;
	}
	
	
	
}
