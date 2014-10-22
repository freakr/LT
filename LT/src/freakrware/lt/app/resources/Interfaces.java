package freakrware.lt.app.resources;

import freakrware.lt.app.core.LEF_Refresh;
import freakrware.lt.app.core.PVF_Refresh;
import freakrware.lt.app.core.TEF_Refresh;
import freakrware.lt.app.core.TVF_Refresh;
import freakrware.lt.app.core.util.DataBase;

public interface Interfaces extends Standards_interface,DataBase_interface{
	
	Standards standard = new Standards();
	DataBase db = new DataBase();
	PVF_Refresh PVF_R = new PVF_Refresh();
	TVF_Refresh TVF_R = new TVF_Refresh();
	TEF_Refresh TEF_R = new TEF_Refresh();
	LEF_Refresh LEF_R = new LEF_Refresh();
	
	

}
