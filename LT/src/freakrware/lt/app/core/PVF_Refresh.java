package freakrware.lt.app.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import freakrware.lt.app.resources.Interfaces;

public class PVF_Refresh implements Interfaces{
	
	private Activity mActivity;
	private LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
	private FrameLayout fl;
	private View rootview;
	
	public void positions_refresh(){
		this.mActivity = standard.mActivity;
		fl = (FrameLayout) rootview.findViewById(R.id.FL);
		if(fl.getChildCount() != 0){
			fl.removeAllViews();
		}
		//lp.setMargins(5, 5, 5, 5);
		TableLayout tl = new TableLayout(mActivity);
        TableRow trpositions = new TableRow(mActivity);
        TextView tv = new TextView(mActivity);
        tv.setText("Positions");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        trpositions.addView(tv);
        trpositions.setGravity(Gravity.CENTER);
        TextView divider = new TextView(mActivity);
        divider.setBackgroundColor(Color.WHITE);
        divider.setHeight(5);
        tl.addView(trpositions);
        tl.addView(divider);
		String[] locs = db.get_locations();
        for(int x = 0;x < locs.length;x++){
        	TableRow trposis = new TableRow(mActivity);
        	LinearLayout ll = new LinearLayout(mActivity);
        	ll.setOrientation(LinearLayout.VERTICAL);
        	ll.setWeightSum(6f);
        	final Button blocs = new Button(mActivity);
        	blocs.setText(locs[x]);
            blocs.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            blocs.setOnClickListener(new OnClickListener(){

    			@Override
    			public void onClick( View v) {
    				AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
    				dialog.setMessage("Delete ?") 
    						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    									@Override
    									public void onClick(DialogInterface dialog,
    											int id) {
    										if (db.exists_location(String.valueOf(blocs.getText())) != 0) {
    											if(db.remove_Location(String.valueOf(blocs.getText()))){
    												Toast.makeText(mActivity, String.valueOf(blocs.getText())+" was deleted !!", Toast.LENGTH_LONG).show();
    												PVF_R.positions_refresh();
    											}else{
    												Toast.makeText(mActivity, "Error , "+ String.valueOf(blocs.getText())+" was not deleted !!", Toast.LENGTH_LONG).show();
    											}
    										}else{
    											Toast.makeText(mActivity, String.valueOf(blocs.getText())+" don't exists !!", Toast.LENGTH_LONG).show();
    											dialog.cancel();
    										}
    									}
    								})
    						.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
    									@Override
    									public void onClick(DialogInterface dialog,
    											int id) {
    									}
    								});
    				dialog.show();
    			}
    		});
            blocs.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            blocs.setGravity(Gravity.CENTER);
            ll.addView(blocs);
            ll.setGravity(Gravity.CENTER);
            trposis.addView(ll);
            trposis.setGravity(Gravity.CENTER);
            tl.addView(trposis);
            
            
        }
        fl.addView(tl);
	}

	
	public void set_rootview(View v){
		this.rootview = v;
	}
    
	
	
}