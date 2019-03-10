package Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import vo.Emp;
/*
 *服务层的操作规范
 */
public interface  IEmpService {
	public boolean insert(Emp emp ) throws Exception;
	public boolean update(Emp emp) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public Emp get(Integer id) throws Exception;
	public List<Emp> list( ) throws Exception;
	public Map<String ,Object > list(int currentPage, int linesize,String keywords,String column) throws Exception;
	public Map<String ,Object > listDetails(int currentPage, int linesize,String keywords,String column) throws Exception;
	
}
