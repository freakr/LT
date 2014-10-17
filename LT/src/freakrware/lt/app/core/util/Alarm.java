package freakrware.lt.app.core.util;

import freakrware.lt.app.core.receiver.CheckCoordinatesReceiver;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;

public class Alarm {

	private Context context;

	public Alarm(Context context) {
		this.context = context;
	}

	public void Start() {
		Intent i = new Intent(context, CheckCoordinatesReceiver.class);
		i.setAction("CheckCoordinatesReceiver");
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i,
				PendingIntent.FLAG_UPDATE_CURRENT);
		long interval =DateUtils.MINUTE_IN_MILLIS ;
		long firstStart = System.currentTimeMillis()
				+ (DateUtils.MINUTE_IN_MILLIS * 2);
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		am.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstStart, interval,
				pi);

	}

	public void Update(long interval) {

		Intent i = new Intent(context, CheckCoordinatesReceiver.class);
		i.setAction("CheckCoordinatesReceiver");
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		long firstStart = System.currentTimeMillis()
				+ (DateUtils.MINUTE_IN_MILLIS * 2);
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		am.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstStart, interval,
				pi);

	}

}
