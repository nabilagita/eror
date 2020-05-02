package com.example.movie.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie.model.upcoming.UpcomingResponse;
import com.example.movie.model.upcoming.UpcomingResultsItem;
import com.example.movie.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingViewModel extends ViewModel {
    private ApiMain apiMain;

    private MutableLiveData<ArrayList<UpcomingResultsItem>> listUpcomingMovie = new MutableLiveData<>();

    public void setUpcomingMovie(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApiUpcoming().getUpcoming().enqueue(new Callback<UpcomingResponse>() {
            @Override
            public void onResponse(Call<UpcomingResponse> call, Response<UpcomingResponse> response) {
                UpcomingResponse responseUpcoming = response.body();
                if (responseUpcoming != null && responseUpcoming.getResults()!= null){
                    ArrayList<UpcomingResultsItem> upcomingItems = responseUpcoming.getResults();
                    listUpcomingMovie.postValue(upcomingItems);
                }
            }

            @Override
            public void onFailure(Call<UpcomingResponse> call, Throwable t) {

            }
        });
    }

        public LiveData<ArrayList<UpcomingResultsItem>> getUpcoming(){
        return listUpcomingMovie;
        }
}
