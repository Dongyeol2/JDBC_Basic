package service;

import java.util.List;

import dao.BookDao;
import vo.Book;

public class BookServiceImpl implements BookService{
	
	private BookDao dao = null;

	public BookServiceImpl() {
		super();
	}
	public BookServiceImpl(BookDao dao) {
		super();
		this.dao = dao;
	}
	
	public BookDao getDao() {
		return dao;
	}

	public void setDao(BookDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Book> bookList() {
		return dao.bookList();
	}
	@Override
	public int addBook(Book vo) throws Exception {
		return dao.addBook(vo);
	}	
}
