package Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


import vo.Emp;
import Factory.ServiceFactory;

public class EmpQueryTest {
	public static void main(String[] args) {
		//四个参数是  第几页，一页几条记录，按什么查找，关键词
		try {
			Map<String,Object > map =  ServiceFactory.getIEmpServiceInstance().list(1,5,"ename","%找%");
			@SuppressWarnings("unused")
			int count = (Integer) map.get("empCount");
			System.out.println("数据量是："+count );
		
			@SuppressWarnings({ "unused", "unchecked" })
			List<Emp> list = (List<Emp>) map.get("allEmps");
			Iterator <Emp> iter = list.iterator();
			Emp emp = new Emp();
			while(iter.hasNext()) {
				emp= iter.next();
				System.out.println(emp.getEname() + emp.getJob());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
