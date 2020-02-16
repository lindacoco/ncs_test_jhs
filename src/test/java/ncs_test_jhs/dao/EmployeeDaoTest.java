package ncs_test_jhs.dao;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_jhs.dao.impl.EmployeeDaoImpl;
import ncs_test_jhs.dto.Department;
import ncs_test_jhs.dto.Employee;
import ncs_test_jhs.dto.Title;
import ncs_test_jhs.util.LogUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
    static EmployeeDao dao; 
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	    dao = EmployeeDaoImpl.getInstance();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	    dao = null;
	}

	@Test
	public void test01SelectEmployeeByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = dao.selectEmployeeByNo(new Employee(1));
		Assert.assertNotNull(emp);
		LogUtil.prnLog(emp);
	}

	@Test
	public void test02SelectEmployeeByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Employee> list =dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
		for(Employee e : list)LogUtil.prnLog(e);
	
	}

	@Test
	public void test03InsertEmp() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(6,"방송국",new Title(3),new Employee(2),3000000,new Department(1),0,new Date());
		LogUtil.prnLog(emp);
		int res = dao.insertEmp(emp);
		Assert.assertNotEquals(0, res);
	}

	@Test
	public void test04UpdateEmp() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(6,"유아",new Title(3),new Employee(2),5000000,new Department(1),0,new Date());
		LogUtil.prnLog(emp);
		int res = dao.updateEmp(emp);
		Assert.assertNotEquals(0, res);
	}

	@Test
	public void test05DeleteEmp() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = dao.deleteEmp(new Employee(6));
		Assert.assertNotEquals(0, res);
	}

}
