package prob3;

import java.util.List;

public class DeptServiceImpl implements DeptService{
	private DeptManager dm = null;
	
	public DeptServiceImpl() {
		super();
	}

	public DeptServiceImpl(DeptManager dm) {
		super();
		this.dm = dm;
	}

	public DeptManager getDm() {
		return dm;
	}

	public void setDm(DeptManager dm) {
		this.dm = dm;
	}

	@Override
	public List<Dept> deptList() {
		return dm.deptList();
	}
}
