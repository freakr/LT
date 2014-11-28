package freakrware.lt.app.core;

import android.content.Context;
import freakrware.lt.app.resources.Interfaces;

public class Adult_Spy_Option implements Interfaces{
	
	private Context context;

	public Adult_Spy_Option()
	{
		this.context = standard.context;
		
	}
	
	public void sms_Position_received(String sender,String[] subs){
		db.edit_setup_parameter_value(db.exists_parameter("LAST_POS_SENDER_RECEIVED"), sender);
		db.edit_setup_parameter_value(db.exists_parameter("LAST_POS_LATI_RECEIVED"), subs[1]);
		db.edit_setup_parameter_value(db.exists_parameter("LAST_POS_LONGI_RECEIVED"), subs[2]);
		db.edit_setup_parameter_value(db.exists_parameter("LAST_POS_ACTION_TIME_RECEIVED"), subs[3]);
	}

	public String sms_send_Position() {
		String position;
		String lati = db.get_setup_parameter(db.exists_parameter("LAST_POS_LATI"));
    	String longi = db.get_setup_parameter(db.exists_parameter("LAST_POS_LONGI"));
    	String lat = db.get_setup_parameter(db.exists_parameter("LAST_ACTION_TIME"));
		position = lati + ";"+longi + ";"+lat;
		
		return position;
	}
	public void sms_get_Position(String phoneNr)
	{
		standard.send_sms(phoneNr, "LT-MESSAGE-SEND-POSITION");
	}

}
