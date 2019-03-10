package Service;

import java.util.List;
import java.util.Set;

/***
 * ���Ų����淶�ӿ�
 */
import vo.Dept;

public interface  IDeptService {
	public boolean insert(Dept dept) throws Exception;
	public boolean update(Dept dept) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public Dept get(Integer id) throws Exception;
	public List<Dept> list() throws Exception;
	
}
