package Factory;

import Impl.EmpServiceImpl;
import Service.IEmpService;
/**
 *
 * ���๦���� ����һ�� ҵ���Ĺ�����
 *  ���������أ�����Ҫ����
 * ����㴦��ҵ��㣬 ֱ�ӵ��ø����get�����Ϳ���
 * @author 35.32
 *
 */
public class ServiceFactory {
	public static IEmpService getIEmpServiceInstance() {
		return new EmpServiceImpl();
	}
}
