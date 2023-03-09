package admin.dao;

import org.apache.ibatis.session.SqlSession;

import admin.dto.AdminDTO;

public class AdminDaoImp implements AdminDAO{

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int addAdmin(AdminDTO dto) {
		return sqlSession.insert("admin.addAdmin", dto);
	}
	@Override
	public AdminDTO selectByAdminId(String adminId) {
		return sqlSession.selectOne("admin.selectByAdminId", adminId);
	}
	@Override
	public void updateAdmin(AdminDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAdmin(AdminDTO dto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int idcheck(String adminId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.idcheck",adminId);
	}


	

}
