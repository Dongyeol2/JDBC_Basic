package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//emp 부서별 평균 급여와 인원수를 구해서 출력
public class Test02 {

	public static void main(String[] args) {
		System.out.println(" JDBC Test");
		String sql = "select e.deptno, dname, count(*) 인원수, round(avg(sal), 2) 평균\r\n" + 
				" from emp e, dept d\r\n" + 
				" where e.deptno = d.deptno\r\n" + 
				" group by e.deptno, dname\r\n" + 
				" order by 평균";
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "SCOTT";
		String pwd = "TIGER";
		
		Connection con = null; 
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver); 
			
			con = DriverManager.getConnection(url,user,pwd); 
			st = con.createStatement(); 
			rs = st.executeQuery(sql);
			
			while(rs.next()) { 
				System.out.print(rs.getString("deptno")+"  ");
				System.out.print(rs.getString("dname")+"  ");
				System.out.print(rs.getString("인원수")+"  ");
				System.out.print(rs.getString("평균")+"  ");
				System.out.println();
			}		
		
		}catch(ClassNotFoundException e) {
			System.out.println("jdbc driver 확인 요.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs != null)
					rs.close();	
				if(st != null)
					st.close();	
				if(con != null)
					con.close();	
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println("** end **");
	}

}
