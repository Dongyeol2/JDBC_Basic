package App;

import java.util.List;

import dao.BookDao;
import service.BookService;
import service.BookServiceImpl;
import vo.Book;

public class BookApp {
	public static void main(String[] args) {
		
		BookDao dao = new BookDao();
		BookService service = new BookServiceImpl(dao);
		
		Book user = new Book("ajax", "kim", 12000000);
		try {
			service.addBook(user);
		} catch (Exception e) {
			System.out.println("등록 데이터 확인 필요");
		}
		
		if(service != null) {
			List<Book> list = service.bookList();
			list.forEach(i -> System.out.println(i));
		}
	}
}
