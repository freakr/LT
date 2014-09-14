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
	private Location gpsloc;
	private Location networkloc;
	
	boolean gpsstatus ;
	public LocationListener gpsListener= new LocationListener() {
        public void onLocationChanged(Location location) {
            // Each time the location is changed we assign loc
            gpsloc = location;
        }

         // Need these even if they do nothing. Can't remember why.
         public void onProviderDisabled(String provider) {}
         public void onProviderEnabled(String provider) {}
         @Override
         public void onStatusChanged(String provider, int status, Bundle extras) {}

		
    };
    public LocationListener networkListener= new LocationListener() {
        public void onLocationChanged(Location location) {
            // Each time the location is changed we assign loc
            networkloc = location;
        }

         // Need these even if they do nothing. Can't remember why.
         public void onProviderDisabled(String provider) {}
         public void onProviderEnabled(String provider) {}
         @Override
         public void onStatusChanged(String provider, int status, Bundle extras) {}

		
    };
	
	@SuppressLint("NewApi")
	public Coordinates(Context context){
		this.context = context;
		
		locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
	    gpsstatus = check_gps_status();
	    
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, gpsListener);
	    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, networkListener);
	    
	}
	private boolean check_gps_status(){
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
	}
	public Location get_location(){
		if(check_gps_status()){
			loc = gpsloc;
	    }else{
	    	loc = networkloc;
	    }
		if(loc != null){}
		else{
			loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		}
		return loc;
		
	}
}
