package Factory;

import java.sql.Connection;

import Impl.DeptDAOImpl;
import Impl.EmpDAOImpl;

/**
 * Ϊ��ȡ��һ��EmpDAOImpl ����   //3.4 ����ȡ��DeptDAOImpl����
 *��Ϊ�˷���һ���ӿڶ���
 *ҵ�������������֮�У������һ���ӿڶ���֮��
 *�Ϳ���������ݽ��в���
 *����ֻ�ôӹ��������ó���һ�� �࣬�������  ����ʵ�ʵ� ��Ա����ݽ��в����ķ���
 *������͹��ˣ�Factory�Ĺ��ܾ�����Щ��
 *�����EmpDAOImpl �������� ��װ��������ͽ�����װ
 *������ľ���ʵ�֣���ֻ��������һ����Ȼ��ʹ����Ĺ��� 
 * Ϊʲô�أ�IEmpDAO �����Ǹ���Ա�����淶�Ľӿ���
 *
 */
public class DaoFactory {
	public static EmpDAOImpl getIEmpDAOinstance(Connection conn) {
		return new EmpDAOImpl(conn);
	}
	public static DeptDAOImpl getIDeptDAOinstance(Connection conn) {
		return new DeptDAOImpl(conn);
	}
}
