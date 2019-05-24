package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.scott_JDBCUtil;

public class Test03 {
	public static void main(String[] args) {
		
		String sname = JOptionPane.showInputDialog("검색할 사원의 이름을 입력하세요 : ");
		
		Connection con = null;
		PreparedStatement ps = null; //st 대신 ps로 대체
		ResultSet rs = null;
		int row = 0;
		
		try {
			//String sql = "select * from emp where lower(ename) like '%'||?||'%'"; //1
			String sql = "select * from emp where lower(ename) like ?"; //2
			con = scott_JDBCUtil.getConnection();
			ps = con.prepareStatement(sql); //sql구문을 미리 컴파일하라고 오라클한테 전달. st보다 ps가 성능 좋음
			//? 셋팅작업
			//ps.setString(1, sname.toLowerCase()); //1
			ps.setString(1, "%"+sname.toLowerCase()+"%"); //2
			
			//실행
			rs = ps.executeQuery();
			
			//결과값 핸들링
			while(rs.next()) {
				System.out.print(rs.getString("ename")+"  ");
				System.out.print(rs.getString("deptno")+"  ");
				System.out.print(rs.getString("job")+"  ");
				System.out.print(rs.getDate("hiredate")+"  ");
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
