package com.android.packitup;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemsFragment extends Fragment implements AddItemDialogFragment.OnItemAddListener{

    private static final String TAG = ItemsFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ItemAdapter mItemAdapter;
    //private Context context;
    private RVItemDecoration mRVItemDecoration;
    private FloatingActionButton fab;

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.items_main, container, false);

        //initialize RecyclerView
        initRecyclerView(view);

        //initialize FloatingActionButton
        initFAB(view);

        return view;
    }

    private void initRecyclerView(View view) {

        //init recyclerview
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //all rows are equal size -- perf benefit
        mRecyclerView.setHasFixedSize(true);

        //init LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //init decorations like line dividers and single touch feedback.
        mRVItemDecoration = new RVItemDecoration(getContext(), getOrientation());
        mRecyclerView.addItemDecoration(mRVItemDecoration);

        //init an adapter
        mItemAdapter = new ItemAdapter();
        mRecyclerView.setAdapter(mItemAdapter);

    }

    private void initFAB(View view) {

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FragmentManager fm = getChildFragmentManager();
                Fragment fragment = fm.findFragmentByTag("dialog_fragment");
                if(fragment == null) {
                    AddItemDialogFragment aidf = new AddItemDialogFragment();
                    aidf.setOnItemAddListener(ItemsFragment.this);
                    aidf.show(fm, "dialog_fragment");
                }
            }
        });
    }

    @Override
    public void onItemAdd(String item) {

        Log.d(TAG, "item: " + item);
    }

    private int getOrientation() {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            return LinearLayoutManager.VERTICAL;
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return LinearLayoutManager.HORIZONTAL;
        }
        return 0;
    }
}

