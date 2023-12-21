package yujingyi.thread;

import java.util.List;

import yujingyi.fileOper.TxtWriterImpl;
import yujingyi.pojo.Book;

public class Thread {
	public synchronized void write(List<Book> book_list) {
		TxtWriterImpl tw = new TxtWriterImpl();
		tw.write(book_list);
	}
}
