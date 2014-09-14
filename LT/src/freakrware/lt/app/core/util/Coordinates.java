package freakrware.lt.app.core.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Coordinates {
	
	private LocationManager locationManager;
	private Context context;
	private Location loc;
	public LocationListener lListener= new LocationListener() {
        public void onLocationChanged(Location location) {
            // Each time the location is changed we assign loc
            loc = location;
        }

         // Need these even if they do nothing. Can't remember why.
         public void onProviderDisabled(String arg0) {}
         public void onProviderEnabled(String provider) {}
         @Override
         public void onStatusChanged(String provider, int status, Bundle extras) {}

		
    };
	
	@SuppressLint("NewApi")
	public Coordinates(Context context){
		this.context = context;
		
		// Get the location manager
	    locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the location provider -> use
	    // default
	    //Criteria criteria = new Criteria();
	    //criteria.setAccuracy(Criteria.ACCURACY_FINE);
	    boolean gpsstatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	    if(gpsstatus){
	    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 35000, 10, lListener);
	    }else{
	    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 35000, 10, lListener);
	    	//provider = locationManager.getBestProvider(criteria, true);
	    	//loc = locationManager.getLastKnownLocation(provider);
	    }
	    
	    
	    
	}
	public Location get_location(){
		if(loc != null){
	    	
	    }else{
	    	loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    }
		return loc;
		
	}
}
