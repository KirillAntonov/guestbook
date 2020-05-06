package com.practice.guestbook;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.practice.guestbook.Network.ClientApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDataSource extends PageKeyedDataSource<Integer, Data> {

    public static final int PAGE_SIZE = 100;
    private static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Data> callback) {
        ClientApi.getApi()
                .getListOfComments("Bearer " + IdentificationActivity.user.getToken().token, FIRST_PAGE)
                .enqueue(new Callback<CommentApiResponse>() {
                    @Override
                    public void onResponse(Call<CommentApiResponse> call, Response<CommentApiResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().data, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Data> callback) {

        ClientApi.getApi()
                .getListOfComments("Bearer " + IdentificationActivity.user.getToken().token, params.key)
                .enqueue(new Callback<CommentApiResponse>() {
                    @Override
                    public void onResponse(Call<CommentApiResponse> call, Response<CommentApiResponse> response) {

                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().data, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentApiResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Data> callback) {

        ClientApi.getApi()
                .getListOfComments("Bearer " + IdentificationActivity.user.getToken().token, params.key)
                .enqueue(new Callback<CommentApiResponse>() {
                    @Override
                    public void onResponse(Call<CommentApiResponse> call, Response<CommentApiResponse> response) {

                        if (response.body() != null) {
                            Integer key = (response.body().meta.current_page < response.body().meta.last_page) ? params.key + 1 : params.key;
                            callback.onResult(response.body().data, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentApiResponse> call, Throwable t) {

                    }
                });

    }
}
