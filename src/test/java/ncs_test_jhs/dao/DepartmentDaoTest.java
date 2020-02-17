package ncs_test_jhs.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_jhs.dao.impl.DepartmentDaoImpl;
import ncs_test_jhs.dto.Department;
import ncs_test_jhs.util.LogUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	    dao = DepartmentDaoImpl.getInstance();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao =null;
	}

	@Test
	public void test01SelectDepartmentByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dept = dao.selectDepartmentByNo(new Department(1));
		Department resDept = new Department(1, "영업", 8);
		Assert.assertEquals(resDept, dept);
		LogUtil.prnLog(dept);
	}

	@Test
	public void test02SelectDepartmentByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Department> dept = dao.selectDepartmentByAll();
		Assert.assertNotNull(dept);
		for(Department d : dept) LogUtil.prnLog(d);
	}

	@Test
	public void test03InsertDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dept = new Department(4,"꿀보",11);
		int res = dao.insertDepartment(dept);
		Assert.assertNotEquals(0,res);
		LogUtil.prnLog(dept);
	}

	@Test
	public void test04UpdateDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		int res = dao.updateDepartment(new Department(4,"꿀꿀",8));
		Assert.assertEquals(1, res);
	
	}

	@Test
	public void test05DeleteDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = dao.deleteDepartment(new Department(4));
		Assert.assertNotEquals(0, res);
		
	}

}
