package freakrware.lt.app.core;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.widget.TextView;
import freakrware.lt.app.core.util.Coordinates;
import freakrware.lt.app.core.util.DataBase;
import freakrware.lt.app.resources.Interfaces;

public class ActualCoords implements Runnable,Interfaces{
	
	
	private Coordinates ccoords;
	private Activity mActivity;
	private TextView vlatitude;
	private TextView vlongitude;
	private TextView vaccuracy;
	private TextView vnposition;
	private TextView vtime;
	private TextView[] views;
	private DataBase db;
	private String[] names;
	private TextView vdistance;
	private TextView vlupdate;
	public static final DecimalFormat df = new DecimalFormat( "#.00" );
	
	public ActualCoords(DataBase db,TextView vlongitude, TextView vlatitude, TextView vaccuracy, TextView vtime, TextView vnposition, TextView vdistance, Coordinates ccoords, Activity mActivity, TextView vlupdate) {
		this.ccoords = ccoords;
		this.mActivity = mActivity;
		this.vlongitude = vlongitude;
		this.vlatitude = vlatitude;
		this.vaccuracy = vaccuracy;
		this.vnposition = vnposition;
		this.vdistance = vdistance;
		this.vtime = vtime;
		this.vlupdate = vlupdate;
		this.db = db;
	}
	public void get_positions_views(){
		this.names = standard.names;
		this.views = standard.views;
	}
	public Location get_actual_coords(){
		return ccoords.get_location();
	}

	@Override
	public void run() {
		standard.thread_rename("Location Update");
		Thread thread = Thread.currentThread();
		while(!thread.isInterrupted()){
			mActivity.runOnUiThread(new Runnable() {
		        public void run() {
		        	get_positions_views();
		        	SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss",Locale.GERMAN);
		        	Date timecoords = new Date(get_actual_coords().getTime());
		        	String time = tf.format(timecoords);
		        	vlongitude.setText(String.valueOf(get_actual_coords().getLongitude()));
	        		vlatitude.setText(String.valueOf(get_actual_coords().getLatitude()));
	        		vaccuracy.setText(df.format(get_actual_coords().getAccuracy())+" m");
	        		vtime.setText(time);
	        		vnposition.setText(db.get_nearest_Location());
	        		String LAT = db.get_setup_parameter(db.exists_parameter("LAST_ACTION_TIME")); // Sun Nov 09 19:58:37 MEZ 2014
	        		long latime = (DateUtils.MINUTE_IN_MILLIS)+Long.parseLong(LAT);
	        		LAT = tf.format((latime - new Date().getTime())-DateUtils.HOUR_IN_MILLIS);
	        		vlupdate.setText(LAT);
	        		String[] ldata = db.get_locations_data(db.exists_location(db.get_nearest_Location()));
    				String lat = ldata[0];
    				String lon = ldata[1];
    				int dist = (int) ccoords.get_distance(lat, lon);
    				if(dist < 1000){
    					vdistance.setText(df.format(dist)+"  m");
    				}else{
    					vdistance.setText(df.format(dist/1000.0)+" km");
    				}
    				
	        		if(dist < (double) Integer.parseInt(db.get_setup_parameter(db.exists_parameter("RANGE"))))
	        		{
	        			vdistance.setTextColor(Color.GREEN);
	        			vnposition.setTextColor(Color.GREEN);
	        		}
	        		else
	        		{
	        			vdistance.setTextColor(Color.RED);
	        			vnposition.setTextColor(Color.RED);
	        		}
	        		if(views!=null){
	        			for(int x = 0;x < views.length;x++){
	        				String name = names[x];
	        				String[] locationdata = db.get_locations_data(db.exists_location(name));
	        				String lati = locationdata[0];
	        				String longi = locationdata[1];
	        				int distance = (int) ccoords.get_distance(lati, longi);
	        				if(distance < 1000){
	        					views[x].setText(df.format(distance)+"  m");
	        				}else{
	        					views[x].setText(df.format(distance/1000.0)+" km");
	        				}
	        				
							
	        			}
	        		}
	        	}
		    });
		
		standard.sleep(5000);
		
		}
	}
}
