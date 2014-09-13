package freakrware.lt.app.core.receiver;

import freakrware.lt.app.core.util.Coordinates;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

public class CheckCoordinatesReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Coordinates coord = new Coordinates(context);
		Location loc = coord.get_location();
		
		
	}

}
