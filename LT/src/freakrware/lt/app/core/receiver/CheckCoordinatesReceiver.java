package freakrware.lt.app.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import freakrware.lt.app.core.util.Coordinates;
import freakrware.lt.app.core.util.CoordinatesService;
import freakrware.lt.app.resources.Interfaces;

public class CheckCoordinatesReceiver extends BroadcastReceiver implements Interfaces{

	@Override
	public void onReceive(Context context, Intent intent) {
		standard.set_Context(context);
		new CoordinatesService(context,20000);
}	

}
