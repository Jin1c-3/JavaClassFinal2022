package yujingyi.thread;

import java.util.List;

import yujingyi.fileOper.TxtWriterImpl;
import yujingyi.pojo.Book;

public class DrawThread extends Thread {
	private List<Book> book_list;

	public DrawThread(List<Book> book_list) {
		super();
		this.book_list = book_list;
	}

	public void run() {
		synchronized (book_list) {
			TxtWriterImpl tw = new TxtWriterImpl();
			tw.write(book_list);
		}
	}

}
