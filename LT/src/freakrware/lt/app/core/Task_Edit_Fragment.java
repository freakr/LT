package freakrware.lt.app.core;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import freakrware.lt.app.resources.Interfaces;


public class Task_Edit_Fragment extends Fragment implements Interfaces{
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_edit, container, false);
        Bundle bundle = getArguments();
        TEF_R.set_rootview(v,(String) bundle.get("Taskname"));
        TEF_R.refresh();
		return v;
	}

}
