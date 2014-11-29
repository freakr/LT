package freakrware.lt.app.resources;

import java.util.ArrayList;
import java.util.List;

import freakrware.lt.app.core.ASO_Fragment;
import freakrware.lt.app.core.ActualCoords;
import freakrware.lt.app.core.Location_Edit_Fragment;
import freakrware.lt.app.core.Setup_Edit_Fragment;
import freakrware.lt.app.core.Task_Edit_P_Fragment;
import freakrware.lt.app.core.Task_Edit_S_Fragment;
import freakrware.lt.app.core.util.Coordinates;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.telephony.SmsManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.LinearLayout.LayoutParams;

public interface Standards_interface extends Fragment_interface {
	/* This class is for storing the data for each application */
	public class PackageInfoStruct {
		public String appname = "";
		public String pname = "";
		public String versionName = "";
		public int versionCode = 0;
		public Drawable icon;
		public String packageName = "";
	}

	public class Standards {
		public Activity mActivity;
		public Coordinates ccoords;
		private ActualCoords acoord;
		private FragmentStatePagerAdapter mPagerAdapter;
		public Fragment TEFS;
		public Fragment TEFP;
		public Fragment LEF;
		public Fragment SEF;
		public Fragment ASO;
		public Context context;
		private Dialog adialog;
		public String[] names;
		public TextView[] views;
		private String[] app_labels;

		public ArrayList<PackageInfoStruct> getPackages() {
			ArrayList<PackageInfoStruct> apps = getInstalledApps(false);
			final int max = apps.size();
			for (int i = 0; i < max; i++) {
				apps.get(i);
			}
			return apps;
		}

		private ArrayList<PackageInfoStruct> getInstalledApps(
				boolean getSysPackages) {
			ArrayList<PackageInfoStruct> res = new ArrayList<PackageInfoStruct>();
			List<PackageInfo> packs = context.getPackageManager()
					.getInstalledPackages(0);
			try {
				app_labels = new String[packs.size()];
			} catch (Exception e) {
				Toast.makeText(context.getApplicationContext(), e.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
			for (int i = 0; i < packs.size(); i++) {
				PackageInfo p = packs.get(i);
				if ((packs.get(i).applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
					continue;
				}
				// if ((!getSysPackages) && (p.versionName == null)) {
				// continue ;
				// }
				PackageInfoStruct newInfo = new PackageInfoStruct();
				newInfo.appname = p.applicationInfo.loadLabel(
						context.getPackageManager()).toString();
				newInfo.pname = p.packageName;
				newInfo.versionName = p.versionName;
				newInfo.versionCode = p.versionCode;
				newInfo.icon = p.applicationInfo.loadIcon(context
						.getPackageManager());
				newInfo.packageName = p.packageName;
				res.add(newInfo);
				app_labels[i] = newInfo.appname;
			}
			return res;
		}

		public void send_sms(String phoneNo, String sms) {

			try {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo, null, sms, null, null);
				Toast.makeText(context, "SMS Sent!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(context, "SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}

		}

		public void ini_fragmentlist() {
			mFragmentList.add(LDF);
			mFragmentList.add(LVF);
			mFragmentList.add(TVF);
		}

		public void fragmentswitch(int pos, Fragment switchto) {
			mFragmentList.set(pos, switchto);
			mPagerAdapter.notifyDataSetChanged();
			this.TEFS = null;
			this.TEFP = null;
			this.LEF = null;
			this.SEF = null;
			this.ASO = null;
		}

		public void fragmentswitch_to_new(int pos, String switchto, String arg1) {
			switch (switchto) {
			case TEFSFRAGMENT:
				Fragment TEFS = new Task_Edit_S_Fragment();
				Bundle args = new Bundle();
				args.putString(TEFSFRAGMENT, arg1);
				TEFS.setArguments(args);
				mFragmentList.set(pos, TEFS);
				mPagerAdapter.notifyDataSetChanged();
				this.TEFS = TEFS;
				break;
			case TEFPFRAGMENT:
				Fragment TEFP = new Task_Edit_P_Fragment();
				Bundle args3 = new Bundle();
				args3.putString(TEFPFRAGMENT, arg1);
				TEFP.setArguments(args3);
				mFragmentList.set(pos, TEFP);
				mPagerAdapter.notifyDataSetChanged();
				this.TEFP = TEFP;
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
			case SEFFRAGMENT:
				Fragment SEF = new Setup_Edit_Fragment();
				Bundle args2 = new Bundle();
				args2.putString(SEFFRAGMENT, arg1);
				SEF.setArguments(args2);
				mFragmentList.set(pos, SEF);
				mPagerAdapter.notifyDataSetChanged();
				this.SEF = SEF;
				break;
			case ASOFRAGMENT:
				Fragment ASO = new ASO_Fragment();
				Bundle args4 = new Bundle();
				args4.putString(ASOFRAGMENT, arg1);
				ASO.setArguments(args4);
				mFragmentList.set(pos, ASO);
				mPagerAdapter.notifyDataSetChanged();
				this.ASO = ASO;
				break;
			}
		}

		public boolean is_Wifi_active() {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			return wifiManager.isWifiEnabled();
		}

		public boolean Wifi_enable() {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			return wifiManager.setWifiEnabled(true);
		}

		public boolean Wifi_disable() {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			return wifiManager.setWifiEnabled(false);
		}

		public boolean is_Sound_active() {
			AudioManager myAudioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			int ringermode = myAudioManager.getRingerMode();
			if (ringermode == AudioManager.RINGER_MODE_NORMAL
					|| ringermode == AudioManager.RINGER_MODE_SILENT) {
				return true;
			} else {
				return false;
			}
		}

		public void Sound_normal() {
			AudioManager myAudioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}

		public void Sound_silent() {
			AudioManager myAudioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}

		public void Sound_vibrate() {
			AudioManager myAudioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}

		public void sleep(int time) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void wait(Thread thread, int time) {
			try {
				thread.wait(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public Thread getThread(final String name) {
			if (name == null)
				throw new NullPointerException("Null name");
			final Thread[] threads = getAllThreads();
			for (Thread thread : threads)
				if (thread.getName().equals(name))
					return thread;
			return null;
		}

		public Thread[] getAllThreads() {
			final ThreadGroup root = Thread.currentThread().getThreadGroup();
			Thread[] threads = new Thread[root.activeCount()];
			root.enumerate(threads, true);
			return threads;
		}

		public void thread_rename(String name) {
			Thread t = Thread.currentThread();
			t.setName(name + " - Thread");
		}

		public void set_Activity(Activity activity) {
			this.mActivity = activity;
			this.context = this.mActivity.getBaseContext();
		}

		public void ini_Ccoords() {
			this.ccoords = new Coordinates(mActivity, 5000);
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

		public LinearLayout newlinlayout(Activity activity, int orientation,
				int backcolor) {
			LinearLayout ll = new LinearLayout(activity);
			ll.setOrientation(orientation);
			ll.setBackgroundColor(backcolor);
			return ll;
		}

		public TextView newtextview(Activity activity, String text,
				int textsize, int color, int gravity) {
			TextView tv = new TextView(activity);
			tv.setText(text);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
			tv.setTextColor(color);
			tv.setGravity(gravity);
			return tv;
		}

		public TextView newtextview(Activity activity, String text,
				int textsize, int gravity) {
			TextView tv = new TextView(activity);
			tv.setText(text);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
			tv.setGravity(gravity);
			return tv;
		}

		public View newdivider_hor(Activity activity, int size, int color) {
			View divider = new View(activity);
			divider.setLayoutParams(new LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, size));
			divider.setBackgroundColor(color);
			return divider;
		}

		public View newdivider_vert(Activity activity, int size, int color) {
			View divider = new View(activity);
			divider.setLayoutParams(new LayoutParams(size,
					LinearLayout.LayoutParams.MATCH_PARENT));
			divider.setBackgroundColor(color);
			return divider;
		}

		public ToggleButton newtoggbutt(Activity activity, String text,
				String texton, String textoff, int textsize, int gravity) {
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

		public void set_Color_ToggleButton(ToggleButton tb, int colortrue,
				int colorfalse) {
			if (tb.isChecked()) {
				tb.setTextColor(Color.BLACK);
				tb.setBackgroundColor(colortrue);
			} else {
				tb.setTextColor(Color.WHITE);
				tb.setBackgroundColor(colorfalse);
			}
		}

		public Dialog Adialog_Delete_Confirmation(Activity activity,
				String item, OnClickListener listener) {
			adialog = new Dialog(activity);
			AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
			dialog.setMessage("Really Delete -" + item + " ?")
					.setPositiveButton("OK", listener)
					.setNegativeButton("Cancel", listener).create();
			adialog = dialog.show();
			return adialog;
		}

		public void set_positions_views(String[] names, TextView[] tvda) {
			this.names = names;
			this.views = tvda;
		}
	}
}
