package prob3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;

public class DeptManager {
	public List<Dept> deptList() {
		List<Dept> list = new ArrayList<>();
		String sql = "select * from dept";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Dept dept = new Dept();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));

				list.add(dept);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, ps, rs);
		}
		return list;
	}

}
