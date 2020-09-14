package com.gacrnd.gcs.recyclerlayoutmanager;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Jack_Ou  created on 2020/9/14.
 */
public class SlideCardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 开始布局，重写Log.e(TAG, "You must override onLayoutChildren(Recycler recycler, State state) ");
     *
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //回收
        detachAndScrapAttachedViews(recycler);

        //布局
        int bottomItem;
        int itemCount = getItemCount();
        if (itemCount < CardConfig.MAX_SHOW_COUNT) {
            bottomItem = 0;
        } else {
            bottomItem = itemCount - CardConfig.MAX_SHOW_COUNT;
        }

        for (int i = bottomItem; i < itemCount; i++) {
            //复用
            final View view = recycler.getViewForPosition(i);
            addView(view);

            measureChildWithMargins(view, 0, 0);

            //getDecoratedMeasuredWidth测量了分割线宽度的方法
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);

            //layoutDecoratedWithMargins  在layoutChunk
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2, widthSpace / 2
                    + getDecoratedMeasuredWidth(view), heightSpace / 2 + getDecoratedMeasuredHeight(view));

            int level = itemCount - i - 1;
            if (level > 0) {
                if (level < CardConfig.MAX_SHOW_COUNT - 1){
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * level);
                    view.setScaleX(1 - CardConfig.SCALE_GAP * level);
                    view.setScaleY(1 - CardConfig.SCALE_GAP * level);
                } else {
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * (level -1));
                    view.setScaleX(1 - CardConfig.SCALE_GAP * (level -1));
                    view.setScaleY(1 - CardConfig.SCALE_GAP * (level -1));
                }
            }
        }
    }

    @Override
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        Log.e(CardConfig.TAG, "onMeasure");
    }


    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        Log.e(CardConfig.TAG, "onLayoutCompleted");
    }

}
