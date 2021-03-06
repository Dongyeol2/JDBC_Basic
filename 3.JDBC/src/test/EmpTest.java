package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JdbcUtil;

public class EmpTest {

	public static void main(String[] args) {
		String sql = "select count(*) as 직속부하직원수, e.manager_id as \"관리자 사번\", m.last_name as \"관리자 이름\" \r\n" + 
				" from employees e, employees m\r\n" + 
				" where e.manager_id = m.employee_id\r\n" + 
				" group by e.manager_id, m.last_name\r\n" + 
				" having count(*) >= 8\r\n" + 
				" order by 직속부하직원수";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql); //sql구문을 미리 컴파일하라고 오라클한테 전달. st보다 ps가 성능 좋음
			//? 셋팅작업
			
			//실행
			rs = ps.executeQuery();
			//결과값 핸들링
			while(rs.next()) { 
				System.out.print(rs.getInt("직속부하직원수")+"  ");
				System.out.print(rs.getString("관리자 사번")+"  ");
				System.out.print(rs.getString("관리자 이름")+"  ");
				System.out.println();
			}	
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con, ps, rs);
		}
	}
}