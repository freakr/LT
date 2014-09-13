package freakrware.lt.app.core.util;

import android.content.Context;
import android.location.Location;

public class CheckCoords {
	
	private Context context;
	
	public CheckCoords(Context context){
		this.context = context;
		check();
	}
	public void check(){
	Coordinates coord = new Coordinates(context);
	Location loc = coord.get_location();
	float distance = loc.distanceTo(loc);
	}
	

}
