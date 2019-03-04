package Dao;

import java.util.List;
import java.util.Set;

import vo.Emp;

/**
 * ����emp���ݲ�Ĳ�����׼
 * 
 * ����һ���ӿ� 
 * ��Ա�����Ĺ淶
 * ����ӿ� ������һϵ�еĶ������ǶԻ�������д���Ķ�����˭Ҫ ʵ����������ӿڣ�˭��Ҫ��д������
 * 
 * �൱���û��㵽���ݲ�ĵ���
 * @author 35.32
 *
 */
public interface IEmpDAO {
	//����һ��Emp ��
	public boolean doCreate(Emp vo) throws Exception;
	//����һ��Emp��
	public boolean doUpdate(Emp vo) throws Exception;
	//����ɾ��
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception;
	//���ݹ�Ա��Ų�ѯָ���Ĺ�Ա��Ϣ
	public Emp findById(Integer id) throws Exception;
	//��ѯ���й�Ա��Ϣ
	public  List<Emp> findAll() throws Exception;
	//��ҳ��ѯ, ��ǰҳ��ÿҳ����������ģ����ѯ�������У�ģ����ѯ�Ĺؼ���
	public List<Emp> findAllSplit(Integer currentpage, Integer linesize, String column ,String keywords) throws Exception;
	//ͳ�Ʋ�ѯ����
	public Integer getAllCount(String column, String keywords) throws Exception;
}  
