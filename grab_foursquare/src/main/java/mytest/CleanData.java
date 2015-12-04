package mytest;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CleanData {

	
	public static void main(String[] args) {
		CleanData c=new CleanData();
		c.cleanGeodata();
	}
	public void cleanGeodata()
	{
		try {
			//CsvReader crd=new CsvReader
			//		("E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/liyuenodes.csv");
			CsvWriter cwt=new CsvWriter
					("E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/crossings.csv");		
			String[] title={"index","latitude","longitude"};
			cwt.writeRecord(title);
			BufferedReader brd=new BufferedReader(new FileReader(
					"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/liyuenodes.txt"));
			brd.readLine();
			String line="";
			int i=1;
			while((line=brd.readLine())!=null)
			{
				String[] str=line.split(",");
				String longitude=str[2].substring(2);
				String latitude=str[3].substring(1);
				System.out.println(longitude+","+latitude);
				String[] record = {Integer.toString(i),latitude,longitude};
				cwt.writeRecord(record);
				i++;
			}
			brd.close();
			cwt.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getlongtitude(String s)
	{
		String[] str=s.split(",");
		String longitude=str[2];
		
		
		return longitude;
	}
}
