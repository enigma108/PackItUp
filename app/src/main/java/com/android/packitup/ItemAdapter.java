package com.android.packitup;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private static final boolean DUMMY_DATA = true;

    private List<Item> mDataset;

    public ItemAdapter() {

        mDataset = new ArrayList<Item>();
        if(DUMMY_DATA) {
            addDummyData();
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row, parent, false);
        ViewHolder vh = new ViewHolder(v, parent.getContext(), this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Item item = mDataset.get(position);
        holder.mTextView.setText(item.getItemName());

        if(item.isSelected()) {
            holder.mAppCompatCheckBox.setChecked(true);
        } else {
            holder.mAppCompatCheckBox.setChecked(false);
        }

        holder.mAppCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setIsSelected(isChecked);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        // each data item is just a string in this case
        private TextView mTextView;
        private AppCompatCheckBox mAppCompatCheckBox;
        private ItemAdapter parent;
        private boolean checked = false;
        private Context context;

        public ViewHolder(View v, Context c, ItemAdapter parent) {
            super(v);

            this.context = c;
            v.setOnTouchListener(this);
            mTextView = (TextView) v.findViewById(R.id.item_text);
            mAppCompatCheckBox = (AppCompatCheckBox)v.findViewById(R.id.checkbox);
            this.parent = parent;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void addDummyData() {
        mDataset.add(new Item("Coffee", true));
        mDataset.add(new Item("Banana"));
        mDataset.add(new Item("Custard Apple"));
        mDataset.add(new Item("Dates"));
        mDataset.add(new Item("Grape"));
        mDataset.add(new Item("Jack Fruit"));
        mDataset.add(new Item("Marshmallow"));
        mDataset.add(new Item("JellyBean"));
        mDataset.add(new Item("Kitkat"));
        mDataset.add(new Item("ButterCup"));
    }
}
