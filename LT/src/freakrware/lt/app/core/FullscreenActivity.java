package freakrware.lt.app.core;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
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

	private static final int SETUP = 1;

	private static final int MENU_QUIT = 2;
	
	private static final int ASO = 3;

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
//		db.add_setup_parameter("LAST_POS_LATI");
//		String strsql = "CREATE TABLE "+ DB_TABLE_11 +" ("+ DB_COL_21 +" INTEGER , "+ DB_COL_24 +" DOUBLE, "+ DB_COL_25 +" DOUBLE, "+ DB_COL_26 +" DOUBLE, "+ DB_COL_23 +" VARCHAR(255), "
//				+ "FOREIGN KEY ("+ DB_COL_21 +") REFERENCES "+ DB_TABLE_10 +" ("+ DB_COL_21 +") on delete cascade)"; 
//		db.edit_database(strsql);
		
		setContentView(R.layout.activity_fullscreen);
		mActivity=this;
		standard.set_Activity(mActivity);
		standard.set_Context(mActivity.getBaseContext());
		standard.ini_Ccoords();
		
//		standard.shareWhatsApp(mActivity,"01708058178");
		
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.ViewPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(NUM_PAGES);
		
    }
	
	/* Creates the menu items */
	public boolean onCreateOptionsMenu(Menu menu) {
	menu.add(0, ASO, 0, "ASO");
	menu.add(0, SETUP, 0, "Setup");
	menu.add(0, MENU_QUIT, 0, "Quit");
	return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case SETUP:
				if(mFragmentList.get(mPager.getCurrentItem()) != standard.SEF)
				{
					standard.fragmentswitch_to_new(mPager.getCurrentItem(), SEFFRAGMENT, "Setup");
				}
				return true;
			case ASO:
				if(mFragmentList.get(mPager.getCurrentItem()) != standard.ASO)
				{
					standard.fragmentswitch_to_new(mPager.getCurrentItem(), ASOFRAGMENT, "ASO");
				}
				return true;
			case MENU_QUIT:
				System.exit(0);
				return true;
		}
		return false;
	} 

    @Override
    public void onBackPressed() {
    	if(mFragmentList.get(mPager.getCurrentItem()) == standard.SEF || mFragmentList.get(mPager.getCurrentItem()) == standard.ASO)
    	{
    		standard.fragmentswitch(0, LDF);
    		standard.fragmentswitch(1, LVF);
    		standard.fragmentswitch(2, TVF);
    	}
    	else
    	{
    		switch(mPager.getCurrentItem())
    		{
    			case 0:
    				// If the user is currently looking at the first step, allow the system to handle the
    				// Back button. This calls finish() on this activity and pops the back stack.
    				super.onBackPressed();
    				break;
    			case 1:
    				if(mFragmentList.get(mPager.getCurrentItem()) == standard.LEF)
    				{
    					LVF_R.refresh();
    					standard.fragmentswitch(1, LVF);
    				}
    				else
    				{
    					mPager.setCurrentItem(mPager.getCurrentItem() - 1);
    				}
    				break;
    			case 2:
    				if(mFragmentList.get(mPager.getCurrentItem()) == standard.TEFS || mFragmentList.get(mPager.getCurrentItem()) == standard.TEFP)
    				{
    					TVF_R.refresh();
    					standard.fragmentswitch(2, TVF);
    				}
    				else
    				{
    					mPager.setCurrentItem(mPager.getCurrentItem() - 1);
    				}
    				break;
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
		

