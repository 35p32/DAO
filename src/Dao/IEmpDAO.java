package Dao;

import java.util.List;
import java.util.Set;

import vo.Emp;

/**
 * 定义emp数据层的操作标准
 * 
 * 这是一个接口 
 * 针对表操作的规范
 * 这个接口 充满了一系列的动作，是对基本表进行处理的动作，谁要 实例化我这个接口，谁就要覆写方法。
 * 
 * 相当于用户层到数据层的调用
 * @author 35.32
 *
 */
public interface IEmpDAO {
	//创造一个Emp 表
	public boolean doCreate(Emp vo) throws Exception;
	//更新一个Emp表
	public boolean doUpdate(Emp vo) throws Exception;
	//批量删除
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception;
	//根据雇员编号查询指定的雇员信息
	public Emp findById(Integer id) throws Exception;
	//查询所有雇员信息
	public  List<Emp> findAll() throws Exception;
	//分页查询, 当前页，每页行数，进行模糊查询的数据列，模糊查询的关键字
	public List<Emp> findAllSplit(Integer currentpage, Integer linesize, String column ,String keywords) throws Exception;
	//统计查询数量
	public Integer getAllCount(String column, String keywords) throws Exception;
}  
