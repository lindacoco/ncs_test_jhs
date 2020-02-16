package ncs_test_jhs.ui.service;

import java.util.List;

import ncs_test_jhs.dao.DepartmentDao;
import ncs_test_jhs.dao.impl.DepartmentDaoImpl;
import ncs_test_jhs.dto.Department;
//데이터 베이스와 연결하는 서비스 
public class DepartmentUiService {
    private DepartmentDao deptDao;

	public DepartmentUiService() {
		deptDao = DepartmentDaoImpl.getInstance();
	}
	
	//목록 전부다 불러오기 
	public List<Department> showDepartmentList(){
		return deptDao.selectDepartmentByAll();
	}
    //제거하기
	public void removeDepartment(Department dept) {
		deptDao.deleteDepartment(dept);
	}
	//수정하기
	public void modifyDepartment(Department dept) {
		deptDao.updateDepartment(dept);
	}
	//추가하기
    public void addUpDepartment(Department dept) {
    	deptDao.insertDepartment(dept);
    }
    public int setFirstNum() {
    	return 0;
    }
   
   
}
