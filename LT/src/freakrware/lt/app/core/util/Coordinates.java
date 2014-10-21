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
	private static final double range = 100;
	private boolean serviceaccess = false;
	
	
	@SuppressLint("NewApi")
	public Coordinates(Context context,int time){
		mGoogleApiClient = new GoogleApiClient.Builder(context)
	    .addApi(LocationServices.API)
	    .addConnectionCallbacks(this)
	    .addOnConnectionFailedListener(this)
	    .build();
		
		mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(time); 
        mGoogleApiClient.connect();
		}
	public void set_service_accesss(){
		this.serviceaccess = true;
		
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
	public void is_location_in_range() {
		String[] locations = db.get_locations();
		for(int x = 0;x < locations.length;x++){
			String[] locationsdata = db.get_locations_data(db.exists_location(locations[x]));
			double distance = get_distance(locationsdata[0], locationsdata[1]);
			if(distance < range){
				db.edit_location_state_value(db.exists_location(locations[x]), true);
				
			}
			else
			{
				db.edit_location_state_value(db.exists_location(locations[x]), false);
			}
		}
		int[] linrange = db.get_locations_in_range();
		for(int y = 0;y < linrange.length;y++){
			do_tasks_in_range(db.get_tasks_from_location(linrange[y]));
		}
		
		
	}


	private void do_tasks_in_range(int[] taskids) {
		for(int x = 0;x < taskids.length;x++)
		{
			if(db.get_taskstandards_data(taskids[x], DataBase.DB_COL_12))
				{
					if(!standard.is_Wifi_active())
					{
						standard.Wifi_enable();
					}
				}
			if(db.get_taskstandards_data(taskids[x], DataBase.DB_COL_14))
			{
				if(!standard.is_Sound_active())
				{
					standard.Sound_normal();
				}
			}
			if(!db.get_taskstandards_data(taskids[x], DataBase.DB_COL_12))
			{
				if(standard.is_Wifi_active())
				{
					standard.Wifi_disable();
				}
			}
			if(!db.get_taskstandards_data(taskids[x], DataBase.DB_COL_14))
			{
				if(standard.is_Sound_active())
				{
					standard.Sound_vibrate();
				}
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
		if(serviceaccess)
		{
			is_location_in_range();
			serviceaccess = false;
			LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
			System.exit(0);
		}
	}






}
