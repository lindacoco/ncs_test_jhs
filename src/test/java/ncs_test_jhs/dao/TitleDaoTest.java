package ncs_test_jhs.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_jhs.dao.impl.TitleDaoImpl;
import ncs_test_jhs.dto.Title;
import ncs_test_jhs.util.LogUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = TitleDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao =null;
	}

	
	
	@Test
	public void test01SelectTitleByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = dao.selectTitleByNo(new Title(1));
		Title resTitle = new Title(1, "대표이사");
		Assert.assertEquals(resTitle, title);
		
		LogUtil.prnLog(title);
	}

	@Test
	public void test02SelectTitleByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Title> list = dao.selectTitleByAll();
		Assert.assertNotNull(list);
		
		for(Title t : list) LogUtil.prnLog(t);
	}

	@Test
	public void test03InsertTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title= new Title(5, "낙하산");
		int res = dao.insertTitle(title);
		Assert.assertNotEquals(0,res);
		LogUtil.prnLog(title);
	}

	@Test
	public void test04UpdateTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = new Title(5,"음");
		int res = dao.updateTitle(title);
		Assert.assertNotEquals(0, res);
		LogUtil.prnLog(title);
		

	}

	@Test
	public void test05DeleteTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = new Title(5);
		int res = dao.deleteTitle(title);
		Assert.assertNotEquals(0, res);
		LogUtil.prnLog(title);

	}

}
