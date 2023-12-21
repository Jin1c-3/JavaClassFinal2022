package yujingyi.dbOper;

import java.util.List;

import yujingyi.pojo.Book;

public interface QueryServiceInter {

	public List<Book> selectByPublisher(String publisher);
}
