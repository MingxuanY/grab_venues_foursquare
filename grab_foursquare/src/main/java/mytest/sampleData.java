package mytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sampleData {

	public static void main(String[] args) {

		List data=new ArrayList<String>();
		try {
			BufferedReader brd = new BufferedReader(
					new FileReader(
							"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/crossings.csv"));
			brd.readLine();
			String line = "";
			while ((line = brd.readLine()) != null) {
				data.add(line);
			}
			brd.close();
			Collections.shuffle(data);
			BufferedWriter bwt=new BufferedWriter(
					new FileWriter(
							"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/50000Crossing.csv"));
			bwt.write("index,latitude,longitude");
			bwt.newLine();
			for(int i=0;i<50000;i++)
			{
				bwt.write(data.get(i).toString());
				bwt.newLine();
				
			}
			bwt.flush();
			bwt.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
