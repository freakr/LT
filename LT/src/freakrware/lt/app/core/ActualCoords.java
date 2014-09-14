package freakrware.lt.app.core;

import android.app.Activity;
import android.location.Location;
import android.widget.TextView;
import freakrware.lt.app.core.util.Coordinates;
import freakrware.lt.app.resources.Interfaces;

public class ActualCoords implements Runnable,Interfaces{
	
	
	private Coordinates ccoords;
	private Activity mActivity;
	private TextView vlatitude;
	private TextView vlongitude;
	private TextView vaccuracy;
	private TextView vprovider;
	
	public ActualCoords(TextView vlongitude, TextView vlatitude, TextView vaccuracy, TextView vprovider, Coordinates ccoords, Activity mActivity) {
		this.ccoords = ccoords;
		this.mActivity = mActivity;
		this.vlongitude = vlongitude;
		this.vlatitude = vlatitude;
		this.vaccuracy = vaccuracy;
		this.vprovider = vprovider;
	}
	
	public Location get_actual_coords(){
		return ccoords.get_location();
	}

	@Override
	public void run() {
		standard.thread_rename("Location Update");
		while(true){
			mActivity.runOnUiThread(new Runnable() {
		        public void run() {
		        	vlongitude.setText(String.valueOf(get_actual_coords().getLongitude()));
	        		vlatitude.setText(String.valueOf(get_actual_coords().getLatitude()));
	        		vaccuracy.setText(String.valueOf(get_actual_coords().getAccuracy())+" m");
	        		vprovider.setText(String.valueOf(get_actual_coords().getProvider()));
		        }
		    });
			standard.wait(5000);
		}
		
	}

}
