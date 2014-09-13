package freakrware.lt.app.core.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class Coordinates {
	
	private LocationManager locationManager;
	private String provider;
	private Context context;
	private Location location;
	
	public Coordinates(Context context){
		this.context = context;
		// Get the location manager
	    locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the location provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    location = locationManager.getLastKnownLocation(provider);
	}
	public Location get_location(){
		return location;
		
	}
}
