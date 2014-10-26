package freakrware.lt.app.resources;

import freakrware.lt.app.core.ActualCoords;
import freakrware.lt.app.core.Location_Edit_Fragment;
import freakrware.lt.app.core.Task_Edit_Fragment;
import freakrware.lt.app.core.util.Coordinates;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.LinearLayout.LayoutParams;

public interface Standards_interface extends Fragment_interface{
    
	
	
	public class Standards{
		
		public Activity mActivity;
		public Coordinates ccoords;
		private ActualCoords acoord;
		private FragmentStatePagerAdapter mPagerAdapter;
		public Fragment TEF;
		public Fragment LEF;
		private Context context;
		private Dialog adialog;
		
		public void ini_fragmentlist() {
			mFragmentList.add(LDF);
			mFragmentList.add(PVF);
			mFragmentList.add(TVF);
		}
		public void fragmentswitch(int pos,Fragment switchto) {
			mFragmentList.set(pos, switchto);
			mPagerAdapter.notifyDataSetChanged();
			this.TEF = null;
			this.LEF = null;
		}
		public void fragmentswitch_to_new(int pos,String switchto, String arg1) {
			switch(switchto){
			case TEFFRAGMENT:
				Fragment TEF = new Task_Edit_Fragment();
				Bundle args = new Bundle();
				args.putString(TEFFRAGMENT, arg1);
				TEF.setArguments(args);
				mFragmentList.set(pos, TEF);
				mPagerAdapter.notifyDataSetChanged();
				this.TEF = TEF;
				break;
			case LEFFRAGMENT:
				Fragment LEF = new Location_Edit_Fragment();
				Bundle args1 = new Bundle();
				args1.putString(LEFFRAGMENT, arg1);
				LEF.setArguments(args1);
				mFragmentList.set(pos, LEF);
				mPagerAdapter.notifyDataSetChanged();
				this.LEF = LEF;
				break;
			}
		}
		
		public boolean is_Wifi_active(){
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			return wifiManager.isWifiEnabled();
		}
		public boolean Wifi_enable(){
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			return wifiManager.setWifiEnabled(true);
		}
		public boolean Wifi_disable(){
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			return wifiManager.setWifiEnabled(false);
		}
		
		public boolean is_Sound_active(){
			AudioManager myAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
			int ringermode = myAudioManager.getRingerMode();
			if(ringermode == AudioManager.RINGER_MODE_NORMAL || ringermode == AudioManager.RINGER_MODE_SILENT){
				return true;
			}else{
				return false;
			}
		}
		public void Sound_normal(){
			AudioManager myAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE); 
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		public void Sound_silent(){
			AudioManager myAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE); 
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		public void Sound_vibrate(){
			AudioManager myAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE); 
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		
		public void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		public void wait(Thread thread,int time){
			try {
				thread.wait(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		
		public Thread getThread( final String name ) {
		    if ( name == null )
		        throw new NullPointerException( "Null name" );
		    final Thread[] threads = getAllThreads( );
		    for ( Thread thread : threads )
		        if ( thread.getName( ).equals( name ) )
		            return thread;
		    return null;
		}
		
		public Thread[] getAllThreads( ) {
		    final ThreadGroup root = Thread.currentThread().getThreadGroup();
		    Thread[] threads = new Thread[ root.activeCount() ];
		    root.enumerate( threads, true);
		    
		    return threads;
		   
		}
		
		public void thread_rename(String name){
			Thread t = Thread.currentThread();
			t.setName(name + " - Thread");
	
		}
		public void set_Activity(Activity activity){
			this.mActivity = activity;
			this.context = this.mActivity.getBaseContext();
		}
		public void ini_Ccoords(){
			this.ccoords = new Coordinates(mActivity,5000);
		}
		public void set_ActualCoords(ActualCoords acoord) {
			this.acoord = acoord;
		}
		public ActualCoords get_ActualCoords() {
			return acoord;
		}
		public void set_mPagerAdapter(FragmentStatePagerAdapter mPagerAdapter) {
			this.mPagerAdapter = mPagerAdapter;
			
		}
		public LinearLayout newlinlayout(Activity activity, int orientation) {
			LinearLayout ll = new LinearLayout(activity);
			ll.setOrientation(orientation);
			return ll;
		}
		public LinearLayout newlinlayout(Activity activity, int orientation,int backcolor) {
			LinearLayout ll = new LinearLayout(activity);
			ll.setOrientation(orientation);
			ll.setBackgroundColor(backcolor);
			return ll;
		}
		public TextView newtextview(Activity activity, String text, int textsize, int color, int gravity) {
			TextView tv = new TextView(activity);
	        tv.setText(text);
	        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
	        tv.setTextColor(color);
	        tv.setGravity(gravity);
	        return tv;
		}
		public TextView newtextview(Activity activity, String text, int textsize, int gravity) {
			TextView tv = new TextView(activity);
	        tv.setText(text);
	        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
	        tv.setGravity(gravity);
	        return tv;
		}
		
		public View newdivider_hor(Activity activity, int size, int color) {
			View divider = new View(activity);
	        divider.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, size));
	        divider.setBackgroundColor(color);
			return divider;
		}
		
		public View newdivider_vert(Activity activity, int size, int color) {
			View divider = new View(activity);
	        divider.setLayoutParams(new LayoutParams(size,LinearLayout.LayoutParams.MATCH_PARENT ));
	        divider.setBackgroundColor(color);
			return divider;
		}
		
		public ToggleButton newtoggbutt(Activity activity, String text,String texton, String textoff, int textsize, int gravity) {
			ToggleButton tb = new ToggleButton(activity);
	        tb.setText(text);
	        tb.setTextOn(texton);
	        tb.setTextOff(textoff);
	        tb.setPadding(10, 10, 10, 10);
	        tb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
	        tb.setGravity(gravity);
			return tb;
		}
		public Button newbutton(Activity activity, String text, int textsize) {
			final Button b = new Button(activity);
			b.setText(text);
	    	b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
	        return b;
		}
		public void set_Context(Context context) {
			this.context = context;
			
		}
		public void set_Color_ToggleButton(ToggleButton tb,int colortrue, int colorfalse) {
			if(tb.isChecked())
			{
				tb.setTextColor(Color.BLACK);
				tb.setBackgroundColor(colortrue);
			}
			else
			{
				tb.setTextColor(Color.WHITE);
				tb.setBackgroundColor(colorfalse);
			}
		}
		public Dialog Adialog_Delete_Confirmation(Activity activity, String item, OnClickListener listener){
			adialog = new Dialog(activity);
			AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
			dialog.setMessage("Really Delete -"+item+" ?")
				.setPositiveButton("OK", listener)

				.setNegativeButton("Cancel", listener)
				.create();
			
			adialog = dialog.show();
			return adialog;
		}
		
		

}
}
