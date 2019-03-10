package vo; 	

import java.io.Serializable;
import java.util.Date;
//基本的表

@SuppressWarnings("serial")
public class Emp implements Serializable{
	private Integer empno;
	private String ename;
	private String job;
	private Date hiredate;	
	private Double sal;
	private Double comm;
	
	private Emp mgr;     
	private Dept dept;  //雇员和领导关系


	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Dept getDept() {
		return dept;
	}
	public void setMgr(Emp mgr) {
		this.mgr = mgr;
	}
	public Emp getMgr() {
		return mgr;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public Integer getEmpno() {
		return empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public Double getComm() {
		return comm;
	}
	public void setComm(Double comm) {
		this.comm = comm;
	}
}


