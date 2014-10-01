package freakrware.lt.app.core;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
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
		fl.addView(tv);
	}

	public void set_rootview(View v, String taskname) {
		this.rootview = v;
		this.taskname = taskname;
		
	}
}
