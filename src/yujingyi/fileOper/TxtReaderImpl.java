package yujingyi.fileOper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yujingyi.pojo.Book;

public class TxtReaderImpl implements TxtReaderInter {

	@Override
	public List<Book> importData() {
		// TODO Auto-generated method stub
		try {
			List<Book> Books = new ArrayList<Book>();
			BufferedReader br = new BufferedReader(new FileReader(new File("book.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {

				String[] fds = line.split(",");
				Book temp = new Book(fds[0], fds[1], fds[2], fds[3], fds[4]);

				Books.add(temp);
			}
			br.close();
			return Books;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Check Invalid FilePath!");// 文件路径异常
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Reading Invlid Lines!");// 文件内部行问题
		}
		return null;
	}

}
