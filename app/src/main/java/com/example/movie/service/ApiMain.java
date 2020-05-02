package com.example.movie.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMain {
    private Retrofit retrofit;

    public NowshowingRepository getApiNowshowing(){
        String BASE_URL = "https://api.themoviedb.org/";
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(NowshowingRepository.class);
    }

    public UpcomingRepository getApiUpcoming(){
        String BASE_URL = "https://api.themoviedb.org/";
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(UpcomingRepository.class);
    }
}
