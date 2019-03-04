package Factory;

import java.sql.Connection;

import Impl.EmpDAOImpl;

/**
 * 为了取得一个EmpDAOImpl 对象，
 *是为了返回一个接口对象
 *业务层在整个操作之中，获得了一个接口对象之后
 *就可以针对数据进行操作
 *我们只用从工厂里面拿出来一个 类，这个类有  具体实际的 针对表数据进行操作的方法
 *到这里就够了，Factory的功能就是这些啊
 *把针对EmpDAOImpl 的申请获得 包装起来，这就叫做包装
 *不管你的具体实现，我只用申请获得一个，然后使用你的功能 
 * 为什么呢，IEmpDAO 不就是个针对表操作规范的接口吗
 * @author 35.32
 *
 */
public class DaoFactory {
	public static EmpDAOImpl getIEmpDAOinstance(Connection conn) {
		return new EmpDAOImpl(conn);
	}
}
