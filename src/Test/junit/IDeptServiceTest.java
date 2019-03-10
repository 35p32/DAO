package Test.junit;

import static org.junit.Assert.*;

import java.security.Provider.Service;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import Factory.ServiceFactory;
import junit.framework.TestCase;
import vo.Dept;

public class IDeptServiceTest {

	@Test
	public void testInsert() {
		Dept dept = new Dept();
		dept.setDeptno(23);
		dept.setDname("痛苦来自遗忘痛苦");
		dept.setLoc("委内瑞拉");
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().insert(dept));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}

	@Test
	public void testUpdate() {
		Dept dept = new Dept();
		dept.setDeptno(23);
		dept.setDname("我你");
		dept.setLoc("委内瑞拉");
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().update(dept));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		Set<Integer> ids = new HashSet<Integer>();
		try {
			ids.add(23);
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().delete(ids));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGet() {
		int id  = 23;
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().get(id) != null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testList() {
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().list().size() > 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
