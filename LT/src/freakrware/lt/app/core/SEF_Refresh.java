package freakrware.lt.app.core;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import freakrware.lt.app.core.R;
import freakrware.lt.app.resources.Interfaces;

public class SEF_Refresh implements Interfaces{
	
	private static final int NPICKER_1000 = 1000;
	private static final int NPICKER_100 = 100;
	private static final int NPICKER_10 = 10;
	private static final int NPICKER_1 = 1;
	private Activity mActivity;
	private RelativeLayout flmain;
	private View rootview;
	private LayoutParams lparam = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.2);
	private LayoutParams lparam1 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0);
	private LayoutParams lparam08 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.8);
	
	public void refresh(){
		this.mActivity = standard.mActivity;
		flmain = (RelativeLayout) rootview.findViewById(R.id.FL);
		if(flmain.getChildCount() != 0){
			flmain.removeAllViews();
		}
		final int green = Color.rgb(0, 255, 0);
		final int red = Color.rgb(255, 0, 0);
		final LinearLayout llmain = standard.newlinlayout(mActivity,LinearLayout.VERTICAL);
		final LinearLayout llrangeblock = standard.newlinlayout(mActivity,LinearLayout.VERTICAL,Color.BLACK);
		final LinearLayout llrangenumberpickers = standard.newlinlayout(mActivity,LinearLayout.HORIZONTAL,Color.BLACK);
		
		final TextView tvfragname = standard.newtextview(mActivity,"Setup",30,red,Gravity.CENTER);
		final TextView tvpararange = standard.newtextview(mActivity,"Range",20,green,Gravity.CENTER);
		final TextView tvmeter = standard.newtextview(mActivity,"m",20,green,Gravity.CENTER);

		
		String range = db.get_setup_parameter(db.exists_parameter("RANGE"));
		
		int values[] = {0,0,0,0}; 
		
		for(int x = range.length()-1;x > 0;x--)
		{
			char ch = range.charAt(x);
			String st = String.valueOf(ch);
			values[x+(values.length-range.length())] = Integer.parseInt(st);
		}
		
			
		final NumberPicker npicker1000 = new NumberPicker(mActivity);
		npicker1000.setId(NPICKER_1000);
		npicker1000.setMaxValue(9);
		npicker1000.setMinValue(0);
		npicker1000.setValue(values[0]);
		final NumberPicker npicker100 = new NumberPicker(mActivity);
		npicker100.setId(NPICKER_100);
		npicker100.setMaxValue(9);
		npicker100.setMinValue(0);
		npicker100.setValue(values[1]);
		final NumberPicker npicker10 = new NumberPicker(mActivity);
		npicker10.setId(NPICKER_10);
		npicker10.setMaxValue(9);
		npicker10.setMinValue(0);
		npicker10.setValue(values[2]);
		final NumberPicker npicker1 = new NumberPicker(mActivity);
		npicker1.setId(NPICKER_1);
		npicker1.setMaxValue(9);
		npicker1.setMinValue(0);
		npicker1.setValue(values[3]);
		
		OnValueChangeListener onValueChangedListener = new OnValueChangeListener(){

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,int newVal) {
				
				int range = (npicker1000.getValue()*NPICKER_1000)+
							(npicker100.getValue()*NPICKER_100)+
							(npicker10.getValue()*NPICKER_10)+
							(npicker1.getValue()*NPICKER_1);
				
				db.edit_setup_parameter_value(db.exists_parameter("RANGE"), String.valueOf(range));
				
			}
			
		};
		

		npicker1000.setOnValueChangedListener(onValueChangedListener);
		npicker100.setOnValueChangedListener(onValueChangedListener);
		npicker10.setOnValueChangedListener(onValueChangedListener);
		npicker1.setOnValueChangedListener(onValueChangedListener);
		
		
		
		llrangenumberpickers.addView(npicker1000);
		llrangenumberpickers.addView(npicker100);
		llrangenumberpickers.addView(npicker10);
		llrangenumberpickers.addView(npicker1);
		llrangenumberpickers.addView(tvmeter);
        tvpararange.setLayoutParams(lparam1);
        llrangeblock.addView(tvpararange);
        llrangenumberpickers.setLayoutParams(lparam);
        llrangeblock.addView(llrangenumberpickers);
        
        tvfragname.setLayoutParams(lparam1);
        llmain.addView(tvfragname);
        llmain.addView(standard.newdivider_hor(mActivity, 3, red));
        llrangeblock.setLayoutParams(lparam);
        llmain.addView(llrangeblock);
        
    	llmain.setLayoutParams(lparam1);
        flmain.addView(llmain);
		
	}

	public void set_rootview(View v) {
		this.rootview = v;
		
	}
}
