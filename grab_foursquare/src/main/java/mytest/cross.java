package mytest;

import java.io.IOException;

import com.csvreader.CsvWriter;

public class cross {

	public CsvWriter csv_wr;
	public void write(int a)
	{
		String[] aa={Integer.toString(a)};
		try {
			csv_wr.writeRecord(aa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
