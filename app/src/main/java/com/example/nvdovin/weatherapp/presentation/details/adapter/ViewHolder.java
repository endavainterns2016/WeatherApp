package com.example.nvdovin.weatherapp.presentation.details.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

abstract class ViewHolder<T> extends RecyclerView.ViewHolder {
    ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bindData(T data);
}
