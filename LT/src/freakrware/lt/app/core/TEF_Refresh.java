package freakrware.lt.app.core;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import freakrware.lt.app.core.R;
import freakrware.lt.app.resources.Interfaces;

public class TEF_Refresh implements Interfaces{
	
	private Activity mActivity;
	private RelativeLayout fl;
	private View rootview;
	private String taskname;
	
	public void refresh(){
		this.mActivity = standard.mActivity;
		fl = (RelativeLayout) rootview.findViewById(R.id.FL);
		if(fl.getChildCount() != 0){
			fl.removeAllViews();
		}
		TextView tv = new TextView(mActivity);
		tv.setText(taskname);
		tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        final Button back = new Button(mActivity);
		back.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
		back.setText("Back");
    	back.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				standard.fragmentswitch(2,TVF);
			}
        	
        });
        fl.addView(back);
		fl.addView(tv);
	}

	public void set_rootview(View v, String taskname) {
		this.rootview = v;
		this.taskname = taskname;
		
	}
}
