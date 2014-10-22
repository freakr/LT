package freakrware.lt.app.core;

import freakrware.lt.app.resources.Interfaces;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Location_Edit_Fragment extends Fragment implements Interfaces{
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit, container, false);
        Bundle bundle = getArguments();
        LEF_R.set_rootview(v,(String) bundle.get(LEFFRAGMENT));
        LEF_R.refresh();
        
		return v;
	}


}
