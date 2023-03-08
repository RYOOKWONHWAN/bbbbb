package member.service;

import member.dto.AuthInfo;
import member.dto.MembersDTO;
import member.dao.MembersDAO;

public class MemberServiceImp implements MemberService{
	private MembersDAO membersDao;
	
	public MemberServiceImp() { 
		// TODO Auto-generated constructor stub
	}
	
	public void setMembersDao(MembersDAO membersDao) {
		this.membersDao = membersDao;
	}

	@Override
	public AuthInfo addMemberProcss(MembersDTO dto) {
		membersDao.insertMember(dto);
		return new AuthInfo(dto.getId(), dto.getName(), dto.getPw());
	}
}
