package com.archer.transitionfirebasetest.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by alanaliaga on 1/1/17.
 */

public class ItemOffsetDecorator extends RecyclerView.ItemDecoration {
    private int mItemOffset;

    public ItemOffsetDecorator (int itemOffset){
        mItemOffset = itemOffset;
    }

    public ItemOffsetDecorator (@NonNull Context context, @IntegerRes int itemOffsetId){
        int itemOffsetDp = context.getResources().getInteger(itemOffsetId);
        mItemOffset = setupDimensions(context.getResources().getDisplayMetrics(), itemOffsetDp);
    }

    private int setupDimensions(DisplayMetrics metrics, int offsetDp) {
        return  offsetDp * (metrics.densityDpi / 160);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }
}


















