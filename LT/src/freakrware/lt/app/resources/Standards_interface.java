package freakrware.lt.app.resources;

import freakrware.lt.app.core.util.Coordinates;
import android.app.Activity;

public interface Standards_interface {

	public class Standards{
		
		public Activity mActivity;
		public Coordinates ccoords; 

		
		public void wait(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		public void thread_rename(String name){
			Thread t = Thread.currentThread();
			t.setName(name + " - Thread");
	
		}
		public void set_Activity(Activity activity){
			this.mActivity = activity;
		}
		public void ini_Ccoords(){
			this.ccoords = new Coordinates(mActivity);
		}
	}
}
