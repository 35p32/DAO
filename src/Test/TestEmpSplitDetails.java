package Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


import vo.Emp;
import Factory.ServiceFactory;

public class TestEmpSplitDetails {
	public static void main(String[] args) {
		//�ĸ�������  �ڼ�ҳ��һҳ������¼�� ��ʲô���ң��ؼ���
		try {
			Map<String,Object > map =  ServiceFactory.getIEmpServiceInstance().listDetails(1,5,"ename","��");
			@SuppressWarnings("unused")
			int count = (Integer) map.get("empCount");
			System.out.println("�������ǣ�"+count );
			@SuppressWarnings({ "unused", "unchecked" })
			List<Emp> list = (List<Emp>) map.get("allEmps");
			Iterator <Emp> iter = list.iterator();
			Emp emp = new Emp();
			while(iter.hasNext()) {
				emp= iter.next();
				System.out.println(emp.getEname() + ','+emp.getJob()+"�쵼�����ǣ�"+emp.getMgr().getEname()+"���������ǣ�"+emp.getDept().getDeptno());
			}
			if(!iter.hasNext()) {
				System.out.println("û���ҵ����ݰ��ؼ���");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
