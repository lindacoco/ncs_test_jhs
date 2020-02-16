package ncs_test_jhs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_test_jhs.dao.DepartmentDao;
import ncs_test_jhs.ds.MySqlDataSource;
import ncs_test_jhs.dto.Department;
import ncs_test_jhs.util.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	
	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	private String sql =null;

     public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Department selectDepartmentByNo(Department dept) {
		sql ="select deptno, deptname, floor from department where deptno =?";
		try(Connection con= MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept.getDeptNo());
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		sql="select deptno, deptname, floor from department";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			
			List<Department> list = new ArrayList<Department>();
			while(rs.next()){
				list.add(getDepartment(rs));
			}
		   return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public int insertDepartment(Department dept) {
		sql ="insert into department values (?,?,?)";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setInt(3, dept.getFloor());
		
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department dept) {
		sql="update department set deptname =? ,floor= ? where deptno =?";
	  try(Connection con = MySqlDataSource.getConnection();
			  PreparedStatement pstmt = con.prepareStatement(sql)){
		  
		  pstmt.setString(1, dept.getDeptName());
		  pstmt.setInt(2, dept.getFloor());
		  pstmt.setInt(3, dept.getDeptNo());
		  return pstmt.executeUpdate();
		  
	  } catch (SQLException e) {
		e.printStackTrace();
	}
		return 0;
	}

	@Override
	public int deleteDepartment(Department dept) {
		sql = "delete from department where deptno =?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, dept.getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
