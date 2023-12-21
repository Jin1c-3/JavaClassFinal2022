package yujingyi.dbOper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yujingyi.pojo.Book;

public class QueryServiceImpl implements QueryServiceInter, CloseServiceInter {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public QueryServiceImpl(String type) {// 构造方法中嵌入conn的初始化
		conn = new DBConnFac().createDBConn(type);
	}

	@Override
	public List<Book> selectByPublisher(String publihser) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		try {
			ps = conn.prepareStatement("SELECT * FROM book where publisher like '%" + publihser + "%'");// 这里的基本表需要更改名字！
			rs = ps.executeQuery();
			while (rs.next()) {
				Book s = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				System.out.println("dbOper.Service.QueryServiceImpl.selectByPublisher => 从数据库中获取到的信息：\n\t" + s);
				books.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("dbOper.Service.QueryServiceImpl.selectByPublisher => 出错");
		}
		return books;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// 关闭顺序恰好与声明顺序相反
				e.printStackTrace();
				System.out.println("dbOper.Service.QueryServiceImpl.close => 关闭rs出错");
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("dbOper.Service.QueryServiceImpl.close => 关闭ps出错");
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("dbOper.Service.QueryServiceImpl.close => 关闭conn出错");
			}
	}

}
