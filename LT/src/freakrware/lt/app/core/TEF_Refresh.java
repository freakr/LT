package freakrware.lt.app.core;

import com.google.android.gms.tagmanager.Container;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;
import freakrware.lt.app.core.R;
import freakrware.lt.app.resources.Interfaces;

public class TEF_Refresh implements Interfaces{
	
	private Activity mActivity;
	private RelativeLayout fl;
	private View rootview;
	private String taskname;
	private LayoutParams lparam09 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.9);
	private LayoutParams lparam1 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0);
	private LayoutParams lparam08 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.8);
	
	@SuppressLint("RtlHardcoded")
	public void refresh(){
		this.mActivity = standard.mActivity;
		fl = (RelativeLayout) rootview.findViewById(R.id.FL);
		if(fl.getChildCount() != 0){
			fl.removeAllViews();
		}
		LinearLayout ll = new LinearLayout(mActivity);
		ll.setOrientation(LinearLayout.VERTICAL);
		TextView tvname = new TextView(mActivity);
		tvname.setLayoutParams(lparam1);
        tvname.setText(taskname);
        tvname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        tvname.setGravity(Gravity.CENTER);
        ll.addView(tvname);
       
        LinearLayout ll1 = new LinearLayout(mActivity);
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setLayoutParams(lparam09);
        ll1.setBackgroundColor(Color.LTGRAY);
		TextView tvwifi = new TextView(mActivity);
		tvwifi.setLayoutParams(lparam1);
        tvwifi.setText("Wifi");
        tvwifi.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tvwifi.setTextColor(Color.BLACK);
        tvwifi.setGravity(Gravity.CENTER);
        ll1.addView(tvwifi);
        LinearLayout ll2 = new LinearLayout(mActivity);
        ll2.setOrientation(LinearLayout.VERTICAL);
        ll2.setLayoutParams(lparam09);
        ll2.setBackgroundColor(Color.WHITE);
        final ToggleButton wifiinrange = new ToggleButton(mActivity);
        wifiinrange.setLayoutParams(lparam09);
//        wifiinrange.setBackgroundColor(Color.DKGRAY);
//        wifiinrange.setTextColor(Color.WHITE);
        wifiinrange.setText("in Range");
        wifiinrange.setTextOn("in Range ON");
        wifiinrange.setTextOff("in Range OFF");
        wifiinrange.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        wifiinrange.setGravity(Gravity.CENTER);
        ll2.addView(wifiinrange);
        final ToggleButton wifioutofrange = new ToggleButton(mActivity);
        wifioutofrange.setLayoutParams(lparam09);
//        wifioutofrange.setBackgroundColor(Color.DKGRAY);
//        wifioutofrange.setTextColor(Color.WHITE);
        wifioutofrange.setText("out of Range");
        wifioutofrange.setTextOn("out of Range ON");
        wifioutofrange.setTextOff("out of Range OFF");
        wifioutofrange.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        wifioutofrange.setGravity(Gravity.CENTER);
        ll2.addView(wifioutofrange);
        ll1.addView(ll2);
        ll.addView(ll1);
        
        LinearLayout ll3 = new LinearLayout(mActivity);
        ll3.setOrientation(LinearLayout.HORIZONTAL);
        ll3.setLayoutParams(lparam09);
        ll3.setBackgroundColor(Color.LTGRAY);
		TextView tvsound = new TextView(mActivity);
		tvsound.setLayoutParams(lparam1);
        tvsound.setText("Sound");
        tvsound.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tvsound.setTextColor(Color.BLACK);
        tvsound.setGravity(Gravity.CENTER);
        ll3.addView(tvsound);
        LinearLayout ll4 = new LinearLayout(mActivity);
        ll4.setOrientation(LinearLayout.VERTICAL);
        ll4.setLayoutParams(lparam09);
        ll4.setBackgroundColor(Color.WHITE);
        final ToggleButton soundinrange = new ToggleButton(mActivity);
        soundinrange.setLayoutParams(lparam09);
//        soundinrange.setBackgroundColor(Color.DKGRAY);
//        soundinrange.setTextColor(Color.WHITE);
        soundinrange.setText("in Range");
        soundinrange.setTextOn("in Range ON");
        soundinrange.setTextOff("in Range OFF");
        soundinrange.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        soundinrange.setGravity(Gravity.CENTER);
        ll4.addView(soundinrange);
        final ToggleButton soundoutofrange = new ToggleButton(mActivity);
        soundoutofrange.setLayoutParams(lparam09);
//        soundoutofrange.setBackgroundColor(Color.DKGRAY);
//        soundoutofrange.setTextColor(Color.WHITE);
        soundoutofrange.setText("out of Range");
        soundoutofrange.setTextOn("out of Range ON");
        soundoutofrange.setTextOff("out of Range OFF");
        soundoutofrange.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        soundoutofrange.setGravity(Gravity.CENTER);
        ll4.addView(soundoutofrange);
        ll3.addView(ll4);
        ll.addView(ll3);
        
        
        final Button back = new Button(mActivity);
		back.setLayoutParams(lparam1);
		back.setText("Back");
    	back.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				standard.fragmentswitch(2,TVF);
			}
        	
        });
        ll.setLayoutParams(lparam09);
    	ll.addView(back);
        fl.addView(ll);
		
	}

	public void set_rootview(View v, String taskname) {
		this.rootview = v;
		this.taskname = taskname;
		
	}
}
