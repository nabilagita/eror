package com.example.movie.view.viewmodel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie.model.nowshowing.NowshowingResponse;
import com.example.movie.model.nowshowing.NowshowingResultsItem;
import com.example.movie.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowshowingViewModel extends ViewModel {
    private ApiMain apiMain;

    private ArrayList<NowshowingResultsItem> nowshowingItems = new ArrayList<>();
    private MutableLiveData<ArrayList<NowshowingResultsItem>> ListNowshowingMovie = new MutableLiveData<>();

    public void setNowshowingMovie(){
        if (this.apiMain== null){
            apiMain = new ApiMain();
        }

        apiMain.getApiNowshowing().getNowshowing().enqueue(new Callback<NowshowingResponse>() {
            @Override
            public void onResponse(Call<NowshowingResponse> call, Response<NowshowingResponse> response) {
                NowshowingResponse responseNowshowing = response.body();
                if (responseNowshowing != null && responseNowshowing.getResults() != null){
                    nowshowingItems = responseNowshowing.getResults();
               ListNowshowingMovie.postValue(nowshowingItems);
                }
            }

            @Override
            public void onFailure(Call<NowshowingResponse> call, Throwable t) {

            }
        });
    }
        public LiveData<ArrayList<NowshowingResultsItem>> getNowshowing(){
        return ListNowshowingMovie;
        }
}
