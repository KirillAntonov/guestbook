package com.practice.guestbook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class DataViewModel extends ViewModel {

    LiveData dataPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;

    public DataViewModel() {
        DataDataSourceFactory dataDataSourceFactory = new DataDataSourceFactory();
        liveDataSource = dataDataSourceFactory.getDataLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(DataDataSource.PAGE_SIZE)
                .build();

        dataPagedList = (new LivePagedListBuilder(dataDataSourceFactory, config).setBoundaryCallback(
                new PagedList.BoundaryCallback() {
                    @Override
                    public void onZeroItemsLoaded() {
                        super.onZeroItemsLoaded();
                    }
                }
        ).build());
    }


}
