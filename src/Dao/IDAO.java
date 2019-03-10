package Dao;

import java.util.List;
import java.util.Set;
/**
 * 这里是提供给Dept 表的操作规范接口
 * 这里使用了泛型
 * 为了实例们，可以更好地使用此接口，我们增加了可移植性，即使用了泛型
 * @author 35.32
 *
 * @param <K> 基本表对象类型，比如  Dept
 * @param <V> 主键的数据类型 
 */
public interface  IDAO <K,V>{
	//创造一个Emp 表
	public boolean doCreate(V vo) throws Exception;
	//更新一个Emp表
	public boolean doUpdate(V vo) throws Exception;
	//批量删除
	public boolean doRemoveBatch(Set<K> ids) throws Exception;
	//根据雇员编号查询指定的雇员信息
	public V findById(K id) throws Exception;
	//查询所有雇员信息
	public  List<V> findAll() throws Exception;
	//分页查询, 当前页，每页行数，进行模糊查询的数据列，模糊查询的关键字
	public List<V> findAllSplit(Integer currentpage, Integer linesize, String column ,String keywords) throws Exception;
	//统计查询数量
	public Integer getAllCount(String column, String keywords) throws Exception;
}
