package freakrware.lt.app.core.receiver;

import freakrware.lt.app.core.util.SMS_Actions;
import freakrware.lt.app.resources.Interfaces;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver implements Interfaces{

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            standard.set_Context(context);
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                if(message.contains("LT-MESSAGE-SEND-POSITION"))
                {
                	String lati = db.get_setup_parameter(db.exists_parameter("LAST_POS_LATI"));
                	String longi = db.get_setup_parameter(db.exists_parameter("LAST_POS_LONGI"));
                	String lat = db.get_setup_parameter(db.exists_parameter("LAST_ACTION_TIME"));
                	String pos = "LT-MESSAGE-RECEIVE-POSITION;"  + lati + ";"+longi + ";"+lat;
                	standard.send_sms(sender,pos);
                	
                	abortBroadcast();
                }
                if(message.contains("LT-MESSAGE-RECEIVE-POSITION"))
                {
                	String[] subs = message.split(";");
                	String lati = subs[1];
                	String longi = subs[2];
                	String lat = subs[3];
                	
                	abortBroadcast();
                }
                
                // prevent any other broadcast receivers from receiving broadcast
                // abortBroadcast();
            }
        }
    }
}