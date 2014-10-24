package freakrware.lt.app.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import freakrware.lt.app.core.util.SystemUiHider;
import freakrware.lt.app.resources.Interfaces;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends FragmentActivity implements Interfaces{

	public Activity mActivity;
	
	/**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO
//		db.add_setup_parameter("LAST_LOCATION");
//		db.add_setup_parameter("IR_DONE");
//		db.edit_setup_parameter_value(db.exists_parameter("IR_DONE"),String.valueOf(false));
//		db.edit_setup_parameter_value(db.exists_parameter("OOR_DONE"),String.valueOf(false));
		// TODO
	
		
		setContentView(R.layout.activity_fullscreen);
		mActivity=this;
		standard.set_Activity(mActivity);
		standard.set_Context(mActivity.getBaseContext());
		standard.ini_Ccoords();
		

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.ViewPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(NUM_PAGES);
		
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
        	if(mPager.getCurrentItem() == 2 && mFragmentList.get(mPager.getCurrentItem()) == standard.TEF){
        		standard.fragmentswitch(2, TVF);
        	}
        	else
        	{
        		if(mPager.getCurrentItem() == 1 && mFragmentList.get(mPager.getCurrentItem()) == standard.LEF){
            		standard.fragmentswitch(1, PVF);
            	}
            	else
            	{
            		mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            	}
        	}
            
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    	
    	
		public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            standard.ini_fragmentlist();
            standard.set_mPagerAdapter(this);
        }
		@Override
	    public int getItemPosition(Object object){
	        return POSITION_NONE;
	    }

		@Override
        public Fragment getItem(int position) {
        	return mFragmentList.get(position);
        }
        
		@Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
		

