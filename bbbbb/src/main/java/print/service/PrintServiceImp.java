package print.service;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import print.dao.PrintDAO;
import print.dto.PageDTO;

public class PrintServiceImp implements PrintService{
	
	PrintDAO printDao;
	
	public void setPrintDao(PrintDAO dao) {
		this.printDao = dao;
	}
	@Override
	public List<BookmanageDTO> printAll(PageDTO pv) {
		// TODO Auto-generated method stub
		System.out.println("서비스 실행");
		return printDao.printAll(pv);
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return printDao.countAll();
	}
	@Override
	public List<BookmanageDTO> searchByAuthorService(String query) {
		// TODO Auto-generated method stub
		System.out.println("서비스"+query);
		return printDao.searchByAuthor(query);
	}
	@Override
	public List<BookmanageDTO> searchByPubService(String query) {
		// TODO Auto-generated method stub
		System.out.println("서비스"+query);
		return printDao.searchByPub(query);
	}
	@Override
	public List<BookmanageDTO> searchByTitleService(String query) {
		// TODO Auto-generated method stub
		System.out.println("서비스"+query);
		return printDao.searchByTitle(query);
	}
	@Override
	public int check(String query) {
		// TODO Auto-generated method stub
		return printDao.check(query);
	}
	@Override
	public List<BookmanageDTO> searchByCatService(String query) {
		// TODO Auto-generated method stub
		return printDao.searchByCat(query);
	}
}
