package ncs_test_jhs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ncs_test_jhs.dao.EmployeeDao;
import ncs_test_jhs.ds.MySqlDataSource;
import ncs_test_jhs.dto.Department;
import ncs_test_jhs.dto.Employee;
import ncs_test_jhs.dto.Title;
import ncs_test_jhs.util.LogUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	
	
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();
	
	

	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql="select empno,empname, title, manager, salary, dno from employee where empno=?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getEmployee(rs);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql="select empno,empname, title, manager, salary , dno from employee";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			List<Employee> list = new ArrayList<>();
			while(rs.next()) {
				 list.add(getEmployee(rs));
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = new Title(rs.getInt("title"));
		Employee manager = new Employee(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dno = new Department(rs.getInt("dno"));
		return new Employee(empNo, empName, title, manager, salary, dno);
	}

	@Override
	public int insertEmp(Employee emp) {
		String sql ="insert into employee(empno,empname,title,manager,salary,dno,gender,hiredate) values (?,?,?,?,?,?,?,?)";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1,emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setInt(3, emp.getTitle().getTitleNo());
			pstmt.setInt(4, emp.getManager().getEmpNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDno().getDeptNo());
			pstmt.setInt(7,emp.getGender());
			pstmt.setTimestamp(8, new Timestamp(emp.getHireDate().getTime()));
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateEmp(Employee emp) {
		String sql="update employee set empname=?,title=?, manager=?, salary=?,dno=?, gender =?, hiredate =? where empno=?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getTitle().getTitleNo());
			pstmt.setInt(3, emp.getManager().getEmpNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDno().getDeptNo());
			pstmt.setInt(6, emp.getGender());
			pstmt.setTimestamp(7, new Timestamp(emp.getHireDate().getTime()));
			pstmt.setInt(8, emp.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return 0;
	}

	@Override
	public int deleteEmp(Employee emp) {
		String sql= "delete from employee where empno=?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
		    return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Employee> selectEmployeeByAll222() {
		String sql="select e.empno, e.empname,t.titleno, t.titlename , e.salary, e.gender, d.deptname, d.deptno, d.floor, e.hiredate\r\n" + 
				"  from employee e left join title t on e.dno = t.titleno left join department d on e.dno =d.deptno order by e.empno";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			List<Employee> list = new ArrayList<>();
			while(rs.next()) {
				 list.add(getEmployee222(rs));
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee222(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("e.empno");
		String empName = rs.getString("e.empname");
		Title title = new Title(rs.getInt("t.titleno"));
		title.setTitleName(rs.getString("t.titlename"));
		int salary = rs.getInt("e.salary");
		Department dno = new Department(rs.getInt("d.deptno"));
		dno.setDeptName(rs.getString("d.deptname"));
		dno.setFloor(rs.getInt("d.floor"));
		int gender = rs.getInt("e.gender");
		Date hireDate = rs.getTimestamp("e.hiredate");
		return new Employee(empNo, empName, title, salary, dno, gender, hireDate);
	}



}
