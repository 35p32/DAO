package Test;

import java.util.Date;

import Factory.ServiceFactory;
import vo.Emp;

public class EmpInsertTest {
	public static void main(String[] args) {
		Emp emp = new Emp();
		emp.setEmpno(99012);
		emp.setEname("’“µΩŒ“");
		emp.setJob("¥˝“µ");
		emp.setHiredate(new Date());
		emp.setSal(500.0);
		emp.setComm(20.2);
		try {
			System.out.println(ServiceFactory.getIEmpServiceInstance().insert(emp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
