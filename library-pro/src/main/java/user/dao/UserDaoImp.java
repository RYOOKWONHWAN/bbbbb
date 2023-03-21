package user.dao;

import org.apache.ibatis.session.SqlSession;

import user.dto.AuthInfo;
import user.dto.UserDTO;

public class UserDaoImp implements UserDAO {
	private SqlSession sqlSession;

	public UserDaoImp() {
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertUser(UserDTO dto) {
		return sqlSession.insert("user.addUser", dto);
	}

	@Override
	public UserDTO selectByUserId(AuthInfo authinfo) {
		return sqlSession.selectOne("user.selectByUserId", authinfo);
	}

	@Override
	public int idcheck(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.idcheck", user_id);
	}

	@Override
	public UserDTO selectByUserIdString(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.selectByUserIdString", user_id);
	}

	@Override
	public String getKeynum(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.getKeynum", user_id);
	}

	// 주소 수정
	@Override
	public void updateAddress(UserDTO dto) {
		sqlSession.update("user.updateAddress", dto);

	}

	// 비밀번호 수정
	@Override
	public void updatePass(UserDTO dto) {
		sqlSession.update("user.updatePassword", dto);

	}

	@Override
	public void deleteUser(String user_id) {
		sqlSession.delete("user.deleteUser", user_id);
	}

}
