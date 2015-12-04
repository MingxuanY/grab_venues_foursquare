package mytest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;

import com.csvreader.CsvReader;

import fi.foyt.foursquare.api.FoursquareApiException;

public class getData_all {
	static String categoryId="";
	static int runtime=0;
	static getData_circle getD=new getData_circle();
	static Timer timer = new Timer();
	static CsvReader csv_rd;
	static List<Double> list_lat=new ArrayList<Double>();
	static List<Double> list_lng=new ArrayList<Double>();
	public static void main(String[] args) {
		try {
			getData_all.csv_rd=new CsvReader(
					"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/50000Crossing.csv");
			
			String la="";
			csv_rd.readHeaders();
			while(csv_rd.readRecord())
			{
				la=csv_rd.get(1);
				list_lat.add(Double.parseDouble(la));
				String lg=csv_rd.get(2);
				list_lng.add(Double.parseDouble(lg));					
			}
			
			categoryId="4d4b7105d754a06374d81259";//food
			timer.schedule(new requestTask(), 1000, (60+10)*60*1000);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
class requestTask extends java.util.TimerTask
{
	public void run()
	{
		if(getData_all.runtime<getData_all.list_lat.size())
		{
		 for(int i=0;i<5000;i++)
	        {
	                 try {
						getData_all.getD.searchVenues(getData_all.list_lat.get(getData_all.runtime),getData_all.list_lng.get(getData_all.runtime),100,getData_all.categoryId);
					} catch (FoursquareApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                 getData_all.runtime++;
	                 if(getData_all.runtime>=getData_all.list_lat.size())
	                 {
	                	 getData_all.timer.cancel();
	         			getData_all.getD.csv_wr.close();
	         			break;
	                 }
	        }
		}
		else
		{
			getData_all.timer.cancel();
			getData_all.getD.csv_wr.close();
		}
	}
}
