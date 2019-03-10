package vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")

public class Dept implements Serializable {
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}  
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	public List<Emp> getEmps() {
		return emps;
	}
	private Integer deptno;
	private String dname;
	private String loc;
	private List<Emp> emps ;
}
