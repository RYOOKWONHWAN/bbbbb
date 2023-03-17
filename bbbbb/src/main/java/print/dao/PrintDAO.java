package print.dao;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;

public interface PrintDAO {
	public List<BookmanageDTO> printAll(PageDTO pv);
	public int countAll();
	public List<BookmanageDTO> searchByTitle(String query);
	public List<BookmanageDTO> searchByAuthor(String query);
	public List<BookmanageDTO> searchByPub(String query);
	public int check(String query);
	public List<BookmanageDTO> searchByCat(String query);
}
