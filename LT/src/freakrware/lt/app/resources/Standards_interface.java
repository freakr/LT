package freakrware.lt.app.resources;

import freakrware.lt.app.core.ActualCoords;
import freakrware.lt.app.core.Task_Edit_Fragment;
import freakrware.lt.app.core.util.Coordinates;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public interface Standards_interface extends Fragment_interface{
    
	
	
	public class Standards{
		
		public Activity mActivity;
		public Coordinates ccoords;
		private ActualCoords acoord;
		private FragmentStatePagerAdapter mPagerAdapter;
		public Fragment TEF;
		
		public void ini_fragmentlist() {
			mFragmentList.add(LDF);
			mFragmentList.add(PVF);
			mFragmentList.add(TVF);
		}
		public void fragmentswitch(int pos,Fragment switchto) {
			mFragmentList.set(pos, switchto);
			mPagerAdapter.notifyDataSetChanged();
			this.TEF = null;
		}
		public void fragmentswitch_to_new(int pos,String switchto, String task) {
			switch(switchto){
			case TEFFRAGMENT:
				Fragment TEF = new Task_Edit_Fragment();
				Bundle args = new Bundle();
				args.putString(TEFFRAGMENT, task);
				TEF.setArguments(args);
				mFragmentList.set(pos, TEF);
				mPagerAdapter.notifyDataSetChanged();
				this.TEF = TEF;
			}
		}
		
		public boolean is_Wifi_active(){
			WifiManager wifiManager = (WifiManager) mActivity.getBaseContext().getSystemService(Context.WIFI_SERVICE);
			return wifiManager.isWifiEnabled();
		}
		public boolean Wifi_enable(){
			WifiManager wifiManager = (WifiManager) mActivity.getBaseContext().getSystemService(Context.WIFI_SERVICE);
			return wifiManager.setWifiEnabled(true);
		}
		public boolean Wifi_disable(){
			WifiManager wifiManager = (WifiManager) mActivity.getBaseContext().getSystemService(Context.WIFI_SERVICE);
			return wifiManager.setWifiEnabled(false);
		}
		
		public boolean is_Sound_active(){
			AudioManager myAudioManager = (AudioManager)mActivity.getSystemService(Context.AUDIO_SERVICE);
			int ringermode = myAudioManager.getRingerMode();
			if(ringermode == AudioManager.RINGER_MODE_NORMAL){
				return true;
			}else{
				return false;
			}
		}
		public void Sound_normal(){
			AudioManager myAudioManager = (AudioManager)mActivity.getSystemService(Context.AUDIO_SERVICE); 
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		public void Sound_silent(){
			AudioManager myAudioManager = (AudioManager)mActivity.getSystemService(Context.AUDIO_SERVICE); 
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		public void Sound_vibrate(){
			AudioManager myAudioManager = (AudioManager)mActivity.getSystemService(Context.AUDIO_SERVICE); 
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		
		public void wait(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		public void thread_rename(String name){
			Thread t = Thread.currentThread();
			t.setName(name + " - Thread");
	
		}
		public void set_Activity(Activity activity){
			this.mActivity = activity;
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
		public ToggleButton newtoggbutt(Activity activity, String text,String texton, String textoff, int textsize, int gravity) {
			ToggleButton tb = new ToggleButton(activity);
	        tb.setText(text);
	        tb.setTextOn(texton);
	        tb.setTextOff(textoff);
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

}
}
