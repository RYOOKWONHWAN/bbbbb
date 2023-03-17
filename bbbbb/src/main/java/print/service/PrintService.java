package print.service;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;

public interface PrintService {
	public List<BookmanageDTO> printAll(PageDTO pv);
	public int countAll();
	
	public List<BookmanageDTO> searchByTitleService(String query);
	
	public List<BookmanageDTO> searchByAuthorService(String query);
	
	public List<BookmanageDTO> searchByPubService(String query);
	public int check(String query);
	public List<BookmanageDTO> searchByCatService(String query);
	
}
