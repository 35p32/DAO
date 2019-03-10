package Factory;

import Impl.DeptServiceImpl;
import Impl.EmpServiceImpl;
import Service.IDeptService;
import Service.IEmpService;
/**
 *
 * 本类功能是 返回一个 业务层的功能类
 *  无条件返回，不需要参数
 * 如果你处于业务层， 直接调用该类得get方法就可以
 * @author 35.32
 * 
 */
public class ServiceFactory {
	public static IEmpService getIEmpServiceInstance() {
	 	return new EmpServiceImpl(); 
	}  
	public static IDeptService getIDeptServiceInstance() {
		return new DeptServiceImpl();
	}
}
