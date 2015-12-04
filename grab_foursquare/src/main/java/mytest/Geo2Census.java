package mytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.ws.Response;
















import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.csvreader.CsvReader;

import fi.foyt.foursquare.api.io.IOHandler;
import fi.foyt.foursquare.api.io.Method;

public class Geo2Census {
	public String basic_url="http://geocoding.geo.census.gov/geocoder/geographies/coordinates?format=json&vintage=4&benchmark=4";
	public int runtime=0;
	public static void main(String[] args) {
		Geo2Census G=new Geo2Census();
		try {
//			BufferedReader brd = new BufferedReader(
//					new FileReader(
//							"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/500000_food(2).csv"));
			CsvReader crd=new CsvReader("E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/500000_food(2).csv");
			BufferedWriter bwt=new BufferedWriter(
				new FileWriter(
						"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/500000_withcensus.csv"));
			String head=crd.getRawRecord();
			head+=",censusID,census_name,blockID,block_name";
			bwt.write(head);
			bwt.newLine();
			crd.readHeaders();
			//String line="";
			while(crd.readRecord())
			{
				//String[] record=line.split(",");
				String info=G.getJSON(crd.get("longitude")+","+crd.get("latitude"));
				bwt.write(crd.getRawRecord()+","+info);
				bwt.newLine();
			}
			bwt.flush();
			bwt.close();
			crd.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getJSON(String coordinate)
	{
		String[] str=coordinate.split(",");
		String url=basic_url+"&x="+str[0]+"&y="+str[1];
		
//		IOHandler ioHandler = null;
//		fi.foyt.foursquare.api.io.Response response=ioHandler.fetchData(url, Method.GET);
		String response=getResponse(url);
		runtime++;
		System.out.println(response);
		System.out.println(runtime);
		JSONObject j_object=null;
		String result = "";
		try {
			j_object = new JSONObject(response);
			JSONObject res=j_object.getJSONObject("result");
			JSONObject geo=res.getJSONObject("geographies");
			JSONArray c_array=geo.getJSONArray("Census Tracts");
			JSONObject census=c_array.getJSONObject(0);
			JSONArray b_array=geo.getJSONArray("2010 Census Blocks");
			JSONObject block=b_array.getJSONObject(0);
			result+=census.getString("OID")+","+census.getString("NAME")+","+block.getString("OID")+","+block.getString("NAME");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public String getResponse(String address)
	{
		 URL url;
		 String result="";
		try {
			url = new URL(address);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();  
			//GET Request Define:   
			urlConnection.setRequestMethod("GET");  
			//urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.connect();
			BufferedReader read = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(),"UTF-8"));
			String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
            read.close();
            urlConnection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
}
