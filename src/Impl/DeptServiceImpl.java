package Impl;
/**
 * 真正的功能服务类实例化对象
 */
import java.util.List;
import java.util.Set;

import Factory.DaoFactory;
import Service.IDeptService;
import dbc.DatabaseConnection;
import vo.Dept;

public class DeptServiceImpl implements IDeptService {
	private DatabaseConnection dbc = new DatabaseConnection();

	
	@Override
	public boolean insert(Dept dept) throws Exception {
	try {
		if(DaoFactory.getIDeptDAOinstance(this.dbc.getConnection()).findById(dept.getDeptno()).getDeptno() == null) {
			return DaoFactory.getIDeptDAOinstance(this.dbc.getConnection()).doCreate(dept);
		}
	} catch (Exception e) {
		throw e;
	}finally {	
		this.dbc.close();
	}
	return false;
}

	@Override
	public boolean update(Dept dept) throws Exception {
		try {
			return DaoFactory.getIDeptDAOinstance(this.dbc.getConnection()).doUpdate(dept);
			
		} catch (Exception e) {
			throw e;
		}finally {	
			this.dbc.close();
		}
}
	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DaoFactory.getIDeptDAOinstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
}

	@Override
	public Dept get(Integer id) throws Exception {
		try { 
			
			return DaoFactory.getIDeptDAOinstance(this.dbc.getConnection()).findById(id);
		} catch (Exception e) {
			throw e ;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Dept> list() throws Exception {
		try {
			return DaoFactory.getIDeptDAOinstance(this.dbc.getConnection()).findAll();
		} catch (Exception e) {
			throw e ;
		}finally {	
			this.dbc.close();
		}
	}
}
