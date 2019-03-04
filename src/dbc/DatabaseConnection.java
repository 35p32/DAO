package dbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ����ר�Ÿ������ݿ�����Ӻ͹رղ���
*ֻҪ�����������࣬�Ҷ��᷵�ظ���һ�����ݿ����Ӷ���
*����������ݿ����Ӳ��ϣ������Ĺ������ǰ׷�
*�������ṩһ�����ݿ������
 * @author 35.32
 *
 */
public class DatabaseConnection {
	
		//���ݿ�����
		private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver" ;
		//���ӻ�����Ϣ
		private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:SOUJIUSUBDB" ;
		//�û���
		private static final String USER = "system" ;
		//����
		private static final String PASSWORD = "soujiu" ;
		
		@SuppressWarnings("unused")
		private Connection conn=  null;
		
		
		/**
		 * ���췽��
		 * @throws Exception
		 */
		public DatabaseConnection() { 
			try {
				
				Class.forName(DBDRIVER);
				this.conn = DriverManager.getConnection(DBURL,USER,PASSWORD);

			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * ȡ��һ�����ݿ����Ӷ���
		 */
		
		public Connection getConnection() {
			return this.conn;
		}
		
		/**
		 * �������ݿ�ر�
		 */
		public  void close() {
			if(this.conn == null) {
				try {
					this.conn.close();
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
}
