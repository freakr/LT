package freakrware.lt.app.resources;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import freakrware.lt.app.core.Live_Data_Fragment;
import freakrware.lt.app.core.Positions_View_Fragment;
import freakrware.lt.app.core.Tasks_View_Fragment;

public interface Fragment_interface {
	
	Fragment PVF = new Positions_View_Fragment();
	Fragment TVF = new Tasks_View_Fragment();
	Fragment LDF = new Live_Data_Fragment();
	ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
	public static final String TEFFRAGMENT = "TEF";

}
