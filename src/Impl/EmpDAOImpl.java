package Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Dao.IEmpDAO;
import getCompleteSql.LoggableStatement;
import vo.Dept;
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
		String sql = "insert into emp(empno,ename,job,hiredate,sal,comm,mgr,deptno) values(?,?,?,?,?,?,?,?)";
		this.pstmt  = this.conn.prepareStatement(sql); 				//pstmt����һ��sql���
		//Ϊ�����������ֵ
		this.pstmt.setInt(1, vo.getEmpno());
		this.pstmt.setString(2, vo.getEname());
		this.pstmt.setString(3, vo.getJob());
		this.pstmt.setDate(4,new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(5, vo.getSal());
		this.pstmt.setDouble(6,vo.getComm());
		//���ݹ������ִ����Ӧ����
		
		if(vo.getMgr()==null) {
			this.pstmt.setNull(7, Types.NULL); //û�쵼�Ͳ�Ҫ�����쵼
		}else {
			this.pstmt.setInt(7, vo.getMgr().getEmpno());
		}
		if(vo.getDept()==null) {            //Ҫ�����һ����¼����û�в������ݼ�¼
			this.pstmt.setNull(8, Types.NULL); //û�쵼�Ͳ�Ҫ�����쵼
		}else {
			this.pstmt.setInt(8, vo.getDept().getDeptno());
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Emp vo) throws Exception {
		String sql = "update   emp  set ename = ? , job= ? , hiredate = ? ,sal= ?, comm= ? ,mgr = ? ,deptno = ?  where empno = ?";
		this.pstmt  = this.conn.prepareStatement(sql); 				//pstmt����һ��sql���
		//Ϊ�����������ֵ
		this.pstmt.setString(1, vo.getEname());
		this.pstmt.setString(2, vo.getJob());
		this.pstmt.setDate(3,new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(4, vo.getSal());
		this.pstmt.setDouble(5,vo.getComm());
		if(vo.getMgr()==null) {
			this.pstmt.setNull(6, Types.NULL); //û�쵼�Ͳ�Ҫ�����쵼
		}else {
			this.pstmt.setInt(6, vo.getMgr().getEmpno());
		}
		if(vo.getDept()==null) {            //Ҫ�����һ����¼����û�в������ݼ�¼
			this.pstmt.setNull(7, Types.NULL); //û�쵼�Ͳ�Ҫ�����쵼
		}else {
			this.pstmt.setInt(7, vo.getDept().getDeptno());
		}
		this.pstmt.setInt(8,vo.getEmpno());
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
	//���޸��� IEmpDao֮�󣬾�����˵���� ���������׼�ӿ� ����˼�������ķ���
	// ��������˲�䱨����ʾ��Ϣ��ʾ���һ�û�� implements �Ǽ����������ɼ����ܻ�
	@Override
	public Emp findByIdDetails(Integer id) throws Exception {
		String sql = " select  e.empno,e.ename,e.job,e.hiredate,e.sal,e.comm, m.empno , m.ename ,d.deptno, d.dname, d,loc"
				+ "from Emp e,emp m ,dept d where empno = ? and e.mgr=m.empno(+) and e.deptno = d.deptno";
		this.pstmt = this.conn.prepareStatement(sql); //sql������
		this.pstmt.setInt(1, id);
		System.out.println("ɱ����");
		ResultSet rs = this.pstmt.executeQuery();
		Emp emp  = new Emp();
		if(rs.next()) {
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5)); 
			emp.setComm(rs.getDouble(6));

			//�������������Ƶ�¼ӵķ����������� ��Ϊһ��Ա���ܹ�Ҫ�� �ϰ�͹����ص㣬���� IEmpDAO�����¼��� ���� �ⷽ�����ݵķ���
			//һ��Ա�����ϰ����ϵ
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			//һ��Ա���͹����ص����ϵ
			Dept d = new Dept();
			d.setDeptno(rs.getInt(9));
			d.setDname(rs.getString(10));
			d.setLoc(rs.getString(11));
			emp.setDept(d);
			
			
		}
		
		return emp;
	}

	@Override
	public List<Emp> findAllDetails() throws Exception {
		List<Emp> emplist = new ArrayList<Emp>(); //��Ŷ��Emp����
		
		String sql = "select e.empno,e.ename,e.job,e.hiredate,e.sal,e.comm ,m.empno,m.ename ,d.deptno,d.dname,d.loc"
				+ "from Emp e , Emp m, Dept d "
				+ "where e.mgr = m.empno(+) and e.deptno = d.deptno";
		
		this.pstmt = this.conn.prepareStatement(sql);//����sql���
		ResultSet  rs = this.pstmt.executeQuery();
		
		//System.out.println("ɱ����");
		while(rs.next()) { //���û��ɣ���������ʹ�õģ�  һ��һ�����ݶ��ǰ�˳�����кõģ����Դ����Ŷ�����Ϣ�Ķ���ֶ�
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));
			
			//�������������Ƶ�¼ӵķ����������� ��Ϊһ��Ա���ܹ�Ҫ�� �ϰ�͹����ص㣬���� IEmpDAO�����¼��� ���� �ⷽ�����ݵķ���
			//һ��Ա�����ϰ����ϵ
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			//һ��Ա���͹����ص����ϵ
			Dept d = new Dept();
			d.setDeptno(rs.getInt(9));
			d.setDname(rs.getString(10));
			d.setLoc(rs.getString(11));
			emp.setDept(d);
			
			 
			
			emplist.add(emp);
		}
		return emplist;
	}

	@Override
	public List<Emp> findAllSplitDetails(Integer currentPage, Integer lineSize, String column, String KeyWords) throws Exception{
		List<Emp> emplist = new ArrayList<Emp>(); //��Ŷ��Emp����
		String sql = "SELECT * FROM "
				+ " ( SELECT  e.empno,e.ename,e.job,e.hiredate,e.sal, "
				+ " e.comm,m.empno mno,m.ename mname,d.deptno,d.dname,d.loc,ROWNUM rn "
				+ " FROM  emp e, emp m, dept d"
				+ " WHERE e." + column + " LIKE ? AND ROWNUM <=?  AND  'e.mgr' =  'm.empno(+)' AND 'e.deptno' = 'd.deptno' ) t  WHERE t.rn>? ";
		
		this.pstmt = this.conn.prepareStatement(sql);//����sql���
		this.pstmt.setString(1,"%"+KeyWords+"%"); 
		this.pstmt.setInt(2, currentPage*lineSize);
		this.pstmt.setInt(3, (currentPage - 1)*lineSize);
		LoggableStatement ls = null;
		ls.wrappedStatement = pstmt;
		
		System.out.println( ls.getQueryString());
		
		
		ResultSet  rs = this.pstmt.executeQuery();
		while(rs.next()) { //һ��һ�����ݶ��ǰ�˳�����кõģ����Դ����Ŷ�����Ϣ�Ķ���ֶ�
			//System.out.println("���������ݵ�");
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));
			
			
			//�������������Ƶ�¼ӵķ����������� ��Ϊһ��Ա���ܹ�Ҫ�� �ϰ�͹����ص㣬���� IEmpDAO�����¼��� ���� �ⷽ�����ݵķ���
			//һ��Ա�����ϰ����ϵ
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			//һ��Ա���͹����ص����ϵ
			Dept d = new Dept();
			d.setDeptno(rs.getInt(9));
			d.setDname(rs.getString(10));
			d.setLoc(rs.getString(11));
			emp.setDept(d);
			
			
			emplist.add(emp);
		}
		return emplist;
	}

}
