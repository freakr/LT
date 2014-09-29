package freakrware.lt.app.resources;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import freakrware.lt.app.core.R;

public class TEF_Refresh implements Interfaces{
	
	private Activity mActivity;
	private FrameLayout fl;
	private View rootview;
	private String taskname;
	
	public void refresh(){
		this.mActivity = standard.mActivity;
		fl = (FrameLayout) rootview.findViewById(R.id.FL);
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
