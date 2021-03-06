package com.example.nvdovin.weatherapp.domain.utils.design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class SeparatorDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    public SeparatorDecoration(Context context, int color, float heightDp, Paint p, TypedValueWrapper typedValueWrapper) {
        paint = p;
        paint.setColor(color);
        final float thickness = typedValueWrapper.getThickness(heightDp, context);
        paint.setStrokeWidth(thickness);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        final int position = params.getViewAdapterPosition();
        if (position < state.getItemCount()) {
            outRect.set(0, 0, 0, (int) paint.getStrokeWidth());
        } else {
            outRect.setEmpty();
        }
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int offset = (int) (paint.getStrokeWidth());
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View view = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

            final int position = params.getViewAdapterPosition();

            if (position < state.getItemCount()) {
                c.drawLine(view.getLeft(), view.getBottom() + offset, view.getRight(), view.getBottom() + offset, paint);
            }
        }
    }
}
