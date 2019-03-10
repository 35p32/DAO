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
 * 本类是对于接口的实例化，本类要手把手地 进行数据的处理
 * 从本类的名称可以看出，本类是对于 IEMPDAO 操作标准的  implements （实现）
 * 本类介于用户层，不和数据库打交道
 * 
 * 
 * @author 35.32
 *
 */
public class EmpDAOImpl implements IEmpDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	//这个构造方法，要求你外部给我一个通往数据库的连接，这样，我才能处理数据
	public EmpDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Emp vo) throws Exception {
		String sql = "insert into emp(empno,ename,job,hiredate,sal,comm,mgr,deptno) values(?,?,?,?,?,?,?,?)";
		this.pstmt  = this.conn.prepareStatement(sql); 				//pstmt挂载一个sql语句
		//为挂载语句设置值
		this.pstmt.setInt(1, vo.getEmpno());
		this.pstmt.setString(2, vo.getEname());
		this.pstmt.setString(3, vo.getJob());
		this.pstmt.setDate(4,new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(5, vo.getSal());
		this.pstmt.setDouble(6,vo.getComm());
		//根据挂载语句执行相应操作
		
		if(vo.getMgr()==null) {
			this.pstmt.setNull(7, Types.NULL); //没领导就不要设置领导
		}else {
			this.pstmt.setInt(7, vo.getMgr().getEmpno());
		}
		if(vo.getDept()==null) {            //要插入的一条记录里面没有部门数据记录
			this.pstmt.setNull(8, Types.NULL); //没领导就不要设置领导
		}else {
			this.pstmt.setInt(8, vo.getDept().getDeptno());
		}
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Emp vo) throws Exception {
		String sql = "update   emp  set ename = ? , job= ? , hiredate = ? ,sal= ?, comm= ? ,mgr = ? ,deptno = ?  where empno = ?";
		this.pstmt  = this.conn.prepareStatement(sql); 				//pstmt挂载一个sql语句
		//为挂载语句设置值
		this.pstmt.setString(1, vo.getEname());
		this.pstmt.setString(2, vo.getJob());
		this.pstmt.setDate(3,new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(4, vo.getSal());
		this.pstmt.setDouble(5,vo.getComm());
		if(vo.getMgr()==null) {
			this.pstmt.setNull(6, Types.NULL); //没领导就不要设置领导
		}else {
			this.pstmt.setInt(6, vo.getMgr().getEmpno());
		}
		if(vo.getDept()==null) {            //要插入的一条记录里面没有部门数据记录
			this.pstmt.setNull(7, Types.NULL); //没领导就不要设置领导
		}else {
			this.pstmt.setInt(7, vo.getDept().getDeptno());
		}
		this.pstmt.setInt(8,vo.getEmpno());
		//根据挂载语句执行相应操作
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		if(ids == null || ids.size() == 0) { //没有要删除的内容
			return false;
		}
		StringBuffer sql = 	new StringBuffer();
		//请注意我们现在是要根据sets 里面的ids 
		//来删除多条记录
		//只要你的码 id 在 ids 里面，那么就删除这一条记录
		sql.append("DELETE FROM EMP WHERE EMPNO IN (");
		
		
		//把所有序号提取出来构造成一个完整的sql语句
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
		this.pstmt = this.conn.prepareStatement(sql); //sql语句挂载
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
		List<Emp> emplist = new ArrayList<Emp>(); //存放多个Emp对象
		
		String sql = "select empno,ename,job,hiredate,sal,comm from Emp ";
		this.pstmt = this.conn.prepareStatement(sql);//挂载sql语句
		ResultSet  rs = this.pstmt.executeQuery();
		while(rs.next()) { //不用怀疑，就是这样使用的，  一个一个数据都是按顺序排列好的，所以储存着多条信息的多个字段
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
		List<Emp> emplist = new ArrayList<Emp>(); //存放多个Emp对象
		String sql = "select * from (select empno,ename,job,hiredate,sal,comm,rownum rn "
				+ " from  Emp "
				+ " where "+ column +" like ? and rownum <=?) temp "
				+ " where temp.rn >? ";
		this.pstmt = this.conn.prepareStatement(sql);//挂载sql语句
		
		this.pstmt.setString(1,"%"+keywords+"%");
		this.pstmt.setInt(2, currentpage*linesize);
		this.pstmt.setInt(3, (currentpage-1)*(linesize ));
		ResultSet  rs = this.pstmt.executeQuery();
		while(rs.next()) { //不用怀疑，就是这样使用的，  一个一个数据都是按顺序排列好的，所以储存着多条信息的多个字段
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
	//在修改了 IEmpDao之后，具体来说就是 我向操作标准接口 添加了几个额外的方法
	// 于是这里瞬间报错，提示信息显示，我还没有 implements 那几个方法，可见智能化
	@Override
	public Emp findByIdDetails(Integer id) throws Exception {
		String sql = " select  e.empno,e.ename,e.job,e.hiredate,e.sal,e.comm, m.empno , m.ename ,d.deptno, d.dname, d,loc"
				+ "from Emp e,emp m ,dept d where empno = ? and e.mgr=m.empno(+) and e.deptno = d.deptno";
		this.pstmt = this.conn.prepareStatement(sql); //sql语句挂载
		this.pstmt.setInt(1, id);
		System.out.println("杀了我");
		ResultSet rs = this.pstmt.executeQuery();
		Emp emp  = new Emp();
		if(rs.next()) {
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5)); 
			emp.setComm(rs.getDouble(6));

			//这是最后两章视频新加的方法，初衷是 因为一个员工总归要有 老板和工作地点，所以 IEmpDAO里面新加了 关于 这方面内容的方法
			//一个员工和老板的联系
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			//一个员工和工作地点的联系
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
		List<Emp> emplist = new ArrayList<Emp>(); //存放多个Emp对象
		
		String sql = "select e.empno,e.ename,e.job,e.hiredate,e.sal,e.comm ,m.empno,m.ename ,d.deptno,d.dname,d.loc"
				+ "from Emp e , Emp m, Dept d "
				+ "where e.mgr = m.empno(+) and e.deptno = d.deptno";
		
		this.pstmt = this.conn.prepareStatement(sql);//挂载sql语句
		ResultSet  rs = this.pstmt.executeQuery();
		
		//System.out.println("杀了我");
		while(rs.next()) { //不用怀疑，就是这样使用的，  一个一个数据都是按顺序排列好的，所以储存着多条信息的多个字段
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));
			
			//这是最后两章视频新加的方法，初衷是 因为一个员工总归要有 老板和工作地点，所以 IEmpDAO里面新加了 关于 这方面内容的方法
			//一个员工和老板的联系
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			//一个员工和工作地点的联系
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
		List<Emp> emplist = new ArrayList<Emp>(); //存放多个Emp对象
		String sql = "SELECT * FROM "
				+ " ( SELECT  e.empno,e.ename,e.job,e.hiredate,e.sal, "
				+ " e.comm,m.empno mno,m.ename mname,d.deptno,d.dname,d.loc,ROWNUM rn "
				+ " FROM  emp e, emp m, dept d"
				+ " WHERE e." + column + " LIKE ? AND ROWNUM <=?  AND  'e.mgr' =  'm.empno(+)' AND 'e.deptno' = 'd.deptno' ) t  WHERE t.rn>? ";
		
		this.pstmt = this.conn.prepareStatement(sql);//挂载sql语句
		this.pstmt.setString(1,"%"+KeyWords+"%"); 
		this.pstmt.setInt(2, currentPage*lineSize);
		this.pstmt.setInt(3, (currentPage - 1)*lineSize);
		LoggableStatement ls = null;
		ls.wrappedStatement = pstmt;
		
		System.out.println( ls.getQueryString());
		
		
		ResultSet  rs = this.pstmt.executeQuery();
		while(rs.next()) { //一个一个数据都是按顺序排列好的，所以储存着多条信息的多个字段
			//System.out.println("还是有数据的");
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));
			
			
			//这是最后两章视频新加的方法，初衷是 因为一个员工总归要有 老板和工作地点，所以 IEmpDAO里面新加了 关于 这方面内容的方法
			//一个员工和老板的联系
			Emp mgr = new Emp();
			mgr.setEmpno(rs.getInt(7));
			mgr.setEname(rs.getString(8));
			emp.setMgr(mgr);
			
			//一个员工和工作地点的联系
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
