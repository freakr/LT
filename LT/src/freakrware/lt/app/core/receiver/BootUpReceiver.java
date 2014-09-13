package freakrware.lt.app.core.receiver;

import freakrware.lt.app.core.services.MyBootService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootUpReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

			Intent i = new Intent(context, MyBootService.class);
			context.startService(i);

		}

	}

}