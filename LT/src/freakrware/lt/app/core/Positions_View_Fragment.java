package freakrware.lt.app.core;

import freakrware.lt.app.resources.Interfaces;
import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class Positions_View_Fragment extends Fragment implements Interfaces{
	
	private Activity mActivity;
	
	@SuppressWarnings("deprecation")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_positions_list, container, false);
        this.mActivity = standard.mActivity;
        TableLayout tl = (TableLayout) v.findViewById(R.id.TLPositions);
        TableRow trpositions = new TableRow(mActivity);
        trpositions.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        TextView tv = new TextView(mActivity);
        tv.setText("Positions");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        trpositions.addView(tv);
        trpositions.setGravity(Gravity.CENTER);
        TableRow trseperator = new TableRow(mActivity);
        trseperator.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        View separator = new View(mActivity);
        separator.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        separator.setBackgroundColor(color.white);
        trseperator.addView(separator);
        trseperator.setGravity(Gravity.CENTER);
        tl.addView(trpositions);
        tl.addView(trseperator);
		return v;
	}

}
