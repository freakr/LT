package freakrware.lt.app.resources;

import freakrware.lt.app.core.ASO_Refresh;
import freakrware.lt.app.core.LEF_Refresh;
import freakrware.lt.app.core.LVF_Refresh;
import freakrware.lt.app.core.SEF_Refresh;
import freakrware.lt.app.core.TEFP_Refresh;
import freakrware.lt.app.core.TEFS_Refresh;
import freakrware.lt.app.core.TVF_Refresh;
import freakrware.lt.app.core.util.DataBase;

public interface Interfaces extends Standards_interface,DataBase_interface{
	
	Standards standard = new Standards();
	DataBase db = new DataBase();
	SEF_Refresh SEF_R = new SEF_Refresh();
	ASO_Refresh ASO_R = new ASO_Refresh();
	LVF_Refresh LVF_R = new LVF_Refresh();
	TVF_Refresh TVF_R = new TVF_Refresh();
	TEFS_Refresh TEFS_R = new TEFS_Refresh();
	TEFP_Refresh TEFP_R = new TEFP_Refresh();
	LEF_Refresh LEF_R = new LEF_Refresh();
	
	

}
