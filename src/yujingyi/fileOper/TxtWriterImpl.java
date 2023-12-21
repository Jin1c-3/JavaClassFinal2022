package yujingyi.fileOper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import yujingyi.pojo.Book;

public class TxtWriterImpl implements TxtWriterInter {

	@Override
	public Boolean write(List<Book> book_list) {
		// TODO Auto-generated method stub
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("result.txt")));
			book_list.forEach(o -> {
				try {
					bw.write(o.toString());
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Writer failed with unknown reason");
				}
			});
			bw.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
