package com.example.nvdovin.weatherapp.presentation.history.grid.dagger;

import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.presentation.history.grid.GridHistoryFragment;
import com.example.nvdovin.weatherapp.presentation.history.grid.GridHistoryPresenter;
import com.example.nvdovin.weatherapp.presentation.history.grid.GridHistoryView;
import com.example.nvdovin.weatherapp.presentation.history.grid.adapter.GridHistoryAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class GridHistoryFragmentModule {

    private GridHistoryFragment gridHistoryFragment;

    public GridHistoryFragmentModule(GridHistoryFragment gridHistoryFragment) {
        this.gridHistoryFragment = gridHistoryFragment;
    }

    @Provides
    @GridHistoryFragmentScope
    GridHistoryFragment provideGridHistoryFragment() {
        return gridHistoryFragment;
    }

    @Provides
    @GridHistoryFragmentScope
    GridHistoryAdapter provideGridHistoryAdapter() {
        return new GridHistoryAdapter(gridHistoryFragment.getActivity());
    }

    @Provides
    @GridHistoryFragmentScope
    GridHistoryView provideGridHistoryView(GridHistoryFragment gridHistoryFragment, GridHistoryAdapter gridHistoryAdapter) {
        return new GridHistoryView(gridHistoryFragment.getActivity(), gridHistoryAdapter);
    }

    @Provides
    @GridHistoryFragmentScope
    GridHistoryPresenter provideGridHistoryPresenter(DataMapper dataMapper, GridHistoryView gridHistoryView) {
        return new GridHistoryPresenter(dataMapper, gridHistoryView);
    }

}
