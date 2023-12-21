package yujingyi.dbOper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnFac {

	// 在使用factory之前需要先导入jar包，先创建一个libs文件夹
	public static Connection createDBConn(String type) {
		Connection conn = null;
		String[] types = { "(?i)mysql", "(?i)sqlserver" };// 专门使用正则表达式，避免大小写问题导致的无法初始化的问题

		if (type.matches(types[0])) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?" + "user=root&password=root");// 这个地方要更改数据库名称，就是mystudb
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("dbOper.factory.DBConnFac => mysql 数据库连接出现问题！");
			}
		} else if (type.matches(types[1])) {
			// 与SQL server相关的操作在这里写。暂时空白
		}
		return conn;
	}
}