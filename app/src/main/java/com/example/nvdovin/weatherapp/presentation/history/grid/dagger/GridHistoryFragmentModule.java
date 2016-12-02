package com.example.nvdovin.weatherapp.presentation.history.grid.dagger;

import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
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
    GridHistoryAdapter provideGridHistoryAdapter(SharedPrefs sharedPrefs) {
        return new GridHistoryAdapter(gridHistoryFragment.getActivity(), sharedPrefs);
    }

    @Provides
    @GridHistoryFragmentScope
    GridHistoryView provideGridHistoryView(GridHistoryAdapter gridHistoryAdapter) {
        return new GridHistoryView(gridHistoryFragment.getActivity(), gridHistoryAdapter);
    }

    @Provides
    @GridHistoryFragmentScope
    GridHistoryPresenter provideGridHistoryPresenter(DataMapper dataMapper, GridHistoryView gridHistoryView, Navigator.Builder builder) {
        return new GridHistoryPresenter(dataMapper, gridHistoryView, builder);
    }

}
