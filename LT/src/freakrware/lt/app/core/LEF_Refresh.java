package freakrware.lt.app.core;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import freakrware.lt.app.resources.Interfaces;

public class LEF_Refresh implements Interfaces{
	
	private Activity mActivity;
	private RelativeLayout flmain;
	private View rootview;
	private String locationname;
//	private LayoutParams lparam09 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.9);
	private LayoutParams lparam1 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0);
	private LayoutParams lparam08 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.8);
	
	@SuppressLint("RtlHardcoded")
	public void refresh(){
		this.mActivity = standard.mActivity;
		flmain = (RelativeLayout) rootview.findViewById(R.id.FL);
		if(flmain.getChildCount() != 0){
			flmain.removeAllViews();
		}
		
		final LinearLayout llmain = standard.newlinlayout(mActivity,LinearLayout.VERTICAL);
		final LinearLayout lltasksblock = standard.newlinlayout(mActivity,LinearLayout.HORIZONTAL,Color.LTGRAY);
		
        final TextView tvlocationname = standard.newtextview(mActivity,locationname,30,Gravity.CENTER);
		
        final Spinner taskspinner = new Spinner(mActivity);
        final ArrayList<String> tasklist = db.get_tasks_arraylist();
		final ArrayAdapter<String> taskadapter = new ArrayAdapter<String>(mActivity,android.R.layout.simple_spinner_item, tasklist);
        taskspinner.setAdapter(taskadapter);
        int taskid = db.get_task_from_location(db.exists_location(locationname));
        if(taskid != 0)
        {
        	String taskname = db.get_task(taskid);
        	int selected = taskadapter.getPosition(taskname);
        	taskspinner.setSelection(selected);
        }
        taskspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int position, long id) {
				
					db.remove_Task_from_Location(db.exists_location(locationname));
					db.add_task_to_location(db.exists_location(locationname), db.exists_task(taskadapter.getItem(position)));
					
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        
        taskspinner.setLayoutParams(lparam1);
        lltasksblock.addView(taskspinner);
        tvlocationname.setLayoutParams(lparam1);
        llmain.addView(tvlocationname);
        lltasksblock.setLayoutParams(lparam08);
        llmain.addView(lltasksblock);
        
        
    	llmain.setLayoutParams(lparam1);
        flmain.addView(llmain);
		
	}

	public void set_rootview(View v, String locationname) {
		this.rootview = v;
		this.locationname = locationname;
		
	}
}
