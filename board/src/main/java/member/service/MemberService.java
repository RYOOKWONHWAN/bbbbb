package member.service;

import member.dto.AuthInfo;
import member.dto.MembersDTO;

public interface MemberService {
	public AuthInfo addMemberProcss(MembersDTO dto);
}
