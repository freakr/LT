package freakrware.lt.app.resources;

import freakrware.lt.app.core.LEF_Refresh;
import freakrware.lt.app.core.LVF_Refresh;
import freakrware.lt.app.core.SEF_Refresh;
import freakrware.lt.app.core.TEF_Refresh;
import freakrware.lt.app.core.TVF_Refresh;
import freakrware.lt.app.core.util.DataBase;

public interface Interfaces extends Standards_interface,DataBase_interface{
	
	Standards standard = new Standards();
	DataBase db = new DataBase();
	SEF_Refresh SEF_R = new SEF_Refresh();
	LVF_Refresh LVF_R = new LVF_Refresh();
	TVF_Refresh TVF_R = new TVF_Refresh();
	TEF_Refresh TEF_R = new TEF_Refresh();
	LEF_Refresh LEF_R = new LEF_Refresh();
	
	

}
