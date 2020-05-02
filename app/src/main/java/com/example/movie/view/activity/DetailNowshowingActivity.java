package com.example.movie.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.nowshowing.NowshowingResultsItem;

public class DetailNowshowingActivity extends AppCompatActivity {

    ImageView ivPoster;
    TextView tvTitle, tvOverview, tvRate;
    private NowshowingResultsItem getNowshowing = new NowshowingResultsItem();


    public static final String DETAIL_NOWSHOWING = "detail_nowshowing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nowshowing);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivPoster = findViewById(R.id.detail_activity_ivposter);
        tvTitle = findViewById(R.id.detail_activity_tvtitle);
        tvOverview = findViewById(R.id.detail_activity_tvdesc);
        tvRate = findViewById(R.id.detail_activity_tvrate);

        getNowshowing = (NowshowingResultsItem) getIntent().getExtras().get(DETAIL_NOWSHOWING);


        tvTitle.setText(getNowshowing.getTitle());
        tvOverview.setText(getNowshowing.getOverview());
        tvRate.setText(getResources().getString(R.string.rating) + " : " + String.valueOf(getNowshowing.getVoteAverage()));

        Glide.with(getApplicationContext()).load(getNowshowing.getPosterPath()).into(ivPoster);
    }
    }

