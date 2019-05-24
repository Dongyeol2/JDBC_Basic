package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.scott_JDBCUtil;
import vo.Book;

public class Test08_select_list {
	public static void main(String[] args) {
		String sql = "select * from book";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0; //1이면 등록성공, 0이면 등록 실패
		List<Book> books = new ArrayList<Book>();
		try {
			con = scott_JDBCUtil.getConnection();
			ps = con.prepareStatement(sql); //sql구문을 미리 컴파일하라고 오라클한테 전달. st보다 ps가 성능 좋음
			//? 셋팅작업
			
			//실행
			rs = ps.executeQuery();
			//결과값 핸들링
			while(rs.next()) { 
				Book book = new Book();
				book.SetBookno(rs.getInt("bookno"));
				book.SetTitle(rs.getString("title"));
				book.SetAuthor(rs.getString("author"));
				book.SetPrice(rs.getInt("price"));
				book.SetPubdate(rs.getDate("pubdate").toString());
				System.out.println(book);
				books.add(book);
			}	
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scott_JDBCUtil.close(con, ps, rs);
		}
		
		System.out.println("Book list");
		for(Book d:books) {
			System.out.println(d);
		}
		System.out.println("-----------------");
		books.forEach(i -> System.out.println(i));
	}
}
