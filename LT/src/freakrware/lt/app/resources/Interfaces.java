package freakrware.lt.app.resources;

import android.support.v4.app.Fragment;
import freakrware.lt.app.core.Live_Data_Fragment;
import freakrware.lt.app.core.PVF_Refresh;
import freakrware.lt.app.core.Positions_View_Fragment;
import freakrware.lt.app.core.TVF_Refresh;
import freakrware.lt.app.core.Tasks_View_Fragment;
import freakrware.lt.app.core.util.DataBase;

public interface Interfaces extends Standards_interface,DataBase_interface{
	
	Standards standard = new Standards();
	DataBase db = new DataBase();
	Fragment PVF = new Positions_View_Fragment();
	Fragment TVF = new Tasks_View_Fragment();
	Fragment LDF = new Live_Data_Fragment();
	PVF_Refresh PVF_R = new PVF_Refresh();
	TVF_Refresh TVF_R = new TVF_Refresh();
	

}
