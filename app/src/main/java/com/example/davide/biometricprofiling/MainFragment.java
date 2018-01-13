package com.example.davide.biometricprofiling;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainFragment extends ListFragment {
    private final List<String> names = new ArrayList<>();
    public interface OnListItemClickListener {
        void onListItemClick(int position);
    }

    private OnListItemClickListener mItemClickListener;

    public MainFragment() {
    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mItemClickListener = (OnListItemClickListener) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("dentro","va");
        Bundle args = getArguments();
        List<String> names= args.getStringArrayList("nomiB");

        final List<String> items = names;
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mItemClickListener.onListItemClick(position);
    }
}
