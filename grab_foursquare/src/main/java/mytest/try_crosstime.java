package mytest;

import java.util.Timer;

import com.csvreader.CsvWriter;

import fi.foyt.foursquare.api.FoursquareApiException;

public class try_crosstime {
	public static int i=0;
	static Timer timer = new Timer();
	static cross c=new cross();
	public static void main(String[] args) {
		c.csv_wr=new CsvWriter("E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/cross.csv");
		timer.schedule(new crossTask(), 1000, 5*1000);
	}

}
class crossTask extends java.util.TimerTask
{
	public void run()
	{
		
		if (try_crosstime.i < 10) {
			try_crosstime.c.write(try_crosstime.i);
			System.out.println("next----------------------------------");
		}
		else
		{
			System.out.println("end");
			try_crosstime.timer.cancel();
			try_crosstime.c.csv_wr.close();
		}
		try_crosstime.i++;
	}
}