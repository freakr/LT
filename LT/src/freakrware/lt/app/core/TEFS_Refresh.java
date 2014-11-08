package freakrware.lt.app.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.ToggleButton;
import freakrware.lt.app.core.R;
import freakrware.lt.app.core.util.DataBase;
import freakrware.lt.app.resources.Interfaces;

public class TEFS_Refresh implements Interfaces{
	
	private Activity mActivity;
	private View rootview;
	private String taskname;
//	private LayoutParams lparam09 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.9);
	private LayoutParams lparam1 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0);
	private LayoutParams lparam08 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.8);
	private RelativeLayout flmain;
	
	
	
	@SuppressLint("RtlHardcoded")
	public void refresh(){
		this.mActivity = standard.mActivity;
		flmain = (RelativeLayout) rootview.findViewById(R.id.FL);
		if(flmain.getChildCount() != 0){
			flmain.removeAllViews();
		}
	
		
		final LinearLayout llmain = standard.newlinlayout(mActivity,LinearLayout.VERTICAL);
		final LinearLayout llwifiblock = standard.newlinlayout(mActivity,LinearLayout.HORIZONTAL,Color.WHITE);
		final LinearLayout llwifibuttons = standard.newlinlayout(mActivity,LinearLayout.VERTICAL,Color.WHITE);
		final LinearLayout llsoundblock = standard.newlinlayout(mActivity,LinearLayout.HORIZONTAL,Color.WHITE);
		final LinearLayout llsoundbuttons = standard.newlinlayout(mActivity,LinearLayout.VERTICAL,Color.WHITE);
        
		final TextView tvfragname = standard.newtextview(mActivity,"Set Task-Standards",30,Gravity.CENTER);
		final TextView tvtaskname = standard.newtextview(mActivity,"from - "+taskname+" - to :",30,Gravity.CENTER);
		final TextView tvwifi = standard.newtextview(mActivity,"Wifi",20,Color.BLACK,Gravity.CENTER);
		final TextView tvsound = standard.newtextview(mActivity,"Sound",20,Color.BLACK,Gravity.CENTER);
		final int colortrue = Color.rgb(0, 255, 0);
		final int colorfalse = Color.rgb(255, 0, 0);
		
		final ToggleButton wifiinrange = standard.newtoggbutt(mActivity,"in Range","in Range ON","in Range OFF",20,Gravity.CENTER);
		wifiinrange.setChecked(db.get_taskstandards_data(db.exists_task(taskname), DataBase.DB_COL_12));
		standard.set_Color_ToggleButton(wifiinrange,colortrue,colorfalse);
		wifiinrange.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				db.edit_taskstandards_value(db.exists_task(taskname), DataBase.DB_COL_12,String.valueOf(arg1));
				standard.set_Color_ToggleButton(wifiinrange,colortrue,colorfalse);
			}
		});
        final ToggleButton wifioutofrange = standard.newtoggbutt(mActivity,"out of Range","out of Range ON","out of Range OFF",20,Gravity.CENTER);
        wifioutofrange.setChecked(db.get_taskstandards_data(db.exists_task(taskname), DataBase.DB_COL_13));
        standard.set_Color_ToggleButton(wifioutofrange,colortrue,colorfalse);
        wifioutofrange.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				db.edit_taskstandards_value(db.exists_task(taskname), DataBase.DB_COL_13,String.valueOf(arg1));
				standard.set_Color_ToggleButton(wifioutofrange,colortrue,colorfalse);
			}
		});
        final ToggleButton soundinrange = standard.newtoggbutt(mActivity,"in Range","in Range ON","in Range OFF",20,Gravity.CENTER);
        soundinrange.setChecked(db.get_taskstandards_data(db.exists_task(taskname), DataBase.DB_COL_14));
        standard.set_Color_ToggleButton(soundinrange,colortrue,colorfalse);
        soundinrange.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				db.edit_taskstandards_value(db.exists_task(taskname), DataBase.DB_COL_14,String.valueOf(arg1));
				standard.set_Color_ToggleButton(soundinrange,colortrue,colorfalse);
			}
		});
        final ToggleButton soundoutofrange = standard.newtoggbutt(mActivity,"out of Range","out of Range ON","out of Range OFF",20,Gravity.CENTER);
        soundoutofrange.setChecked(db.get_taskstandards_data(db.exists_task(taskname), DataBase.DB_COL_15));
        standard.set_Color_ToggleButton(soundoutofrange,colortrue,colorfalse);
        soundoutofrange.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				db.edit_taskstandards_value(db.exists_task(taskname), DataBase.DB_COL_15,String.valueOf(arg1));
				standard.set_Color_ToggleButton(soundoutofrange,colortrue,colorfalse);
			}
		});
        
        wifiinrange.setLayoutParams(lparam1);
        llwifibuttons.addView(wifiinrange);
        wifioutofrange.setLayoutParams(lparam1);
        llwifibuttons.addView(wifioutofrange);
        
        soundinrange.setLayoutParams(lparam1);
        llsoundbuttons.addView(soundinrange);
        soundoutofrange.setLayoutParams(lparam1);
        llsoundbuttons.addView(soundoutofrange);
        
        tvwifi.setLayoutParams(lparam1);
        llwifiblock.addView(tvwifi);
//        llmain.addView(standard.newdivider_vert(mActivity, 3, Color.BLACK));
        llwifibuttons.setLayoutParams(lparam08);
        llwifiblock.addView(llwifibuttons);
        
        tvsound.setLayoutParams(lparam1);
        llsoundblock.addView(tvsound);
//        llmain.addView(standard.newdivider_vert(mActivity, 3, Color.BLACK));
        llsoundbuttons.setLayoutParams(lparam08);
        llsoundblock.addView(llsoundbuttons);
        
        tvfragname.setLayoutParams(lparam1);
        llmain.addView(tvfragname);
        tvtaskname.setLayoutParams(lparam1);
        llmain.addView(tvtaskname);
        llmain.addView(standard.newdivider_hor(mActivity, 3, Color.CYAN));
        llwifiblock.setLayoutParams(lparam08);
        llmain.addView(llwifiblock);
        llmain.addView(standard.newdivider_hor(mActivity, 3, Color.BLACK));
        llsoundblock.setLayoutParams(lparam08);
        llmain.addView(llsoundblock);
        
    	llmain.setLayoutParams(lparam1);
        flmain.addView(llmain);
		
	}

	public void set_rootview(View v, String taskname) {
		this.rootview = v;
		this.taskname = taskname;
		
	}
}
