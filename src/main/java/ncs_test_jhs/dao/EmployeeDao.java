package ncs_test_jhs.dao;

import java.util.List;

import ncs_test_jhs.dto.Employee;



public interface EmployeeDao {
	Employee selectEmployeeByNo(Employee emp);
    List<Employee> selectEmployeeByAll();
    List<Employee> selectEmployeeByAll222();
    int insertEmp(Employee emp);
    int updateEmp(Employee emp);
    int deleteEmp(Employee emp);
    
}
