package testdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectMySql {
	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	//定义MySQL数据库的连接地址
	public static final String DBURL = "jdbc:mysql://139.224.254.0:3306/springweb";
	//MySQL数据库的连接用户名
	public static final String DBUSER = "lwj";
	//MySQL数据库的连接密码
	public static final String DBPASS = "123456";
	
	
	
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		//数据库插入语句
		String insertSQL = "insert into user (id, name, age) values (3, 'key', 23)";
		//数据库修改语句
		String alterSQL = "update user SET name='jon' where id=8";
		//数据库删除语句
		String deleteSQL = "delete from user where id=5";
		//查询
		String selectString = "select * from springweb.user where id = 1";
        try {
             //加载驱动程序
             Class.forName(DBDRIVER);
        }
        catch (ClassNotFoundException e) {
             e.printStackTrace();
        }
        try {
             //连接MySQL数据库时，要写上连接的用户名和密码
             con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
             
           //实例化Statement对象
           stmt = con.createStatement();
           //执行数据库更新操作
			/*stmt.executeUpdate(insertSQL);
			stmt.executeUpdate(alterSQL);
			stmt.executeUpdate(deleteSQL);*/
           rs = stmt.executeQuery(selectString);
           
           while(rs.next()) {
        	   int id = rs.getInt("id");
        	   String name = rs.getString(3);
        	   System.out.println(id+"->"+name);
           }
           
        }
        catch (SQLException e) {
             e.printStackTrace();
        }
        System.out.println(con);
		try {
		    //关闭数据库
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
