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
 * ��ע�⣬���������� ҵ�������淶�Ľӿڵľ���ʵ��
 * ���������� �������������� ������һϵ�о���Ķ���
 * ���Ƕ�ҵ���ӿڹ淶�ľ���ʵ��
 * 
 * ������������濴���� Dao���������ʵ��
 * ȫ�� ������
 * ȫ��������ͽӿڽ��в���
 * 
 * �Ժ� ���ֹ�����( ����˵ʵ���˾��幦�ܵ���)��ֻҪ�����ӽ���ˣ�ȫ��Ҫ�Ϲ���
 * @author 35.32
 *
 */
public class EmpServiceImpl implements IEmpService {
	//�õ�һ��DatabaseConnection
	
	private DatabaseConnection dbc = new DatabaseConnection();

	
	@Override
	public boolean insert(Emp emp) throws Exception {	
		try {
		
		
			if(DaoFactory.getIEmpDAOinstance(this.dbc.getConnection()).findById(emp.getEmpno()).getEmpno() == null) { //����������ظ� 
				//��Ҳ���������ʣ������ظ��򹤳�����ȡ����ôһ�� EmpDAOImpl �ǲ���ͬһ����
				//���ǿ϶��ģ���Ϊ�����ṩ�� DatabaseConnection �������ͬһ�������ص� ����϶�Ҳ��һ����
				
				//ҵ�����ɶ����µļ�¼�Ĵ���
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
