package ncs_test_jhs.dao;

import java.util.List;

import ncs_test_jhs.dto.Department;

public interface DepartmentDao {
    Department selectDepartmentByNo(Department dept);
    List<Department> selectDepartmentByAll();
    
    int insertDepartment(Department dept);
    int updateDepartment(Department dept);
    int deleteDepartment(Department dept);
    
}
