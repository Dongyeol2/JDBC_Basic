package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.scott_JDBCUtil;

public class Test04_Insert {
	public static void main(String[] args) {
		String sql = "insert into book(bookno, title, price)\r\n" + 
				" values((select nvl(max(bookno),0)+1 from book), ? , ?)"; //title, price는 변수로 설정
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0; //1이면 등록성공, 0이면 등록 실패
		
		try {
			con = scott_JDBCUtil.getConnection();
			ps = con.prepareStatement(sql); //sql구문을 미리 컴파일하라고 오라클한테 전달. st보다 ps가 성능 좋음
			//? 셋팅작업
			ps.setString(1, "sql");
			ps.setInt(2, 22000);
			//실행
			row = ps.executeUpdate();
			//결과값 핸들링
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scott_JDBCUtil.close(con, ps, rs);
		}	
	}
}
