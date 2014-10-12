package freakrware.lt.app.core.util;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;


import freakrware.lt.app.resources.Interfaces;

public class Coordinates implements Interfaces, ConnectionCallbacks,LocationListener, OnConnectionFailedListener{
	
	private GoogleApiClient mGoogleApiClient;

    private LocationRequest mLocationRequest;
    private Location loc;
	private static final double cellrange = 2500;
	private DataBase db = new DataBase();
	
	@SuppressLint("NewApi")
	public Coordinates(Context context,int time){
		mGoogleApiClient = new GoogleApiClient.Builder(context)
	    .addApi(LocationServices.API)
	    .addConnectionCallbacks(this)
	    .addOnConnectionFailedListener(this)
	    .build();
		
		mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(time); // Update location every second
        mGoogleApiClient.connect();
		}
		
	public Location get_location(){
		
		if(loc != null){
			return loc;
		}else{
			return loc = new Location("No Position");
		}
	}
	public double get_distance(String lati, String longi) {
		Location aloc = get_location();
		Location dloc = new Location(aloc);
		dloc.setLatitude(Double.parseDouble(lati));
		dloc.setLongitude(Double.parseDouble(longi));
		return aloc.distanceTo(dloc);
	}
	public void checkdistances() {
		String[] locations = db.get_locations();
		for(int x = 0;x < locations.length;x++){
			String[] locationsdata = db.get_locations_data(db.exists_location(locations[x]));
			double distance = get_distance(locationsdata[0], locationsdata[1]);
			if(distance < Double.parseDouble(locationsdata[2])){
				new CheckTask(locations[x],CheckTask.WIFI_ACCURACY);
			}
			if(distance < cellrange){
				new CheckTask(locations[x],CheckTask.CELL_ACCURACY);
			}
		}
		
	}


	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		System.out.println(arg0);
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		System.out.println(arg0);
		
	}

	@Override
	public void onLocationChanged(Location location) {
		loc = location;
		
	}






}
