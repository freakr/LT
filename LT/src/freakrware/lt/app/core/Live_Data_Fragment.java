package freakrware.lt.app.core;

import java.util.Locale;

import freakrware.lt.app.core.util.Coordinates;
import freakrware.lt.app.core.util.DataBase;
import freakrware.lt.app.core.util.SystemUiHider;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Live_Data_Fragment extends Fragment {
	
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 5000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
		private SystemUiHider mSystemUiHider;
		private TextView vlongitude ;
    	private TextView vlatitude ;
    	private TextView vaccuracy ;
    	private TextView vprovider ;
    	private TextView vtime ;
    	private Activity mActivity;
    	private Coordinates ccoords;
    	private ActualCoords acoord;
    	private DataBase db = new DataBase();

    	private Button bsaveposition;
    	private Button bshowposition;
	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.live_data_fragment, container, false);
        
        ccoords = new Coordinates(mActivity);
		
        bsaveposition = (Button) v.findViewById(R.id.bSavePosition);
		bsaveposition.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				final Location pos = ccoords.get_location();
				AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
				final EditText input = new EditText(mActivity);
				input.setInputType(InputType.TYPE_CLASS_TEXT);
				dialog.setView(input);
				dialog.setMessage("Name for this Position") 
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										String inhalt = input.getText().toString();
										if (db.exists_location(inhalt) == 0) {
											db.add_location(inhalt);
											db.add_location_position(db.exists_location(inhalt), pos.getLatitude(), pos.getLongitude(),
													pos.getAccuracy(),pos.getProvider());
											Toast.makeText(mActivity, inhalt+" was added", Toast.LENGTH_LONG).show();
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
		bshowposition = (Button) v.findViewById(R.id.bShowPosition);
		bshowposition.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String lati = String.valueOf(vlatitude.getText());
				String longi = String.valueOf(vlongitude.getText());
				String uri = String.format(Locale.ENGLISH,"geo:%s,%s?z=%d", lati, longi,250);
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
				try
		        {
		            startActivity(intent);
		        }
		        catch(ActivityNotFoundException ex)
		        {
		            try
		            {
		                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		                startActivity(unrestrictedIntent);
		            }
		            catch(ActivityNotFoundException innerEx)
		            {
		                Toast.makeText(mActivity, "Please install a maps application", Toast.LENGTH_LONG).show();
		            }
		        }
			}
			
		});
		vlongitude = (TextView) v.findViewById(R.id.TVLongitudevalue);
		vlatitude = (TextView) v.findViewById(R.id.TVLatitudevalue);
		vaccuracy = (TextView) v.findViewById(R.id.TVAccuracyvalue);
		vprovider = (TextView) v.findViewById(R.id.TVProvidervalue);
		vtime = (TextView) v.findViewById(R.id.TVTimevalue);
		acoord = new ActualCoords(vlongitude,vlatitude,vaccuracy,vtime,vprovider,ccoords,mActivity);
		new Thread(acoord).start();
        
		final View controlsView = v.findViewById(R.id.fullscreen_content_controls);
		final View contentView = v.findViewById(R.id.fullscreen_content);

		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		mSystemUiHider = SystemUiHider.getInstance(mActivity, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					// Cached values.
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							// If the ViewPropertyAnimator API is available
							// (Honeycomb MR2 and later), use it to animate the
							// in-layout UI controls at the bottom of the
							// screen.
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
							// If the ViewPropertyAnimator APIs aren't
							// available, simply show or hide the in-layout UI
							// controls.
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
							// Schedule a hide().
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});
		delayedHide(100);		
		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});

		// Upon interacting with UI controls, delay any scheduled hide()
		// operations to prevent the jarring behavior of controls going away
		// while interacting with the UI.
		
	

	
	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */
	OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	
	return v;
    }
    
    Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};
	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
    
    
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
   
}


