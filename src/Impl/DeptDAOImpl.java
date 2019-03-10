package Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;

import Dao.IDeptDao;
import vo.Dept;
/**
 *  这是对于    IDeptDao  接口的实现
 * @author 35.32
 *
 *
 *	private Integer deptno;
	private String dname;
	private String loc
 *
 */
public class DeptDAOImpl implements IDeptDao {
	private Connection conn ;
	
	private PreparedStatement pstmt ;
	
	public DeptDAOImpl(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public boolean doCreate(Dept vo) throws Exception {
		String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1,vo.getDeptno());
		this.pstmt.setString(2, vo.getDname());
		this.pstmt.setString(3, vo.getLoc());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Dept vo) throws Exception {
		String sql = "update   dept set deptno = ? , dname = ? , loc = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1,vo.getDeptno());
		this.pstmt.setString(2, vo.getDname());
		this.pstmt.setString(3, vo.getLoc());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		if(ids.size() == 0 || ids == null) {
			return false; //不必删除
		}
		StringBuffer sql = new StringBuffer("Delete from dept where deptno in (");
		Iterator<Integer> iter = ids.iterator();
		
		while(iter.hasNext() ) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length()-1,sql.length()).append(")");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		return this.pstmt.executeUpdate()> 0; 
	}

	@Override
	public Dept findById(Integer id) throws Exception {
		Dept dept  = null;
		String sql = "select deptno,  dname , loc  from dept where deptno = ? ";
		this.pstmt =  this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			dept = new Dept();
			dept.setDeptno(rs.getInt(1));
			dept.setDname(rs.getString(2));
			dept.setLoc(rs.getString(3));
		}
		return dept;
	}

	@Override
	public List<Dept> findAll() throws Exception {
		String sql =  "select  deptno , dname , loc from dept";
		List<Dept> all = new ArrayList<Dept>();
		
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet  rs  = this.pstmt.executeQuery();
	
		Dept temp = null;
		while(rs.next()) {
			temp = new Dept();
			temp.setDeptno(rs.getInt(1));
			temp.setDname(rs.getString(2));
			temp.setLoc(rs.getString(3));
			all.add(temp); 
		}
		return all;
	}

	@Override
	public List<Dept> findAllSplit(Integer currentpage, Integer linesize, String column, String keywords) throws Exception {
		
		
		System.out.println("此方法未实现！");
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keywords) throws Exception {
		return null;
	}

}
