package freakrware.lt.app.resources;

import freakrware.lt.app.core.ActualCoords;
import freakrware.lt.app.core.R;
import freakrware.lt.app.core.util.Coordinates;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;

public interface Standards_interface {

	public class Standards{
		
		public Activity mActivity;
		public Coordinates ccoords;
		private ActualCoords acoord; 
		
		@SuppressLint("NewApi")
		public void switch_fragments(Fragment ffrom,android.app.Fragment fto){
			android.app.FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();

			transaction.replace(ffrom.getId(), fto);
			transaction.addToBackStack(null);

			transaction.commit();
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
			this.ccoords = new Coordinates(mActivity);
		}
		public void set_ActualCoords(ActualCoords acoord) {
			this.acoord = acoord;
		}
		public ActualCoords get_ActualCoords() {
			return acoord;
		}
	}
}
