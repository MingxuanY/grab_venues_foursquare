package mytest;

import java.io.IOException;
import java.util.Hashtable;

import fi.foyt.foursquare.*;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import fi.foyt.foursquare.example.BasicExample;

import com.csvreader.*;

public class getData_circle {

	public CsvWriter csv_wr;
	
	public Hashtable h_table=new Hashtable();

	FoursquareApi foursquareApi = new FoursquareApi(
			"H42J3QH4JE51EWOPBRXD45W1S3K2RUW5VAB55U415KAHJIMF",
			"ZW5NAFR3ARLG02C5UAIZ00MBEC4ODML1N0X2SIVJVXPVYGEN",
			"https://www.google.com");
	getData_circle() {
		csv_wr = new CsvWriter(
				"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/food2.csv");
		String[] title = { "venue_ID", "venue_name", "category", "Street",
				"Postcode", "price", "checkins", "users", "latitude",
				"longitude" };
		try {
			csv_wr.writeRecord(title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String ll = args.length > 0 ? args[0] : "34.025264, -118.277396";
		long begintime = System.currentTimeMillis();
		getData_try a = new getData_try();

		try {
			a.csv_wr = new CsvWriter(
					"E:/USC Study/directed research/diabetes/foursquare-api-java-20140131/csv/food_circle1.csv");
			String[] title = { "venue_ID", "venue_name", "category", "Street",
					"Postcode", "price", "checkins", "users", "latitude",
					"longitude" };
			a.csv_wr.writeRecord(title);

			String categoryId = "4d4b7105d754a06374d81259";// "52f2ab2ebcbc57f1066b8b46";

			// search 7
			// rectangles-------------------------------------------------
			// a.searchVenues(leftbound,bottombound,rightbound,topbound,categoryId);
			// a.searchVenues(34.666605,-118.818303,34.822242,-117.669033,categoryId);
			a.searchVenues(34.516605, -118.748303, 34.666605, -117.669033,
					categoryId);
			// a.searchVenues(34.366605,-118.688303,34.516605,-117.669033,categoryId);
			// a.searchVenues(34.216605,-118.628303,34.366605,-117.669033,categoryId);
			// a.searchVenues(34.046605,-118.663303,34.216605,-117.669033,categoryId);
			// a.searchVenues(33.946191,-118.443303,34.046605,-117.809033,categoryId);
			// a.searchVenues(33.746191,-118.413303,33.946191,-118.099033,categoryId);

			// -------------------------------------------------------------------

		} catch (FoursquareApiException e) {
			// TODO: Error handling
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.csv_wr.close();
		long endtime = System.currentTimeMillis();
		long costTime = (endtime - begintime);
		System.out.println("running time:" + Double.toString(costTime / 60000));
	}

	public void searchVenues(double latitude, double longitude, int radius,
			String categoryId) throws FoursquareApiException {
		String ll = Double.toString(latitude);
		ll += ",";
		ll += Double.toString(longitude);

		// First we need a initialize FoursquareApi.
		
		//System.out.println("successfully login");
		// After client has been initialized we can make queries.
		// define range of unit area
		int count = 0;

		Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll,
				null, null, null, null, 50, "browse", categoryId, null, null,
				null, radius, null);

		if (result.getMeta().getCode() == 200) {
			// if query was ok we can finally we do something with the data
			System.out.println(result.getResult().getVenues().length);
			if (result.getResult().getVenues().length >= 50)
				javax.swing.JOptionPane.showMessageDialog(null, "over 50!");
			for (CompactVenue venue : result.getResult().getVenues()) {
				// TODO: Do something we the data
				// System.out.println(venue.getName());
				try {
					if (!h_table.containsKey(venue.getId())) {
						if (venue.getPrice() == null) {
							String[] record = {
									venue.getId(),
									venue.getName(),
									venue.getCategories()[0].getName(),
									venue.getLocation().getAddress(),
									"CA"+venue.getLocation().getPostalCode(),
									null,
									Integer.toString(venue.getStats().getCheckinsCount()),
									Integer.toString(venue.getStats().getUsersCount()),
									Double.toString(venue.getLocation().getLat()),
									Double.toString(venue.getLocation().getLng()) };
							csv_wr.writeRecord(record);
						} else {
							String[] record = {
									venue.getId(),
									venue.getName(),
									venue.getCategories()[0].getName(),
									venue.getLocation().getAddress(),
									"CA"+venue.getLocation().getPostalCode(),
									Integer.toString(venue.getPrice().getTier()),
									Integer.toString(venue.getStats().getCheckinsCount()),
									Integer.toString(venue.getStats().getUsersCount()),
									Double.toString(venue.getLocation().getLat()),
									Double.toString(venue.getLocation().getLng()) };
							csv_wr.writeRecord(record);
						}
						h_table.put(venue.getId(), h_table.size());
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else {
			// TODO: Proper error handling
			System.out.println("Error occured: ");
			System.out.println("  code: " + result.getMeta().getCode());
			System.out.println("  type: " + result.getMeta().getErrorType());
			System.out
					.println("  detail: " + result.getMeta().getErrorDetail());
		}

		System.out.println("no. of requests:" + Integer.toString(count));
		// System.out.println(rec_l);
		// System.out.println(rec_b);

	}
}
