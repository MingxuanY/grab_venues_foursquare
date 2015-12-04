package mytest;
import java.io.IOException;
import java.util.Timer;

import com.csvreader.CsvWriter;

import mytest.getData_try;
import fi.foyt.foursquare.api.*;
public class get_data_hourly {
	
	static double locat[][]={{34.666605,-118.818303,34.822242,-118.218303},
						{34.666605,-118.218303,34.822242,-117.669033},
						{34.516605,-118.748303,34.666605,-118.148303},
						{34.516605,-118.148303,34.666605,-117.669033},
						{34.366605,-118.688303,34.516605,-118.088303},
						{34.366605,-118.088303,34.516605,-117.669033},
						{34.216605,-118.628303,34.366605,-118.028303},
						{34.216605,-118.028303,34.366605,-117.669033},
						{34.046605,-118.663303,34.216605,-118.063303},
						{34.046605,-118.063303,34.216605,-117.669033},
						{33.946191,-118.443303,34.046605,-117.809033},
						{33.746191,-118.413303,33.946191,-118.099033}};
	static double[] l_temp=new double[4];
	static String categoryId="";
	static getData_try getD=new getData_try();
	static int runtime=0;
	static Timer timer = new Timer();
	public static void main(String[] args) {
		
		long begintime = System.currentTimeMillis();
		//get_data_hourly getDH=new get_data_hourly();
	    try {
	    	get_data_hourly.getD.csv_wr=new CsvWriter("E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/food1.csv");
	    	String[] title={"venue_ID","venue_name","category","Street","Postcode","price","checkins","users","latitude","longitude"};
	    	get_data_hourly.getD.csv_wr.writeRecord(title);
	    	
	    	
	    	
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		l_temp=get_data_hourly.locat[0];
		categoryId="4d4b7105d754a06374d81259";//food
		
//		for(int i=0;i<3;i++)
//		{
			l_temp=get_data_hourly.locat[get_data_hourly.runtime];
			timer.schedule(new myTask(), 1000, (60+10)*60*1000);
			
//		}
		
//		System.out.println("food & drink");
//		categoryId="4bf58dd8d48988d1f9941735";//food & drink restaurant
//		
//		for(int i=0;i<getDH.locat.length;i++)
//		{
//			l_temp=getDH.locat[i];
//			timer.schedule(new myTask(), 1000, (60+10)*60*1000);
//			System.out.println("next----------------------------------");
//		}
		
		//get_data_hourly.getD.csv_wr.close();
	    long endtime=System.currentTimeMillis();
	    long costTime = (endtime - begintime);
	    System.out.println("running time:"+Double.toString(costTime/60000));
		
	}
}
class myTask extends java.util.TimerTask
{
	public void run()
	{
		
		if (get_data_hourly.runtime < get_data_hourly.locat.length) {
			try {
				get_data_hourly.getD.searchVenues(get_data_hourly.l_temp[0],
						get_data_hourly.l_temp[1], get_data_hourly.l_temp[2],
						get_data_hourly.l_temp[3], get_data_hourly.categoryId);
				System.out.println("next----------------------------------");
			} catch (FoursquareApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			get_data_hourly.timer.cancel();
			get_data_hourly.getD.csv_wr.close();
		}
		get_data_hourly.runtime++;
	}
}