package freakrware.lt.app.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import freakrware.lt.app.core.util.Coordinates;

public class CheckCoordinatesReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Coordinates cord = new Coordinates(context,1000);
		cord.is_location_in_range();
	}

}
