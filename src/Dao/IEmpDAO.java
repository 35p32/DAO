package Dao;

import java.util.List;

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
  //此处重用了IDAO 标准接口
public interface IEmpDAO   extends IDAO<Integer ,Emp > {
	/**
	 * 查询雇员的详细信息， 包括其所在部门的信息
	 * @param id 要查询的雇员编号
	 * @return  以vo对象返回，没有则返回NULL
	 * @throws Exception SQL查询错误
	 */
	public Emp findByIdDetails(Integer id) throws Exception;
	/**
	 * 查询所有雇员的所有完整信息
	 * @return	雇员的 List<> 对象，如果没有数据，集	合长度为零
 	 * @throws Exception SQL查询错误
	 */
	public List<Emp> findAllDetails() throws Exception;
	/**
	 * 分页查询 雇员详细信息
	 * @param currentpage 当前所在页数
	 * @param linesize  一页含有几条记录
	 * @param column 进行模糊查询的数据库列
	 * @param keywords 模糊查询关键词
	 * @return
	 */
	public List<Emp> findAllSplitDetails(Integer currentPage,Integer lineSize, String column ,String keyword) throws Exception;
	
}  
