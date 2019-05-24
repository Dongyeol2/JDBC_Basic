package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.scott_JDBCUtil;

public class Test05_delete {
	public static void main(String[] args) {
		String sql = "delete from book\r\n" + 
				" where bookno = ?"; //title, price는 변수로 설정
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0; //1이면 등록성공, 0이면 등록 실패
		
		try {
			con = scott_JDBCUtil.getConnection();
			ps = con.prepareStatement(sql); //sql구문을 미리 컴파일하라고 오라클한테 전달. st보다 ps가 성능 좋음
			//? 셋팅작업
			ps.setInt(1, 2);
			//실행
			row = ps.executeUpdate();
			//결과값 핸들링
			System.out.println("delete row : " + row);
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scott_JDBCUtil.close(con, ps, rs);
		}	
	}
}
