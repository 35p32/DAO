package Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Factory.DaoFactory;
import Service.IEmpService;
import dbc.DatabaseConnection;
import vo.Emp;
/**
 * 
 * 请注意，这个类是针对 业务层操作规范的接口的具体实现
 * 我们在这里 才是真真正正地 声明了一系列具体的动作
 * 这是对业务层接口规范的具体实现
 * 
 * 我在这个类里面看不见 Dao具体的连接实现
 * 全靠 工厂类
 * 全靠工厂类和接口进行操作
 * 
 * 以后 这种功能类( 就是说实现了具体功能的类)，只要是连接借口了，全都要上工厂
 * @author 35.32
 *
 */
public class EmpServiceImpl implements IEmpService {
	//得到一个DatabaseConnection
	
	private DatabaseConnection dbc = new DatabaseConnection();

	
	@Override
	public boolean insert(Emp emp) throws Exception {	
		try {
		
		
			if(DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).findById(emp.getEmpno()).getEmpno() == null) { //如果主键不重复 
				//你也许会提出疑问，我们重复向工厂类索取的这么一个 EmpDAOImpl 是不是同一个类
				//答案是肯定的，因为给他提供的 DatabaseConnection 对象就是同一个，返回的 对象肯定也是一样的
				
				//业务层完成对于新的记录的创建
				return(DaoFactory.getIEmpDAOinstance(dbc.getConnection()).doCreate(emp));
			}
			return false;
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Emp emp) throws Exception {
		 try {
			return(DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).doUpdate(emp));
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
	    try {
			return (DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).doRemoveBatch(ids));
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public Emp get(Integer id) throws Exception {
		try {
			return DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).findById(id);
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Emp> list() throws Exception {
		try {
			return  DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).findAll();
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list(int currentPage, int linesize,  String column,String keywords)throws Exception{
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			
			map.put("allEmps", DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).findAllSplit(currentPage, linesize, column, keywords) );
			map.put("empCount", DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).getAllCount(column, keywords));
			return map;
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
	}

}
