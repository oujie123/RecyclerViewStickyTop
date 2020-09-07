package com.example.stickytop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/7 22:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/7 22:11
 * @UpdateRemark: 更新说明
 */
class StarDecoration extends RecyclerView.ItemDecoration {

    private int headHeight;
    private Paint headPaint;
    private Paint textPaint;
    private Rect textRect;

    public StarDecoration(Context context) {
        headHeight = dp2px(context, 100);

        headPaint = new Paint();
        headPaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.WHITE);

        textRect = new Rect();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter starAdapter = (StarAdapter) parent.getAdapter();
            int childCount = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < childCount; i++) {
                //获取每一个item
                View view = parent.getChildAt(i);
                //获取当前文字
                int position = parent.getChildAdapterPosition(view);
                boolean isGroupHead = starAdapter.isGroupHeader(position);
                if (isGroupHead) {
                    c.drawRect(left, view.getTop() - headHeight, right, view.getTop(), headPaint);
                    String groupName = starAdapter.getGroupName(position);
                    textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                    Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                    c.drawText(groupName, left + 20, view.getTop() - (headHeight / 2 + (fontMetrics.descent + fontMetrics.ascent) / 2), textPaint);
                } else {
                    c.drawRect(left, view.getTop() - 4, right, view.getTop(), headPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //头部和其他地方分开留空
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter starAdapter = (StarAdapter) parent.getAdapter();
            //获得当前item的位子
            int position = parent.getChildLayoutPosition(view);
            if (starAdapter.isGroupHeader(position)) {
                outRect.set(0, headHeight, 0, 0);
            } else {
                outRect.set(0, 4, 0, 0);
            }
        }
    }

    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5f);
    }
}
