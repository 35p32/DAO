package Dao;

import java.util.List;

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
  //�˴�������IDAO ��׼�ӿ�
public interface IEmpDAO   extends IDAO<Integer ,Emp > {
	/**
	 * ��ѯ��Ա����ϸ��Ϣ�� ���������ڲ��ŵ���Ϣ
	 * @param id Ҫ��ѯ�Ĺ�Ա���
	 * @return  ��vo���󷵻أ�û���򷵻�NULL
	 * @throws Exception SQL��ѯ����
	 */
	public Emp findByIdDetails(Integer id) throws Exception;
	/**
	 * ��ѯ���й�Ա������������Ϣ
	 * @return	��Ա�� List<> �������û�����ݣ���	�ϳ���Ϊ��
 	 * @throws Exception SQL��ѯ����
	 */
	public List<Emp> findAllDetails() throws Exception;
	/**
	 * ��ҳ��ѯ ��Ա��ϸ��Ϣ
	 * @param currentpage ��ǰ����ҳ��
	 * @param linesize  һҳ���м�����¼
	 * @param column ����ģ����ѯ�����ݿ���
	 * @param keywords ģ����ѯ�ؼ���
	 * @return
	 */
	public List<Emp> findAllSplitDetails(Integer currentPage,Integer lineSize, String column ,String keyword) throws Exception;
	
}  
