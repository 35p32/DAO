package Dao;

import java.util.List;
import java.util.Set;
/**
 * �������ṩ��Dept ��Ĳ����淶�ӿ�
 * ����ʹ���˷���
 * Ϊ��ʵ���ǣ����Ը��õ�ʹ�ô˽ӿڣ����������˿���ֲ�ԣ���ʹ���˷���
 * @author 35.32
 *
 * @param <K> ������������ͣ�����  Dept
 * @param <V> �������������� 
 */
public interface  IDAO <K,V>{
	//����һ��Emp ��
	public boolean doCreate(V vo) throws Exception;
	//����һ��Emp��
	public boolean doUpdate(V vo) throws Exception;
	//����ɾ��
	public boolean doRemoveBatch(Set<K> ids) throws Exception;
	//���ݹ�Ա��Ų�ѯָ���Ĺ�Ա��Ϣ
	public V findById(K id) throws Exception;
	//��ѯ���й�Ա��Ϣ
	public  List<V> findAll() throws Exception;
	//��ҳ��ѯ, ��ǰҳ��ÿҳ����������ģ����ѯ�������У�ģ����ѯ�Ĺؼ���
	public List<V> findAllSplit(Integer currentpage, Integer linesize, String column ,String keywords) throws Exception;
	//ͳ�Ʋ�ѯ����
	public Integer getAllCount(String column, String keywords) throws Exception;
}
