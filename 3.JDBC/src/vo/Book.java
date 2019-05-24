package vo;

public class Book {
	private int bookno; //number(5)
	private String title; //varchar(12)
	private String author; //varchar(12),
	private int price; //number(5)
	private String pubdate; //date
	
	public Book() {
		super();
	}
		
	public Book(int bookno, String title, String author, int price, String pubdate) {
		this.bookno = bookno;
		this.title = title;
		this.author = author;
		this.price = price;
		this.pubdate = pubdate;
	}
	
	public Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}


	public void SetBookno(int bookno) {
		this.bookno = bookno;
	}
	public int GetBookno() {
		return bookno;
	}
	
	public void SetTitle(String title) {
		this.title = title;
	}
	public String GetTitle() {
		return title;
	}
	
	public void SetAuthor(String author) {
		this.author = author;
	}
	public String GetAuthor() {
		return author;
	}
	
	public void SetPrice(int price) {
		this.price = price;
	}
	public int GetPrice() {
		return price;
	}
	
	public void SetPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String GetPubdate() {
		return pubdate;
	}
	
	@Override
	public String toString() {
		return "Book [bookno=" + bookno + ", title=" + title + ", author=" + author + ", price=" + price + ", pubdate="
				+ pubdate + "]";
	}
}
