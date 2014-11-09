package freakrware.lt.app.core.util;

import java.util.ArrayList;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;


import android.os.Looper;
import freakrware.lt.app.resources.Interfaces;

public class CoordinatesService implements Interfaces, ConnectionCallbacks,LocationListener, OnConnectionFailedListener{
	
	private GoogleApiClient mGoogleApiClient;
	private Location loc;
	private static double range = (double) Integer.parseInt(db.get_setup_parameter(db.exists_parameter("RANGE")));
	
	public CoordinatesService(Context context,int time){
		mGoogleApiClient = new GoogleApiClient.Builder(context)
	    .addApi(LocationServices.API)
	    .addConnectionCallbacks(this)
	    .addOnConnectionFailedListener(this)
	    .build();
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
	public void is_location_in_range() {
		range = (double) Integer.parseInt(db.get_setup_parameter(db.exists_parameter("RANGE")));
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
		if(linrange.length != 0 )
		{
			String irdone = db.get_setup_parameter(db.exists_parameter("IR_DONE"));
			if( irdone.equals(String.valueOf(false)))
			{
				do_tasks_in_range(db.get_tasks_from_location(linrange[0]));
				db.edit_setup_parameter_value(db.exists_parameter("LAST_LOCATION"), db.get_location(linrange[0]));
				db.edit_setup_parameter_value(db.exists_parameter("OOR_DONE"),String.valueOf(false));
				db.edit_setup_parameter_value(db.exists_parameter("IR_DONE"),String.valueOf(true));
			}
			
		}
		else
		{
			String oordone = db.get_setup_parameter(db.exists_parameter("OOR_DONE"));
			if( oordone.equals(String.valueOf(false)))
			{
				do_tasks_out_of_range(db.get_tasks_from_location(db.exists_location(db.get_setup_parameter(db.exists_parameter("LAST_LOCATION")))));
				db.edit_setup_parameter_value(db.exists_parameter("IR_DONE"),String.valueOf(false));
				db.edit_setup_parameter_value(db.exists_parameter("OOR_DONE"),String.valueOf(true));
			}
			
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
			String[] programmstostart = db.get_task_programms(taskids[x]);
			PackageManager pm = standard.context.getPackageManager();
			ArrayList<PackageInfoStruct> apps = standard.getPackages();
			
			
			if(programmstostart != null)
			{
				for(int y = 0 ; y < programmstostart.length;y++)
				{
					String pname = null;
					for(int z = 0 ; z < apps.size();z++)
					{
						if(apps.get(z).appname.equals(programmstostart[y]))
							{
								pname = apps.get(z).packageName;
							}
					}
					if(pname != null)
					{
						Intent LaunchIntent = pm.getLaunchIntentForPackage(pname);
						standard.context.startActivity(LaunchIntent);
					}
					
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
		loc = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		is_location_in_range();
		mGoogleApiClient.disconnect();
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		System.out.println(arg0);
		
	}

	@Override
	public void onLocationChanged(Location location) {
		loc = location;
	}
	private void do_tasks_out_of_range(int[] taskids) {
		for(int x = 0;x < taskids.length;x++)
		{
			if(db.get_taskstandards_data(taskids[x], DataBase.DB_COL_13))
			{
				if(!standard.is_Wifi_active())
				{
					standard.Wifi_enable();
				}
			}
			if(db.get_taskstandards_data(taskids[x], DataBase.DB_COL_15))
			{
				if(!standard.is_Sound_active())
				{
					standard.Sound_normal();
				}
			}
			if(!db.get_taskstandards_data(taskids[x], DataBase.DB_COL_13))
			{
				if(standard.is_Wifi_active())
				{
					standard.Wifi_disable();
				}
			}
			if(!db.get_taskstandards_data(taskids[x], DataBase.DB_COL_15))
			{
				if(standard.is_Sound_active())
				{
					standard.Sound_vibrate();
				}
			}
		}
		
	}






}
