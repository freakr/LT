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
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import freakrware.lt.app.resources.Interfaces;


public class ASO_Refresh implements Interfaces{
	
	private Activity mActivity;
	private RelativeLayout fl;
	private View rootview;
	private String[] asos;
	private Dialog adialog;
	private Adult_Spy_Option aso = new Adult_Spy_Option();
	
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
        tv.setText("ASO's");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        trpositions.addView(tv);
        trpositions.setGravity(Gravity.CENTER);
        TableRow trnewtask = new TableRow(mActivity);
    	final Button bnewtask = new Button(mActivity);
    	bnewtask.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	bnewtask.setText("New ASO");
        bnewtask.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        bnewtask.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick( View v) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
				final EditText input = new EditText(mActivity);
				input.setInputType(InputType.TYPE_CLASS_TEXT);
				dialog.setView(input);
				dialog.setMessage("Name for this ASO") 
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										final String inhalt = input.getText().toString();
										if (db.exists_aso(inhalt) == 0) {
											
											AlertDialog.Builder dialog2 = new AlertDialog.Builder(mActivity);
											final EditText input2 = new EditText(mActivity);
											input2.setInputType(InputType.TYPE_CLASS_TEXT);
											dialog2.setView(input2);
											dialog2.setMessage("PhoneNr for this ASO") 
													.setPositiveButton("OK", new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface dialog,
																		int id) {
																	final String inhalt2 = input2.getText().toString();
																	if (db.exists_aso(inhalt) == 0) {
																		db.add_aso(inhalt);
																		db.add_aso_data(db.exists_aso(inhalt), 0, 0, 0, inhalt2);
																		
																		Toast.makeText(mActivity, inhalt2+" was added", Toast.LENGTH_LONG).show();
																		ASO_R.refresh();
																	}else{
																		Toast.makeText(mActivity, inhalt2+" exists ,pls take another Name", Toast.LENGTH_LONG).show();
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
											dialog2.show();
//											Toast.makeText(mActivity, inhalt+" was added", Toast.LENGTH_LONG).show();
//											ASO_R.refresh();
										}else{
											Toast.makeText(mActivity, inhalt+" exists ,pls take another Name", Toast.LENGTH_LONG).show();
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
        trnewtask.addView(bnewtask);
        tl.addView(trpositions);
        tl.addView(standard.newdivider_hor(mActivity, 3, Color.WHITE));
        tl.addView(trnewtask);
        tl.addView(standard.newdivider_hor(mActivity, 6, Color.WHITE));
		asos = db.get_ASOs();
        for(int x = 0;x < asos.length;x++){
        	TableRow trposis = new TableRow(mActivity);
        	final Button basos = new Button(mActivity);
        	basos.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        	basos.setText(asos[x]);
            basos.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            basos.setOnClickListener(new OnClickListener(){

    			@Override
    			public void onClick( View v) {
    				AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
    				adialog = new Dialog(mActivity);
    				
    				final LinearLayout ll1 = standard.newlinlayout(mActivity, LinearLayout.VERTICAL);
    				final Button bTSedit = new Button(mActivity);
    				bTSedit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	        	bTSedit.setText("Get Position");
    	            bTSedit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
    	            bTSedit.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							String[] asodata = db.get_aso_data(db.exists_aso(String.valueOf(basos.getText())));
							String phoneNr = asodata[3];
							
							aso.sms_Position_get(phoneNr);
							adialog.cancel();
						}
    	            	
    	            });
    	            final Button bTPedit = new Button(mActivity);
    				bTPedit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	        	bTPedit.setText("Show Last Position");
    	            bTPedit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
    	            bTPedit.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							String[] asodata = db.get_aso_data(db.exists_aso(String.valueOf(basos.getText())));
							String lati = asodata[0];
							String longi = asodata[1];
							String uri = String.format(Locale.ENGLISH,"geo:%s,%s?q=%s,%s(%s)", lati, longi,lati,longi,String.valueOf(basos.getText())+ " Postion");
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
							adialog.cancel();
						}
    	            	
    	            });
    	            final Button bdelete = new Button(mActivity);
    				bdelete.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	        	bdelete.setText("Delete");
    	            bdelete.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
    	            bdelete.setOnClickListener(new OnClickListener(){
    	            	
    	            	@Override
						public void onClick(View v) {
							
							sure_delete(basos);
							adialog.cancel();
							
						}
    	            	
    	            });
    	            ll1.addView(bTSedit);
    	            ll1.addView(bTPedit);
    	            ll1.addView(bdelete);
    	            dialog.setView(ll1);
    				dialog.setMessage("What to do ?"); 
    				adialog = dialog.show();
    			}
    		});
            
            basos.setGravity(Gravity.CENTER);
            trposis.addView(basos);
            trposis.setGravity(Gravity.CENTER);
            tl.addView(trposis);
            
       }
        sv.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT, 1));
        tl.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1));
    	sv.addView(tl);
        fl.addView(sv);
	}

	
	public void set_rootview(View v){
		this.rootview = v;
	}
	public void sure_delete(final Button basos) {
		
			DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			            switch (which){ 
			            case DialogInterface.BUTTON_POSITIVE:
			            	if (db.exists_aso(String.valueOf(basos.getText())) != 0) 
							{
								if(db.remove_aso(String.valueOf(basos.getText())))
								{
									Toast.makeText(mActivity, String.valueOf(basos.getText())+" was deleted !!", Toast.LENGTH_LONG).show();
									adialog.cancel();
									ASO_R.refresh();
								}
								else
								{
									Toast.makeText(mActivity, "Error , "+ String.valueOf(basos.getText())+" was not deleted !!", Toast.LENGTH_LONG).show();
									adialog.cancel();
								}
							}
							else
							{
								adialog.cancel();
							Toast.makeText(mActivity, String.valueOf(basos.getText())+" don't exists !!", Toast.LENGTH_LONG).show();
							}

			            	
			            	dialog.dismiss();
			                break; 
			            case DialogInterface.BUTTON_NEGATIVE: 

			            	dialog.dismiss();
			                break;
			            }
			            dialog.dismiss();
			    }
			};
			Dialog dia = standard.Adialog_Delete_Confirmation(mActivity, String.valueOf(basos.getText()), listener);
			dia.show();
			}
		
	
	
}


