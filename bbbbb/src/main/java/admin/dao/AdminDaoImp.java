package admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;

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
	public AdminDTO selectByAdminId(adminAuthInfo auth) {
		return sqlSession.selectOne("admin.selectByadmin_id", auth);
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
		System.out.println("dao imp 실행됨" );
		return sqlSession.selectOne("admin.idcheck",adminId);
	}
	@Override
	public List<AdminDTO> printAdmin() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("printAdmin");
	}
	@Override
	public List<AdminDTO> printUser() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("printUser");
	}
	

}
