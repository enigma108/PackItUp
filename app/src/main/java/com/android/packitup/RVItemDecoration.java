package com.android.packitup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class RVItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "DIVIDER_ITEM_DECORATION";

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider, android.support.v7.appcompat.R.attr.selectableItemBackground
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation;
    private int resid = 0;

    public RVItemDecoration(Context context, int orientation) {

        //fetch the default list divider
        final TypedArray ta = context.obtainStyledAttributes(ATTRS);
        mDivider = ta.getDrawable(0);
        resid = ta.getResourceId(1, 0);
        ta.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {

        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid operation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        Log.d(TAG, "onDraw");
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
        //setSelectableItemBackground(parent);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }

    private void setSelectableItemBackground(RecyclerView parent) {

        final int childCount = parent.getChildCount();

        //iterate through the children in the ViewGroup (RecyclerView)
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            child = child.findViewById(R.id.view_style);
            child.setBackgroundResource(resid);
        }
    }
}
