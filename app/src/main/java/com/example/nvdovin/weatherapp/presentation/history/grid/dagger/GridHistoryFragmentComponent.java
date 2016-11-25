package com.example.nvdovin.weatherapp.presentation.history.grid.dagger;

import com.example.nvdovin.weatherapp.presentation.application.dagger.AppComponent;
import com.example.nvdovin.weatherapp.presentation.history.grid.GridHistoryFragment;

import dagger.Component;

/**
 * Created by nvdovin on 11/24/2016.
 */

@Component(modules = GridHistoryFragmentModule.class, dependencies = AppComponent.class)
@GridHistoryFragmentScope
public interface GridHistoryFragmentComponent {

    void inject(GridHistoryFragment gridHistoryFragment);

}
