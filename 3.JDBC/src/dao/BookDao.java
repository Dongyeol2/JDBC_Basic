package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;
import util.scott_JDBCUtil;
import vo.Book;

public class BookDao {
	
	//select * from book;
	public List<Book> bookList() {
		List<Book> list = new ArrayList<>();
		String sql = "select * from book";
		
		Connection con = null;
		PreparedStatement  ps = null;
		ResultSet rs = null;
		
		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.SetTitle(rs.getString("title"));
				book.SetPrice(rs.getInt("price"));
				
				list.add(book);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con, ps, rs);
		}
		return list;
	}
	
	public int addBook(Book vo) throws Exception{
		String sql = "insert into book(bookno, title, author, price)\r\n" + 
				" values((select nvl(max(bookno),0)+1 from book),?, ? , ?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0; //1이면 등록성공, 0이면 등록 실패
		
		try {
			con = scott_JDBCUtil.getConnection();
			ps = con.prepareStatement(sql); //sql구문을 미리 컴파일하라고 오라클한테 전달. st보다 ps가 성능 좋음
			ps.setString(1, vo.GetTitle());
			ps.setString(2, vo.GetAuthor());
			ps.setInt(3, vo.GetPrice());
			
			row = ps.executeUpdate();
			
			
		}finally {
			scott_JDBCUtil.close(con, ps, rs);
		}
		
		return row;
	}
	
}
