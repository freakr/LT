package freakrware.lt.app.core;

import java.util.ArrayList;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;
import freakrware.lt.app.core.util.DataBase;
import freakrware.lt.app.resources.Interfaces;

public class TEFP_Refresh implements Interfaces{
	
	private Activity mActivity;
	private View rootview;
	private String taskname;
//	private LayoutParams lparam09 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.9);
	private LayoutParams lparam1 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 2.0);
	private LayoutParams lparam08 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.5);
	private RelativeLayout flmain;
	private ArrayList<PackageInfoStruct> apps;
	
	
	
	@SuppressLint("RtlHardcoded")
	public void refresh(){
		this.mActivity = standard.mActivity;
		flmain = (RelativeLayout) rootview.findViewById(R.id.FL);
		if(flmain.getChildCount() != 0){
			flmain.removeAllViews();
		}
		
		apps = standard.getPackages();
		
	
		
		final LinearLayout llmain = standard.newlinlayout(mActivity,LinearLayout.VERTICAL);
		final TextView tvfragname = standard.newtextview(mActivity,"Set Task-Porgramms",30,Gravity.CENTER);
		final TextView tvtaskname = standard.newtextview(mActivity,"from - "+taskname+" - to :",30,Gravity.CENTER);
		final LinearLayout llprogrammsblock = standard.newlinlayout(mActivity,LinearLayout.VERTICAL);
		final ScrollView svprogrammsblock = new ScrollView(mActivity);
		
		
		
		for(int x = 0 ; x < apps.size();x++)
		{
			String name = apps.get(x).appname;
			
			
			final LinearLayout appblock = standard.newlinlayout(mActivity,LinearLayout.HORIZONTAL);
			
			final TextView appname = standard.newtextview(mActivity,name,20,Gravity.START);
			final CheckBox appcheck = new CheckBox(mActivity);
			
			appname.setLayoutParams(lparam08);
			appblock.addView(appname);
			appcheck.setLayoutParams(lparam1);
			appblock.addView(appcheck);
			appblock.setLayoutParams(lparam1);
			llprogrammsblock.addView(appblock);
		}
		

		
		tvfragname.setLayoutParams(lparam1);
        llmain.addView(tvfragname); 
        tvtaskname.setLayoutParams(lparam1);
		llmain.addView(tvtaskname);
        llmain.addView(standard.newdivider_hor(mActivity, 3, Color.CYAN));
        
//        llprogrammsblock.setLayoutParams(lparam1);
        svprogrammsblock.addView(llprogrammsblock);
        svprogrammsblock.setLayoutParams(lparam08);
		llmain.addView(svprogrammsblock);
    	llmain.setLayoutParams(lparam1);
        flmain.addView(llmain);
		
	}

	public void set_rootview(View v, String taskname) {
		this.rootview = v;
		this.taskname = taskname;
		
	}
}