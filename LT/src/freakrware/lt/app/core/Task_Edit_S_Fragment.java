package freakrware.lt.app.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import freakrware.lt.app.resources.Interfaces;


public class Task_Edit_S_Fragment extends Fragment implements Interfaces{
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit, container, false);
        Bundle bundle = getArguments();
        TEFS_R.set_rootview(v,(String) bundle.get(TEFSFRAGMENT));
        TEFS_R.refresh();
        
		return v;
	}


}
