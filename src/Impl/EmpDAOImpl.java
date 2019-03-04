package Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Dao.IEmpDAO;
import vo.Emp;
/**
 * �����Ƕ��ڽӿڵ�ʵ����������Ҫ�ְ��ֵ� �������ݵĴ���
 * �ӱ�������ƿ��Կ����������Ƕ��� IEMPDAO ������׼��  implements ��ʵ�֣�
 * ��������û��㣬�������ݿ�򽻵�
 * 
 * 
 * @author 35.32
 *
 */
public class EmpDAOImpl implements IEmpDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	//������췽����Ҫ�����ⲿ����һ��ͨ�����ݿ�����ӣ��������Ҳ��ܴ�������
	public EmpDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Emp vo) throws Exception {
		String sql = "insert into emp(empno,ename,job,hiredate,sal,comm) values(?,?,?,?,?,?)";
		this.pstmt  = this.conn.prepareStatement(sql); 				//pstmt����һ��sql���
		//Ϊ�����������ֵ
		this.pstmt.setInt(1, vo.getEmpno());
		this.pstmt.setString(2, vo.getEname());
		this.pstmt.setString(3, vo.getJob());
		this.pstmt.setDate(4,new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(5, vo.getSal());
		this.pstmt.setDouble(6,vo.getComm());
		//���ݹ������ִ����Ӧ����
		
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Emp vo) throws Exception {
		String sql = "update   emp  set ename = ? , job= ? , hiredate = ? ,sal= ?, comm= ?  where empno = ? ";
		this.pstmt  = this.conn.prepareStatement(sql); 				//pstmt����һ��sql���
		//Ϊ�����������ֵ
		this.pstmt.setString(1, vo.getEname());
		this.pstmt.setString(2, vo.getJob());
		this.pstmt.setDate(3,new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(4, vo.getSal());
		this.pstmt.setDouble(5,vo.getComm());
		this.pstmt.setInt(6,vo.getEmpno());
		//���ݹ������ִ����Ӧ����
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		if(ids == null || ids.size() == 0) { //û��Ҫɾ��������
			return false;
		}
		StringBuffer sql = 	new StringBuffer();
		//��ע������������Ҫ����sets �����ids 
		//��ɾ��������¼
		//ֻҪ����� id �� ids ���棬��ô��ɾ����һ����¼
		sql.append("DELETE FROM EMP WHERE EMPNO IN (");
		
		
		//�����������ȡ���������һ��������sql���
		Iterator<Integer> iter =  ids.iterator();
		while(iter.hasNext()) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length()-1,sql.length()).append(")");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		
		return  pstmt.executeUpdate() == ids.size();
	}

	@Override
	public Emp findById(Integer id) throws Exception {
		String sql = "select  ename,job,hiredate,sal,comm from Emp where empno = ?";
		this.pstmt = this.conn.prepareStatement(sql); //sql������
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		Emp emp  = new Emp();
		if(rs.next()) {
			emp.setEname(rs.getString(1));
			emp.setJob(rs.getString(2));
			emp.setHiredate(rs.getDate(3));
			emp.setSal(rs.getDouble(4)); 
			emp.setComm(rs.getDouble(5));
		}
		
		return emp;
	}

	@Override
	public List<Emp> findAll() throws Exception {
		List<Emp> emplist = new ArrayList<Emp>(); //��Ŷ��Emp����
		
		String sql = "select empno,ename,job,hiredate,sal,comm from Emp ";
		this.pstmt = this.conn.prepareStatement(sql);//����sql���
		ResultSet  rs = this.pstmt.executeQuery();
		while(rs.next()) { //���û��ɣ���������ʹ�õģ�  һ��һ�����ݶ��ǰ�˳�����кõģ����Դ����Ŷ�����Ϣ�Ķ���ֶ�
			Emp emp = new Emp();
			emp.setEname(rs.getString(1));
			emp.setJob(rs.getString(2));
			emp.setHiredate(rs.getDate(3));
			emp.setSal(rs.getDouble(4));
			emp.setComm(rs.getDouble(5));
			emplist.add(emp);
		}
		return emplist;
	}

	@Override
	public List<Emp> findAllSplit(Integer currentpage, Integer linesize, String column, String keywords) throws Exception {
		List<Emp> emplist = new ArrayList<Emp>(); //��Ŷ��Emp����
		String sql = "select * from (select empno,ename,job,hiredate,sal,comm,rownum rn "
				+ " from  Emp "
				+ " where "+ column +" like ? and rownum <=?) temp "
				+ " where temp.rn >? ";
		this.pstmt = this.conn.prepareStatement(sql);//����sql���
		
		this.pstmt.setString(1,"%"+keywords+"%");
		this.pstmt.setInt(2, currentpage*linesize);
		this.pstmt.setInt(3, (currentpage-1)*(linesize ));

		ResultSet  rs = this.pstmt.executeQuery();
		while(rs.next()) { //���û��ɣ���������ʹ�õģ�  һ��һ�����ݶ��ǰ�˳�����кõģ����Դ����Ŷ�����Ϣ�Ķ���ֶ�
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));
			emplist.add(emp);
		}
		return emplist;
	}

	@Override

	public Integer getAllCount(String column, String keywords) throws Exception {
		String sql = "select count(empno) from emp where "+ column +" like ?";
		this.pstmt =  this.conn.prepareStatement(sql);
		
		this.pstmt.setString(1,"% "+keywords+" %");
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0 ;
	}

}
