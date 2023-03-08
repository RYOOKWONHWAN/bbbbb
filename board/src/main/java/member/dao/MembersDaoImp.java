package member.dao;

import org.apache.ibatis.session.SqlSession;

import member.dto.MembersDTO;

public class MembersDaoImp implements MembersDAO {
	private SqlSession sqlSession;
	
	public MembersDaoImp() {
	
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertMember(MembersDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("members.insertMember", dto);
	}
}
