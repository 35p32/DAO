package Test.junit;

import java.util.List;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import Factory.ServiceFactory;
import junit.framework.TestCase;
import vo.Emp;

public class IEmpServiceTest {
	private static int empno ;
	static {
		//调用Random类从而 随机生成一个小于10000的数
		empno = new Random().nextInt(10000);
	}
	@Test
	public void testInsert() {
		
		Emp emp = new Emp();
		emp.setEmpno(empno);
		emp.setEname("找到我");
		emp.setJob("待业");
		emp.setHiredate(new Date());
		emp.setSal(500.0);
		emp.setComm(20.2);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().insert(emp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		
		Emp vo = new Emp();
		vo.setEmpno(99011);
		vo.setEname("找到我");
		vo.setJob("待业更新后2");
		vo.setHiredate(new Date());
		vo.setSal(500.0);
		vo.setComm(20.2);
		try {
				TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().update(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Test
	public void testDelete() {	
		Set<Integer> ids = new HashSet<Integer>();
		ids.add(99011);
		try {
				TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().delete(ids));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Test
	public void testGet() {
		try {
			TestCase.assertNotNull(  ServiceFactory.getIEmpServiceInstance().get(99012) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testList() {
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().list().size()>0 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testListIntIntStringString() {
		try {
			Map<String,Object > map =  ServiceFactory.getIEmpServiceInstance().list(1,5,"ename","找到我");
			int count = (Integer) map.get("empCount");
			
			List<Emp> list =(List<Emp>) map.get("allEmps");
			
			TestCase.assertTrue(count>=0 && list.size()>=0);
			
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}

}
