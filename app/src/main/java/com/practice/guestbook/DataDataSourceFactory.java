package com.practice.guestbook;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class DataDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> dataLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        DataDataSource dataDataSource = new DataDataSource();
        dataLiveDataSource.postValue(dataDataSource);
        return dataDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getDataLiveDataSource() {
        return dataLiveDataSource;
    }
}
