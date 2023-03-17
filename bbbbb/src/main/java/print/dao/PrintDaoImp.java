package print.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;

public class PrintDaoImp implements PrintDAO{
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<BookmanageDTO> printAll(PageDTO pv) {
		// TODO Auto-generated method stub
		System.out.println("dao 실행");
		return sqlSession.selectList("print.printbookAll",pv);
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("count");
	}
	@Override
	public List<BookmanageDTO> searchByAuthor(String query) {
		// TODO Auto-generated method stub
		System.out.println("dao"+query);
		return sqlSession.selectList("print.searchByAuthor",query); 
	}@Override
	public List<BookmanageDTO> searchByPub(String query) {
		// TODO Auto-generated method stub
		System.out.println("dao"+query);
		return sqlSession.selectList("print.searchByPub",query);
	}@Override
	public List<BookmanageDTO> searchByTitle(String query) {
		// TODO Auto-generated method stub
		System.out.println("dao"+query);
		return sqlSession.selectList("print.searchByTitle",query);
	}
	@Override
	public int check(String query) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("print.check",query);
	}
	@Override
	public List<BookmanageDTO> searchByCat(String cate) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("print.searchByCat",cate);
	}
}
