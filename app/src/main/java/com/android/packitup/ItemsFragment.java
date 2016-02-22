package com.android.packitup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemsFragment extends Fragment {

    private static final String TAG = ItemsFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ItemAdapter mItemAdapter;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.items_main, container, false);

        //init recyclerview
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //all rows are equal size -- perf benefit
        mRecyclerView.setHasFixedSize(true);

        //init LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //init an adapter
        mItemAdapter = new ItemAdapter();
        mRecyclerView.setAdapter(mItemAdapter);

        return view;
    }
}

