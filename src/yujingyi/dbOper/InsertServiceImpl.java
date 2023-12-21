package yujingyi.dbOper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertServiceImpl implements InsertServiceInter, CloseServiceInter {// 继承了插入接口和关闭接口
	Connection conn = null;
	PreparedStatement ps = null;

	public InsertServiceImpl(String type) {
		// TODO Auto-generated constructor stub
		conn = new DBConnFac().createDBConn(type);
	}

	@Override
	public Boolean insertRecords(String input) {// 输入进来的参数必须以逗号作为分割，如果是其他符号，需要修改 cut 变量
		// TODO Auto-generated method stub
		String cut = ",";// 分割符号，可根据情况修改
		System.out
				.println("dbOper.Service.InsertServiceImpl.insertRecords => 即将使用'" + cut + "'分割从前端得到的数据：\n\t" + input);
		String[] v = input.split(cut);

		// 只需要改动SQL语句即可
		// 注意Java中的字符串只能用双引号，数据库中可以用单引号，此处应为单双混合写法
		String sql = "insert into book values('" + v[0] + "','" + v[1] + "','" + v[2] + "','" + v[3] + "'," + v[4]
				+ ")";
		System.out.println("dbOper.Service.InsertServiceImpl.insertRecords => 即将执行这段SQL语句：\n\t" + sql);
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("dbOper.Service.InsertServiceImpl.insertRecords => SQL执行失败");
			return false;
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		// 此处的关闭顺序和生成顺序正好相反
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("dbOper.Service.InsertServiceImpl.close => 关闭ps出错");
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("dbOper.Service.InsertServiceImpl.close => 关闭conn出错");
			}
	}
}
