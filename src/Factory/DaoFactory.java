package Factory;

import java.sql.Connection;

import Impl.EmpDAOImpl;

/**
 * Ϊ��ȡ��һ��EmpDAOImpl ����
 *��Ϊ�˷���һ���ӿڶ���
 *ҵ�������������֮�У������һ���ӿڶ���֮��
 *�Ϳ���������ݽ��в���
 *����ֻ�ôӹ��������ó���һ�� �࣬�������  ����ʵ�ʵ� ��Ա����ݽ��в����ķ���
 *������͹��ˣ�Factory�Ĺ��ܾ�����Щ��
 *�����EmpDAOImpl �������� ��װ��������ͽ�����װ
 *������ľ���ʵ�֣���ֻ��������һ����Ȼ��ʹ����Ĺ��� 
 * Ϊʲô�أ�IEmpDAO �����Ǹ���Ա�����淶�Ľӿ���
 * @author 35.32
 *
 */
public class DaoFactory {
	public static EmpDAOImpl getIEmpDAOinstance(Connection conn) {
		return new EmpDAOImpl(conn);
	}
}
