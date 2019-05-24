package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.scott_JDBCUtil;

public class Test07_select {
	public static void main(String[] args) {
		String sql = "select * from book";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0; //1이면 등록성공, 0이면 등록 실패
		
		try {
			con = scott_JDBCUtil.getConnection();
			ps = con.prepareStatement(sql); //sql구문을 미리 컴파일하라고 오라클한테 전달. st보다 ps가 성능 좋음
			//? 셋팅작업
			
			//실행
			rs = ps.executeQuery();
			//결과값 핸들링
			while(rs.next()) { 
				System.out.print(rs.getInt("bookno")+"  ");
				System.out.print(rs.getString("title")+"  ");
				System.out.print(rs.getString("author")+"  ");
				System.out.print(rs.getInt("price")+"  ");
				System.out.print(rs.getDate("pubdate")+"  ");
				System.out.println();
			}	
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scott_JDBCUtil.close(con, ps, rs);
		}	
	}
}
