package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class scott_JDBCUtil {
	public static Connection getConnection() {
//		String driver = "oracle.jdbc.OracleDriver";
//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//		String user = "SCOTT";
//		String pwd = "TIGER";

		Connection con = null;
		// Statement st = null;
		// ResultSet rs = null;

		try {
			Properties p = new Properties();
			p.load(new FileInputStream("c://lib/DBinfo.txt"));
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pw = p.getProperty("pw");
			String driver = p.getProperty("driver");

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
