package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test01 {
	public static void main(String[] args) {
		System.out.println(" JDBC Test");
		String sql = "select * from dept";
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "SCOTT";
		String pwd = "TIGER";
		
		Connection con = null; //DB와의 연결 객체
		Statement st = null; //sql구문 관리 객체
		ResultSet rs = null; //결과처리 하기 위한 객체
		
		try { //로딩된 Driver 클래스 이용해서 connection 요청
			Class.forName(driver); 
			//con, st, rs는 모두 연결지향형 데이터이기 때문에 자원반납 필수적.
			con = DriverManager.getConnection(url,user,pwd); //연결하기위한 con 객체 생성(url, user, pwd)전달
			st = con.createStatement(); //연결한 con 객체의 sql 구문 관리할 st객체 생성
			rs = st.executeQuery(sql); //st객체에서 sql문 가져와서 쿼리문 실행
			
			while(rs.next()) { //결과값 처리
//				System.out.print(rs.getString(1)+"  "); //컬럼인덱스로 값 뽑아냄
//				System.out.print(rs.getString(2)+"  ");
//				System.out.print(rs.getString(3)+"  ");
				System.out.print(rs.getString("deptno")+"  "); //컬럼 이름으로 값 뽑아냄
				System.out.print(rs.getString("dname")+"  ");
				System.out.print(rs.getString("loc")+"  ");
				//System.out.println(rs.getMetaData());
				//System.out.println(con.getMetaData()); //내가 연결하고 있는 DB의 메타데이터 뽑아냄
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
