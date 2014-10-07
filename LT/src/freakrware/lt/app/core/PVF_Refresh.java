package freakrware.lt.app.core;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import freakrware.lt.app.core.util.Coordinates;
import freakrware.lt.app.resources.Interfaces;

public class PVF_Refresh implements Interfaces{
	
	private Activity mActivity;
	private RelativeLayout fl;
	private View rootview;
	private String[] locs;
	private String[] locsshow;
	protected Dialog adialog;
	private TextView[] tvdistancearray;
	private ActualCoords acoord;
	private String[] names;
	
	public void refresh(){
		this.mActivity = standard.mActivity;
		fl = (RelativeLayout) rootview.findViewById(R.id.FL);
		if(fl.getChildCount() != 0){
			fl.removeAllViews();
		}
		ScrollView sv = new ScrollView(mActivity);
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
		tvdistancearray = new TextView[locs.length];
		names = new String[locs.length];
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
    				adialog = new Dialog(mActivity);
    				final LinearLayout ll = new LinearLayout(mActivity);
    				final Button bedit = new Button(mActivity);
    				bedit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	        	bedit.setText("Show Position");
    	            bedit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
    	            bedit.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							String uri = String.format(Locale.ENGLISH,"geo:%s,%s?q=%s,%s(%s)", lati, longi,lati,longi,name);
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
							try
							{
							mActivity.startActivity(intent);
							adialog.cancel();
							}
							catch(ActivityNotFoundException ex)
							{
							try
							{
							Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
							mActivity.startActivity(unrestrictedIntent);
							adialog.cancel();
							}
							catch(ActivityNotFoundException innerEx)
							{
							Toast.makeText(mActivity, "Please install a maps application", Toast.LENGTH_LONG).show();
							adialog.cancel();
							}
							adialog.cancel();
							}
							
						}
    	            	
    	            });
    	            final Button bdelete = new Button(mActivity);
    				bdelete.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	        	bdelete.setText("Delete");
    	            bdelete.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
    	            bdelete.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							if (db.exists_location(String.valueOf(blocs.getText())) != 0) {
								if(db.remove_Location(String.valueOf(blocs.getText()))){
									Toast.makeText(mActivity, String.valueOf(blocs.getText())+" was deleted !!", Toast.LENGTH_LONG).show();
									adialog.cancel();
									PVF_R.refresh();
								}else{
									Toast.makeText(mActivity, "Error , "+ String.valueOf(blocs.getText())+" was not deleted !!", Toast.LENGTH_LONG).show();
									adialog.cancel();
								}
							}else{
								adialog.cancel();
								Toast.makeText(mActivity, String.valueOf(blocs.getText())+" don't exists !!", Toast.LENGTH_LONG).show();
							}
							adialog.cancel();
						}
    	            	
    	            });
    				ll.addView(bdelete);
    	            ll.addView(bedit);
    	            dialog.setView(ll);
    				dialog.setMessage("What to do ?"); 
    				adialog = dialog.show();
    			}
    		});
            final TextView tvdistance = new TextView(mActivity);
            tvdistance.setId(x);
            Coordinates cord = new Coordinates(mActivity,5000);
            double distance = cord.get_distance(lati,longi);
            tvdistance.setText(String.valueOf(distance));
            tvdistance.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            tvdistance.setGravity(Gravity.CENTER);
            tvdistance.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
            blocs.setGravity(Gravity.CENTER);
            trposis.addView(blocs);
            trposis.addView(tvdistance);
            trposis.setGravity(Gravity.CENTER);
            tl.addView(trposis);
            names[x] = (String) blocs.getText();
            tvdistancearray[x] = tvdistance;
            
       }
        sv.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT, 1));
        tl.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1));
    	sv.addView(tl);
        fl.addView(sv);
        set_distance_views(names,tvdistancearray);
        
	}

	
	private void set_distance_views(String[] names, TextView[] tvda) {
		this.acoord = standard.get_ActualCoords();
		acoord.set_positions_views(names,tvda);
	}


	public void set_rootview(View v){
		this.rootview = v;
	}
    
	
	
}