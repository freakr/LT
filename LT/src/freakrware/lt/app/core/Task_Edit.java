package freakrware.lt.app.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import freakrware.lt.app.resources.Interfaces;


@SuppressLint("NewApi")
public class Task_Edit implements Interfaces{

	private String task;
	private Activity mActivity;
	
	public Task_Edit(String task) {
		this.task = task;
		this.mActivity = standard.mActivity;
		onCreate();
	}

	public void onCreate(){
		// Create new fragment and transaction
		android.app.Fragment editFragment = new Task_Edit_Fragment();
		Bundle args = new Bundle();
		args.putString("Taskname",task);
		editFragment.setArguments(args);
		
		android.app.FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack
		transaction.replace(R.id.FL, editFragment);
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
	}
}
