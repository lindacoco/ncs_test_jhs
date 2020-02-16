package ncs_test_jhs.ui.service;

import java.util.List;

import ncs_test_jhs.dao.DepartmentDao;
import ncs_test_jhs.dao.EmployeeDao;
import ncs_test_jhs.dao.TitleDao;
import ncs_test_jhs.dao.impl.DepartmentDaoImpl;
import ncs_test_jhs.dao.impl.EmployeeDaoImpl;
import ncs_test_jhs.dao.impl.TitleDaoImpl;
import ncs_test_jhs.dto.Department;
import ncs_test_jhs.dto.Employee;
import ncs_test_jhs.dto.Title;
//데이터 베이스와 연결하는 서비스 
public class EmployeeUiService {
    private EmployeeDao empDao;
    private TitleDao titleDao;
    private DepartmentDao deptDao;

	public EmployeeUiService() {
		empDao = EmployeeDaoImpl.getInstance();
		titleDao = TitleDaoImpl.getInstance();
		deptDao = DepartmentDaoImpl.getInstance();
	}
	
	//목록 전부다 불러오기 
	public List<Employee> showEmployeeList(){
		return empDao.selectEmployeeByAll();
	}
	
	public List<Employee> showEmployeeList222(){
		return empDao.selectEmployeeByAll222();
	}
    //제거하기
	public void removeEmployee(Employee emp) {
		empDao.deleteEmp(emp);
	}
	//수정하기
	public void modifyEmployee(Employee emp) {
		empDao.updateEmp(emp);
	}
	//추가하기
    public void addUpEmployee(Employee emp) {
    	empDao.insertEmp(emp);
    }
    public int setFirstNum() {
    	return 0;
    }
    
    //cmb부분에 데이터 불러오기
    public List<Title> showTitleList(){
    	return titleDao.selectTitleByAll();
    }
    public List<Department> showDepartmentList(){
    	return deptDao.selectDepartmentByAll();
    }

	
    
   
   
}
