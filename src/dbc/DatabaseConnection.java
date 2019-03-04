package dbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 本类专门负责数据库的连接和关闭操作
*只要你调用我这个类，我都会返回给你一个数据库连接对象
*但是如果数据库连接不上，后续的工作都是白费
*无条件提供一个数据库的连接
 * @author 35.32
 *
 */
public class DatabaseConnection {
	
		//数据库驱动
		private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver" ;
		//连接基本信息
		private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:SOUJIUSUBDB" ;
		//用户名
		private static final String USER = "system" ;
		//密码
		private static final String PASSWORD = "soujiu" ;
		
		@SuppressWarnings("unused")
		private Connection conn=  null;
		
		
		/**
		 * 构造方法
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
		 * 取得一个数据库连接对象
		 */
		
		public Connection getConnection() {
			return this.conn;
		}
		
		/**
		 * 负责数据库关闭
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
