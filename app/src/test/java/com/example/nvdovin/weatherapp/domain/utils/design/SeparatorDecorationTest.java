package com.example.nvdovin.weatherapp.domain.utils.design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeparatorDecorationTest {

    @Mock
    Rect rect;
    @Mock
    View view;
    @Mock
    RecyclerView recyclerView;
    @Mock
    RecyclerView.State state;
    @Mock
    Paint paint;
    @Mock
    Canvas canvas;
    @Mock
    Context context;
    @Mock
    TypedValueWrapper typedValueWrapper;
    private SeparatorDecoration separatorDecoration;

    @Before
    public void setUp() throws Exception {
        separatorDecoration = new SeparatorDecoration(context, 1, 1f, paint, typedValueWrapper);
    }

    @Test
    public void testOnDrawForValidPosition() throws Exception {
        float test = 1f;
        when(paint.getStrokeWidth()).thenReturn(test);
        when(recyclerView.getChildCount()).thenReturn(1);
        View mockView = mock(View.class);
        when(recyclerView.getChildAt(anyInt())).thenReturn(mockView);
        RecyclerView.LayoutParams mockLayoutParams = mock(RecyclerView.LayoutParams.class);
        when(mockView.getLayoutParams()).thenReturn(mockLayoutParams);
        when(state.getItemCount()).thenReturn(1);

        separatorDecoration.onDraw(canvas, recyclerView, state);

        verify(canvas).drawLine(view.getLeft(), view.getBottom() + test, view.getRight(), view.getLeft() + test, paint);
    }

    @Test
    public void testOnDrawForInvalidPosition() throws Exception {
        float test = -1f;
        when(paint.getStrokeWidth()).thenReturn(test);
        when(recyclerView.getChildCount()).thenReturn(2);
        View mockView = mock(View.class);
        when(recyclerView.getChildAt(anyInt())).thenReturn(mockView);
        RecyclerView.LayoutParams mockLayoutParams = mock(RecyclerView.LayoutParams.class);
        when(mockView.getLayoutParams()).thenReturn(mockLayoutParams);
        when(state.getItemCount()).thenReturn(1);
        separatorDecoration.onDraw(canvas, recyclerView, state);
        verify(canvas, never()).drawLine(view.getLeft(), view.getBottom() + test, view.getRight(), view.getLeft() + test, paint);
    }

}