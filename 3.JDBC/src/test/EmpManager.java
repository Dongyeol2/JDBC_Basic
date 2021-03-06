package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.JdbcUtil;

public class EmpManager {
	public static void main(String[] args) {
		String jname1 = JOptionPane.showInputDialog("검색할 첫번째 부서의 이름을 입력하세요 : ");
		String jname2 = JOptionPane.showInputDialog("검색할 두번째 부서의 이름을 입력하세요 : ");
		String[] jobs = {jname1, jname2};
		printEmployee(jobs);
		
	}
	public static void printEmployee(String jobs[]) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0; //1이면 등록성공, 0이면 등록 실패
		
		try {
			String sql = "select e.employee_id 사번, e.first_name 이름, e.salary 연봉\r\n" + 
					" from employees e, jobs s\r\n" + 
					" where e.job_id = s.job_id and lower(s.job_title) like ? or lower(s.job_title) like ?";
			
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			//? 셋팅작업
			System.out.println(1);
			ps.setString(1, "%"+jobs[0].toLowerCase()+"%");
			ps.setString(2, "%"+jobs[1].toLowerCase()+"%");
			System.out.println(2);
			//실행
			rs = ps.executeQuery();
			System.out.println(3);
			//결과값 핸들링
			while(rs.next()) { 
				System.out.print(rs.getInt("사번")+"  ");
				System.out.print(rs.getString("이름")+"  ");
				System.out.print(rs.getInt("연봉")+"  ");
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
