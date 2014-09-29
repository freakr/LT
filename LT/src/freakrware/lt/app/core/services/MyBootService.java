package freakrware.lt.app.core.services;

import freakrware.lt.app.core.util.Alarm;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyBootService extends Service {

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onStart(Intent intent, int startid) {

		//Alarm alarm = new Alarm(this);
		//alarm.Start();

		//stopSelf();

	}

}
