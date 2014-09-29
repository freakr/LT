package freakrware.lt.app.core;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
	private String[] locs;
	private String[] locsshow;
	
	public void refresh(){
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
		locs = db.get_locations();
        for(int x = 0;x < locs.length;x++){
        	final String name = locs[x];
        	TableRow trposis = new TableRow(mActivity);
        	locsshow = db.get_locations_data(db.exists_location(name));
        	final String lati = String.valueOf(locsshow[0]);
			final String longi = String.valueOf(locsshow[1]);
        	final Button blocs = new Button(mActivity);
        	blocs.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
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
    												PVF_R.refresh();
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
            final Button blocsshow = new Button(mActivity);
        	blocsshow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        	blocsshow.setText("Show Position");
            blocsshow.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            blocsshow.setOnClickListener(new OnClickListener(){

    			@Override
    			public void onClick(View v) {
    				String uri = String.format(Locale.ENGLISH,"geo:%s,%s?q=%s,%s(%s)", lati, longi,lati,longi,name);
    				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    				try
    		        {
    		            mActivity.startActivity(intent);
    		        }
    		        catch(ActivityNotFoundException ex)
    		        {
    		            try
    		            {
    		                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    		                mActivity.startActivity(unrestrictedIntent);
    		            }
    		            catch(ActivityNotFoundException innerEx)
    		            {
    		                Toast.makeText(mActivity, "Please install a maps application", Toast.LENGTH_LONG).show();
    		            }
    		        }
    			}
    			
    		});
            blocs.setGravity(Gravity.CENTER);
            trposis.addView(blocs);
            blocsshow.setGravity(Gravity.CENTER);
            trposis.addView(blocsshow);
            trposis.setGravity(Gravity.CENTER);
            tl.addView(trposis);
            
       }
        fl.addView(tl);
	}

	
	public void set_rootview(View v){
		this.rootview = v;
	}
    
	
	
}