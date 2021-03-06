package freakrware.lt.app.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
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

public class TVF_Refresh implements Interfaces{
	
	private Activity mActivity;
	private RelativeLayout fl;
	private View rootview;
	private String[] tasks;
	private Dialog adialog;
	
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
        tv.setText("Tasks");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        trpositions.addView(tv);
        trpositions.setGravity(Gravity.CENTER);
        TableRow trnewtask = new TableRow(mActivity);
    	final Button bnewtask = new Button(mActivity);
    	bnewtask.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	bnewtask.setText("New Task");
        bnewtask.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        bnewtask.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick( View v) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
				final EditText input = new EditText(mActivity);
				input.setInputType(InputType.TYPE_CLASS_TEXT);
				dialog.setView(input);
				dialog.setMessage("Name for this Task") 
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										String inhalt = input.getText().toString();
										if (db.exists_task(inhalt) == 0) {
											db.add_task(inhalt);
											db.add_taskstandards(db.exists_task(inhalt));
											db.add_taskstate(db.exists_task(inhalt));
											Toast.makeText(mActivity, inhalt+" was added", Toast.LENGTH_LONG).show();
											TVF_R.refresh();
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
		tasks = db.get_tasks();
        for(int x = 0;x < tasks.length;x++){
        	TableRow trposis = new TableRow(mActivity);
        	final Button btasks = new Button(mActivity);
        	btasks.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        	btasks.setText(tasks[x]);
            btasks.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            btasks.setOnClickListener(new OnClickListener(){

    			@Override
    			public void onClick( View v) {
    				AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
    				adialog = new Dialog(mActivity);
    				
    				final LinearLayout ll1 = standard.newlinlayout(mActivity, LinearLayout.VERTICAL);
    				final Button bTSedit = new Button(mActivity);
    				bTSedit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	        	bTSedit.setText("Edit Standards");
    	            bTSedit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
    	            bTSedit.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							standard.fragmentswitch_to_new(2,TEFSFRAGMENT,String.valueOf(btasks.getText()));
							adialog.cancel();
						}
    	            	
    	            });
    	            final Button bTPedit = new Button(mActivity);
    				bTPedit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
    	        	bTPedit.setText("Edit Programms");
    	            bTPedit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
    	            bTPedit.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							standard.fragmentswitch_to_new(2,TEFPFRAGMENT,String.valueOf(btasks.getText()));
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
							
							sure_delete(btasks);
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
            
            btasks.setGravity(Gravity.CENTER);
            trposis.addView(btasks);
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
	public void sure_delete(final Button btasks) {
		
			DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			            switch (which){ 
			            case DialogInterface.BUTTON_POSITIVE:
			            	if (db.exists_task(String.valueOf(btasks.getText())) != 0) 
							{
								if(db.remove_task(String.valueOf(btasks.getText())))
								{
									Toast.makeText(mActivity, String.valueOf(btasks.getText())+" was deleted !!", Toast.LENGTH_LONG).show();
									adialog.cancel();
									TVF_R.refresh();
								}
								else
								{
									Toast.makeText(mActivity, "Error , "+ String.valueOf(btasks.getText())+" was not deleted !!", Toast.LENGTH_LONG).show();
									adialog.cancel();
								}
							}
							else
							{
								adialog.cancel();
							Toast.makeText(mActivity, String.valueOf(btasks.getText())+" don't exists !!", Toast.LENGTH_LONG).show();
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
			Dialog dia = standard.Adialog_Delete_Confirmation(mActivity, String.valueOf(btasks.getText()), listener);
			dia.show();
			}
		
	
	
}