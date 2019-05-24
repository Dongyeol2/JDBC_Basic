package prob3;

import java.util.List;

public class DeptApp {
	public static void main(String[] args) {
		DeptManager dm = new DeptManager();
		DeptService ds = new DeptServiceImpl(dm);
		
		if(ds != null) {
			List<Dept> list = ds.deptList();
			list.forEach(i -> System.out.println(i));
		}
	}
}
